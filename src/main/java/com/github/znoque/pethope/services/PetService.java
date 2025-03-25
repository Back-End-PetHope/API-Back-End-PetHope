package com.github.znoque.pethope.services;

import com.github.znoque.pethope.dto.PetDto;
import com.github.znoque.pethope.model.pet.Pet;
import com.github.znoque.pethope.repository.PetRepository;
import org.springframework.stereotype.Service;

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


}
