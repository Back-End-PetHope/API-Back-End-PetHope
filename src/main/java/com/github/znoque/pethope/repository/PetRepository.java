package com.github.znoque.pethope.repository;

import com.github.znoque.pethope.Enum.Especie;
import com.github.znoque.pethope.model.pet.Pet;
import com.github.znoque.pethope.Enum.Raca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Integer>, JpaSpecificationExecutor<Pet> {

//    List<Pet> findByEspecie (Especie especie);
//
//    List<Pet> findByRaca (Raca raca);
//
//    List<Pet> findByIdadeBetween (int idadeMin, int idadeMax);
}