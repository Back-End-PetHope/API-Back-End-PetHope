package com.github.znoque.pethope.controller;

import com.github.znoque.pethope.dto.PetDto;
import com.github.znoque.pethope.model.pet.Pet;
import com.github.znoque.pethope.services.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping("/v1/update/{id}")
    public ResponseEntity<Pet> updatePet(@PathVariable int id, @RequestBody @Valid PetDto petDto) {
        Pet updatedPet = petService.updatePet(petDto, id);
        return ResponseEntity.ok(updatedPet);
    }

    @DeleteMapping("/v1/delete/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable int id) {
        petService.deletePetById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/v1/getById/{id}")
    public ResponseEntity<Pet> getPetById(@PathVariable int id) {
        Pet pet = petService.getPetById(id);
        return ResponseEntity.ok(pet);
    }

    @PatchMapping("/v1/patch/{id}")
    public ResponseEntity<Pet> patchPet(@PathVariable int id) {
        Pet petInativado = petService.inativarPet(id);

        return ResponseEntity.ok(petInativado);
    }


}
