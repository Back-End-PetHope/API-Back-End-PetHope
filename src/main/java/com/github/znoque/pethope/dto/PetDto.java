package com.github.znoque.pethope.dto;

import com.github.znoque.pethope.model.pet.Especie;
import com.github.znoque.pethope.model.pet.Raca;
import com.github.znoque.pethope.model.pet.Sexo;

public record PetDto(
        String nome,
        String descricao,
        Especie especie,
        Raca raca,
        int idade,
        Sexo sexo,
        boolean ativo,
        boolean disponibilidade) {

}
