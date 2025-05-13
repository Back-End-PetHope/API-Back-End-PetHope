package com.github.znoque.pethope.controller;

import com.github.znoque.pethope.docs.PetApi;
import com.github.znoque.pethope.dto.ResponseDto;
import com.github.znoque.pethope.dto.pet.PetRequestDto;
import com.github.znoque.pethope.dto.pet.PetResponseDto;
import com.github.znoque.pethope.enums.Especie;
import com.github.znoque.pethope.enums.Raca;
import com.github.znoque.pethope.enums.Sexo;
import com.github.znoque.pethope.enums.Temperamento;
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
public class PetController implements PetApi {
    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @Override
    @PostMapping
    public ResponseEntity<ResponseDto<PetResponseDto>> create(@RequestBody @Valid PetRequestDto petRequestDto) {
        PetResponseDto pet = petService.savePet(petRequestDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto<>(pet));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<PetResponseDto>> update(@PathVariable int id, @RequestBody @Valid PetRequestDto petRequestDto) {
        PetResponseDto updatedPet = petService.updatePet(petRequestDto, id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto<>(updatedPet));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        petService.deletePetById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<PetResponseDto>> getById(@PathVariable int id) {
        PetResponseDto pet = petService.getPetById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto<>(pet));
    }

    @Override
    @GetMapping
    public ResponseEntity<ResponseDto<List<PetResponseDto>>> getAll() {
        List<PetResponseDto> pets = petService.getPets();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto<>(pets));
    }

    @Override
    @GetMapping("/especies")
    public ResponseEntity<List<Map<String, String>>> getEspecies() {
        List<Map<String, String>> especies = Arrays.stream(Especie.values())
                .map(e -> Map.of("nome", e.getDisplayName(), "value", e.name()))
                .toList();

        return ResponseEntity.ok(especies);
    }

    @Override
    @GetMapping("/racas")
    public ResponseEntity<List<Map<String, String>>> getRacasbyEspecie(@RequestParam String especie) {
        List<Map<String, String>> racas = Raca.getRacasByEspecie(Especie.fromDisplayName(especie)).stream()
                .map(r -> Map.of("nome", r.getDisplayName(), "value", r.name()))
                .toList();

        return ResponseEntity.ok(racas);

    }

    @Override
    @GetMapping("/temperamentos")
    public ResponseEntity<List<Map<String, String>>> getTemperamentos() {
        List<Map<String, String>> temperamentos = Arrays.stream(Temperamento.values())
                .map(t -> Map.of("nome", t.getDisplayName(), "value", t.name()))
                .toList();

        return ResponseEntity.ok(temperamentos);
    }

    @Override
    @GetMapping("/sexos")
    public ResponseEntity<List<Map<String, String>>> getSexos() {
        List<Map<String, String>> sexos = Arrays.stream(Sexo.values())
                .map(s -> Map.of("nome", s.getDisplayName(), "value", s.name()))
                .toList();

        return ResponseEntity.ok(sexos);
    }

    @Override
    @GetMapping("/filtros")
    public ResponseEntity<ResponseDto<List<PetResponseDto>>> getByFilters(
            @RequestParam(required = false) String especie,
            @RequestParam(required = false) String raca,
            @RequestParam(required = false) Integer idadeMin,
            @RequestParam(required = false) Integer idadeMax
    ) {
        if (idadeMin != null && idadeMax != null && idadeMin > idadeMax) {
            return ResponseEntity.badRequest().build();
        }

        List<PetResponseDto> pets = petService.findByFilters(especie, raca, idadeMin, idadeMax);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto<>(pets));
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<ResponseDto<PetResponseDto>> deactivatePet(@PathVariable int id) {
        PetResponseDto petInativado = petService.inativarPet(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto<>(petInativado));
    }
    
    @PatchMapping("/adotado/{id}")
    public ResponseEntity<ResponseDto<PetResponseDto>> adopted_Pet(@PathVariable int id) {
        PetResponseDto petAdotado = petService.adotedPet(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto<>(petAdotado));
    }
}
