package com.github.znoque.pethope.repository;

import com.github.znoque.pethope.model.pet.Especie;
import com.github.znoque.pethope.model.pet.Pet;
import com.github.znoque.pethope.model.pet.Raca;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Integer> {

    List<Pet> findByEspecie (Especie especie);

    List<Pet> findByRaca (Raca raca);

    List<Pet> findByIdadeBetween (int idadeMin, int idadeMax);
}