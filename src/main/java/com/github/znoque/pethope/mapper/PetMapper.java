package com.github.znoque.pethope.mapper;

import com.github.znoque.pethope.dto.pet.PetRequestDto;
import com.github.znoque.pethope.dto.pet.PetResponseDto;
import com.github.znoque.pethope.model.Pet;
import org.springframework.stereotype.Component;

@Component
public class PetMapper {

    public PetResponseDto toPetResponseDto(Pet persistedPet) {
        return new PetResponseDto(
                String.valueOf(persistedPet.getId()),
                persistedPet.getNome(),
                persistedPet.getDescricao(),
                persistedPet.getEspecie(),
                persistedPet.getRaca(),
                persistedPet.getIdade(),
                persistedPet.getSexo(),
                persistedPet.getTemperamento(),
                persistedPet.isAtivo(),
                persistedPet.isDisponivel(),
                persistedPet.getUsuario()
        );
    }

    public Pet toPet(PetRequestDto petRequestDto) {
        return Pet.builder()
                .comNome(petRequestDto.nome())
                .comDescricao(petRequestDto.descricao())
                .comEspecie(petRequestDto.especie())
                .comIdade(petRequestDto.idade())
                .comRaca(petRequestDto.raca())
                .comSexo(petRequestDto.sexo())
                .comTemperamento(petRequestDto.temperamento())
                .estaAtivo(petRequestDto.ativo())
                .estaDisponivel(petRequestDto.disponivel())
                .comUsuario(petRequestDto.usuario())
                .build();
    }
}
