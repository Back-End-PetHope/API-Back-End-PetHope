package com.github.znoque.pethope.services;

import com.github.znoque.pethope.dto.PetDto;
import com.github.znoque.pethope.model.pet.Especie;
import com.github.znoque.pethope.model.pet.Pet;
import com.github.znoque.pethope.model.pet.Raca;
import com.github.znoque.pethope.repository.PetRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public Pet savePet(PetDto petDto) {
        Pet pet = new Pet();

        pet.setNome(petDto.nome());
        pet.setDescricao(petDto.descricao());
        pet.setEspecie(petDto.especie());
        pet.setRaca(petDto.raca());
        pet.setIdade(petDto.idade());
        pet.setSexo(petDto.sexo());
        pet.setAtivo(petDto.ativo());
        pet.setDisponibilidade(pet.isDisponibilidade());

        return petRepository.save(pet);
    }

    public List<Pet> getPets() {
        return petRepository.findAll();
    }

    public Pet getPetById (int id) {
        return petRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pet não encontrado com o id: " + id));

    }

    public Pet updatePet (PetDto petDto, int id) {
        Pet pet = petRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pet não encontrado com o id: " + id));

        pet.setNome(petDto.nome());
        pet.setDescricao(petDto.descricao());
        pet.setEspecie(petDto.especie());
        pet.setRaca(petDto.raca());
        pet.setIdade(petDto.idade());
        pet.setSexo(petDto.sexo());
        pet.setAtivo(petDto.ativo());
        pet.setDisponibilidade(pet.isDisponibilidade());

        return petRepository.save(pet);

    }

    public void deletePetById(int id) {
       if(petRepository.existsById(id)) {
           petRepository.deleteById(id);
       } else {
           throw new RuntimeException("Pet não existe ou já deletado.");
       }
    }

    public List<Pet> findByEspecie(Especie especie) {
        return petRepository.findByEspecie(especie);
    }

    public List<Pet> findByRaca(Raca raca) {
        return petRepository.findByRaca(raca);
    }

    public List<Pet> findByIdadeBetween(int idadeMin, int idadeMax) {
        if(idadeMin > idadeMax) {
            throw new IllegalArgumentException("Idade minima não pode ser maior que idade máxima.");
        }
        return petRepository.findByIdadeBetween(idadeMin, idadeMax);
    }

    public Pet inativarPet(int id) {
        Pet petInativado = petRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pet não encontrado."));

        petInativado.setAtivo(false);

        return petRepository.save(petInativado);
    }


}
