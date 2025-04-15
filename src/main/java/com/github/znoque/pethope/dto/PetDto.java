package com.github.znoque.pethope.dto;

import com.github.znoque.pethope.Enum.Especie;
import com.github.znoque.pethope.Enum.Raca;
import com.github.znoque.pethope.Enum.Sexo;
import com.github.znoque.pethope.Enum.Temperamento;

public record PetDto(
        String nome,
        String descricao,
        Especie especie,
        Raca raca,
        int idade,
        Sexo sexo,
        Temperamento temperamento,
        boolean ativo,
        boolean disponibilidade) {

}
