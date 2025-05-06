package com.github.znoque.pethope.mapper;

import com.github.znoque.pethope.dto.pet.PetRequestDto;
import com.github.znoque.pethope.dto.pet.PetResponseDto;
import com.github.znoque.pethope.dto.user.UserResponseDto;
import com.github.znoque.pethope.model.Pet;
import com.github.znoque.pethope.model.User;
import org.springframework.stereotype.Component;

@Component
public class PetMapper {

    private final UserMapper userMapper;

    public PetMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public PetResponseDto toPetResponseDto(Pet persistedPet) {
        UserResponseDto userResponseDto = userMapper.toUserResponseDto(persistedPet.getUsuario());

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
                userResponseDto
        );
    }

    public Pet toPet(PetRequestDto petRequestDto, User usuarioLogado) {
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
                .comUsuario(usuarioLogado)
                .build();
    }
}
