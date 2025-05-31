package com.github.znoque.pethope.repository;

import com.github.znoque.pethope.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PetRepository extends JpaRepository<Pet, Integer>, JpaSpecificationExecutor<Pet> {
}