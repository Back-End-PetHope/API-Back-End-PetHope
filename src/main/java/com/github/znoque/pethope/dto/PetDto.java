package com.github.znoque.pethope.dto;

import com.github.znoque.pethope.model.pet.Especie;

public record PetDto(
        String nome,
        Especie especie,
        int idade,
        String descricao,
        boolean ativo,
        boolean disponibilidade) {

}
