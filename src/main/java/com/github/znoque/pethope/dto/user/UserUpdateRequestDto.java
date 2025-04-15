package com.github.znoque.pethope.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public record UserUpdateRequestDto(

        @CPF
        @Size(min = 14, max = 14, message = "CPF deve conter exatamente 14 dígitos")
        String cpf,

        String responsavelNome,

        @Email(message = "E-mail inválido")
        String email,

        @Size(max = 14, message = "Telefone deve conter até 14 dígitos")
        String telefone,

        @Size(max = 150, message = "Cidade deve conter até 150 caracteres")
        String cidade,

        @Size(max = 150, message = "Logradouro deve conter até 150 caracteres")
        String endereco,

        @Size(min = 6, max = 255, message = "A senha deve ter no mínimo 6 e no máximo 255 caracteres")
        String password
) {}
