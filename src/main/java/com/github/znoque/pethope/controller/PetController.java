package com.github.znoque.pethope.controller;

import com.github.znoque.pethope.dto.PetDto;
import com.github.znoque.pethope.model.pet.Pet;
import com.github.znoque.pethope.services.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pets")
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) { this.petService = petService; }

    @PostMapping("/v1/create")
    public ResponseEntity<Pet> createPet(@RequestBody @Valid PetDto petDto) {
        Pet pet = petService.savePet(petDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(pet);
    }

}
