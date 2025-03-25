package com.github.znoque.pethope.services;

import com.github.znoque.pethope.dto.PetDto;
import com.github.znoque.pethope.model.pet.Especie;
import com.github.znoque.pethope.model.pet.Pet;
import com.github.znoque.pethope.repository.PetRepository;
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
        pet.setEspecie(petDto.especie());
        pet.setIdade(petDto.idade());
        pet.setPorte(petDto.porte());
        pet.setTemperamento(petDto.temperamento());
        pet.setDoenca(petDto.doenca());
        pet.setNecessidadeEspecifica(petDto.necessidadeEspecifica());
        pet.setAtivo(petDto.ativo());
        pet.setDisponibilidade(pet.isDisponibilidade());

        return petRepository.save(pet);
    }

    public List<Pet> getPets() {
        return petRepository.findAll();
    }

    public Pet getPetById (int id) {
        return petRepository.findById(id).orElseThrow(() -> new RuntimeException("Pet não existe"));

    }

    public Pet updatePet (PetDto petDto, int id) {
        Pet pet = petRepository.findById(id).orElseThrow(() -> new RuntimeException("Pet não encontrado"));

            pet.setNome(petDto.nome());
            pet.setEspecie(petDto.especie());
            pet.setIdade(petDto.idade());
            pet.setPorte(petDto.porte());
            pet.setTemperamento(petDto.temperamento());
            pet.setDoenca(petDto.doenca());
            pet.setNecessidadeEspecifica(petDto.necessidadeEspecifica());
            pet.setAtivo(petDto.ativo());
            pet.setDisponibilidade(pet.isDisponibilidade());

            return petRepository.save(pet);

    }

    public void deletePetById(int id) {
       if(petRepository.existsById(id)) {
           petRepository.deleteById(id);
       } else {
           throw new RuntimeException("Pet não existe ou ja deletado");
       }
    }

    public List<Pet> findByEspecie(Especie especie) {
        return petRepository.findByEspecie(especie);
    }

    public List<Pet> findByRaca(String raca) {
        return petRepository.findByRaca(raca);
    }

    public List<Pet> findByIdadeBetween(int idadeMin, int idadeMax) {
        if(idadeMin > idadeMax) {
            throw new RuntimeException("Idade minima não pode ser maior que idade máxima");
        }
        return petRepository.findByIdadeBetween(idadeMin, idadeMax);
    }


}
