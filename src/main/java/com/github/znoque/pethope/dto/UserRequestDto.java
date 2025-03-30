package com.github.znoque.pethope.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequestDto(@Email(message = "E-mail inválido")
                             @NotBlank(message = "E-mail não pode ser vazio")
                             String email,

                             @NotBlank(message = "Senha não pode ser vazia")
                             @Size(min = 6, max = 255, message = "A senha deve ter no mínimo 6 e no máximo 255 caracteres")
                             String password) {
}
