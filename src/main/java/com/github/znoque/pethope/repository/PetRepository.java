package com.github.znoque.pethope.repository;

import com.github.znoque.pethope.model.pet.Especie;
import com.github.znoque.pethope.model.pet.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Integer> {

    boolean findById (String id);

    List<Pet> findByEspecie (Especie especie);

    List<Pet> findByRaca (String raca);

    List<Pet> findByIdade (int idadeMin, int idadeMax);
}