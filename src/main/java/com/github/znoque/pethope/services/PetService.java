package com.github.znoque.pethope.services;

import com.github.znoque.pethope.dto.pet.PetRequestDto;
import com.github.znoque.pethope.dto.pet.PetResponseDto;
import com.github.znoque.pethope.mapper.PetMapper;
import com.github.znoque.pethope.model.Pet;
import com.github.znoque.pethope.model.User;
import com.github.znoque.pethope.repository.PetRepository;
import com.github.znoque.pethope.specification.PetSpec;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    private final PetRepository petRepository;
    private final PetMapper petMapper;

    public PetService(PetRepository petRepository, PetMapper petMapper) {
        this.petRepository = petRepository;
        this.petMapper = petMapper;
    }

    @Transactional
    public PetResponseDto savePet(PetRequestDto petRequestDto) {
        Pet pet = petMapper.toPet(petRequestDto);

        User usuarioLogado = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        pet.setUsuario(usuarioLogado);

        Pet persistedPet = petRepository.save(pet);
        return petMapper.toPetResponseDto(persistedPet);
    }

    public List<PetResponseDto> getPets() {
        return petRepository.findAll().stream().map(petMapper::toPetResponseDto).toList();
    }

    public PetResponseDto getPetById(int id) {
        Pet pet = petRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Pet não encontrado com o id: " + id));
        return petMapper.toPetResponseDto(pet);
    }

    public PetResponseDto updatePet(PetRequestDto petRequestDto, int id) {
        Pet pet = petRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Pet não encontrado com o id: " + id));

        pet.atualizarCom(petRequestDto);

        Pet updatedPet = petRepository.save(pet);

        return petMapper.toPetResponseDto(updatedPet);

    }

    public void deletePetById(int id) {
        if (!petRepository.existsById(id)) {
            throw new EntityNotFoundException("Pet não encontrado com o id: " + id);
        }
        petRepository.deleteById(id);
    }

    public PetResponseDto inativarPet(int id) {
        Pet pet = petRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Pet não encontrado com o id: " + id));

        pet.inativar();

        Pet inactivePet = petRepository.save(pet);

        return petMapper.toPetResponseDto(inactivePet);
    }

    public List<PetResponseDto> findByFilters(String especie, String raca, Integer idadeMin, Integer idadeMax) {
        Specification<Pet> spec = PetSpec.filters(especie, raca, idadeMin, idadeMax);

        return petRepository.findAll(spec).stream().map(petMapper::toPetResponseDto).toList();
    }


}
