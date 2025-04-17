package com.github.znoque.pethope.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public record UserRequestDto(

        @CPF(message = "CPF deve ser válido")

        String cpf,

        @NotBlank(message = "Nome não pode ser vazio")
        String responsavelNome,

        @NotBlank(message = "Telefone não pode ser vazio")
        @Size(max = 14, message = "Telefone deve conter até 14 dígitos")
        String telefone,

        @NotBlank(message = "Cidade não pode ser vazio")
        @Size(max = 150, message = "Cidade deve conter até 150 caracteres")
        String cidade,

        @NotBlank(message = "Logradouro não pode ser vazio")
        @Size(max = 150, message = "Logradouro deve conter até 150 caracteres")
        String endereco,

        @Email(message = "E-mail inválido")
        @NotBlank(message = "E-mail não pode ser vazio")
        String email,

        @NotBlank(message = "Senha não pode ser vazia")
        @Size(min = 6, max = 255, message = "A senha deve ter no mínimo 6 e no máximo 255 caracteres")
        String password
) {}
