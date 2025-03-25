package com.github.znoque.pethope.dto;

import com.github.znoque.pethope.model.pet.Especie;
import com.github.znoque.pethope.model.pet.Porte;

public record PetDto(
        String nome,
        Especie especie,
        int idade,
        Porte porte,
        String temperamento,
        String doenca,
        String necessidadeEspecifica,
        boolean ativo,
        boolean disponibilidade) {

}
