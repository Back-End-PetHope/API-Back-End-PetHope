package com.github.znoque.pethope.controller;

import com.github.znoque.pethope.Enum.Sexo;
import com.github.znoque.pethope.Enum.Temperamento;
import com.github.znoque.pethope.dto.PetDto;
import com.github.znoque.pethope.Enum.Especie;
import com.github.znoque.pethope.model.pet.Pet;
import com.github.znoque.pethope.Enum.Raca;
import com.github.znoque.pethope.services.PetService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/pets")
@CrossOrigin(origins = "*")
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) { this.petService = petService; }

    @PostMapping
    public ResponseEntity<Pet> createPet(@RequestBody @Valid PetDto petDto) {
        Pet pet = petService.savePet(petDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(pet);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pet> updatePet(@PathVariable int id, @RequestBody @Valid PetDto petDto) {
        Pet updatedPet = petService.updatePet(petDto, id);
        return ResponseEntity.ok(updatedPet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable int id) {
        petService.deletePetById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> getPetById(@PathVariable int id) {
        Pet pet = petService.getPetById(id);
        return ResponseEntity.ok(pet);
    }

    @GetMapping
    public List<Pet> getAllPets() {
        return petService.getPets();
    }

    //Gets para os seletores de cadastro
    @GetMapping("/especies")
    public ResponseEntity<List<Map<String, String>>> getEspecies() {
        List<Map<String, String>> especies = Arrays.stream(Especie.values())
                .map(e -> Map.of("nome", e.getDisplayName(), "value", e.name()))
                        .toList();

        return ResponseEntity.ok(especies);
    }

    @GetMapping("/racas")
    public ResponseEntity<List<Map<String, String>>> getRacasbyEspecie(@RequestParam String especie) {
        try {
            List<Map<String, String>> racas = Raca.getRacasByEspecie(Especie.fromDisplayName(especie)).stream()
                    .map(r -> Map.of("nome", r.getDisplayName(), "value", r.name()))
                    .toList();

            return ResponseEntity.ok(racas);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(List.of(Map.of("erro", e.getMessage())));
        }
    }

    @GetMapping("/temperamentos")
    public ResponseEntity<List<Map<String, String>>> getTemperamentos() {
        List<Map<String, String>> temperamentos = Arrays.stream(Temperamento.values())
                .map(t -> Map.of("nome", t.getDisplayName(), "value", t.name()))
                .toList();

        return ResponseEntity.ok(temperamentos);
    }

    @GetMapping("/sexos")
    public ResponseEntity<List<Map<String, String>>> getSexos() {
        List<Map<String, String>> sexos = Arrays.stream(Sexo.values())
                .map(s -> Map.of("nome", s.getDisplayName(), "value", s.name()))
                .toList();

        return ResponseEntity.ok(sexos);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Pet>> getPetByFilters(
            @RequestParam(required = false) String especie,
            @RequestParam(required = false) String raca,
            @RequestParam(required = false) Integer idadeMin,
            @RequestParam(required = false) Integer idadeMax
    ) {
        if (idadeMin != null && idadeMax != null && idadeMin > idadeMax) {
            return ResponseEntity.badRequest().build();
        }

        List<Pet> pets = petService.findByFilters(especie, raca, idadeMin, idadeMax);
        return ResponseEntity.ok(pets);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Pet> patchPet(@PathVariable int id) {
        Pet petInativado = petService.inativarPet(id);

        return ResponseEntity.ok(petInativado);
    }


}
