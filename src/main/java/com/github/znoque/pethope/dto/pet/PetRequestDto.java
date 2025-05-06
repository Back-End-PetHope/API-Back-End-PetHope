package com.github.znoque.pethope.dto.pet;

import com.github.znoque.pethope.enums.Especie;
import com.github.znoque.pethope.enums.Raca;
import com.github.znoque.pethope.enums.Sexo;
import com.github.znoque.pethope.enums.Temperamento;
import com.github.znoque.pethope.model.User;
import jakarta.validation.constraints.*;

public record PetRequestDto(
        @NotBlank(message = "O nome não pode ser vazio")
        @Size(max = 50, message = "O nome deve conter no máximo 50 dígitos")
        String nome,

        @NotBlank(message = "A descrição não pode ser vazia")
        @Size(max = 255, message = "A descrição deve conter no máximo 255 caracteres")
        String descricao,

        @NotNull(message = "A espécie não pode ser nula")
        Especie especie,

        @NotNull(message = "A raça não pode ser nula")
        Raca raca,

        @Positive(message = "A idade do pet deve ser um número positivo")
        @Min(value = 0, message = "A idade não pode ser negativa")
        @Max(value = 30, message = "A idade não pode ser maior que 30 anos")
        int idade,

        @NotNull(message = "O sexo não pode ser nulo")
        Sexo sexo,

        @NotNull(message = "O temperamento não pode ser nulo")
        Temperamento temperamento,

        boolean ativo,

        boolean disponivel,

        User usuario
) {
}
