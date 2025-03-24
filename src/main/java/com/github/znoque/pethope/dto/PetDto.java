package com.github.znoque.pethope.dto;

public record PetDto(
        String nome,
        String descricao,
        Especie especie,
        int idade,
        Porte porte,
        String temperamento,
        String doenca,
        String necessidadeEspecifica,
        boolean ativo,
        boolean disponibilidade) {

}
