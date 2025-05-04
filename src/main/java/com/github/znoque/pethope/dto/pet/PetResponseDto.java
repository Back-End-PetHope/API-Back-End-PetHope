package com.github.znoque.pethope.dto.pet;

import com.github.znoque.pethope.enums.Especie;
import com.github.znoque.pethope.enums.Raca;
import com.github.znoque.pethope.enums.Sexo;
import com.github.znoque.pethope.enums.Temperamento;
import com.github.znoque.pethope.model.User;

public record PetResponseDto(
        String id,
        String nome,
        String descricao,
        Especie especie,
        Raca raca,
        int idade,
        Sexo sexo,
        Temperamento temperamento,
        boolean ativo,
        boolean disponivel,
        User usuario
) {
}
