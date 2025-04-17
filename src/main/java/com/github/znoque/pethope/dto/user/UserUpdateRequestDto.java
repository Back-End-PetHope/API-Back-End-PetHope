package com.github.znoque.pethope.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public record UserUpdateRequestDto(

        @CPF(message = "CPF deve ser válido")
        String cpf,

        @NotBlank(message = "Nome responsável não pode estar vazio")
        String responsavelNome,

        @Email(message = "E-mail inválido")
        @NotBlank(message = "Email não pode estar vazio")
        String email,

        @Size(max = 14, message = "Telefone deve conter até 14 dígitos")
        @NotBlank(message = "Telefone não pode estar vazio")
        String telefone,

        @Size(max = 150, message = "Cidade deve conter até 150 caracteres")
        @NotBlank(message = "Cidade não pode estar vazia")
        String cidade,

        @Size(max = 150, message = "Logradouro deve conter até 150 caracteres")
        @NotBlank(message = "Endereço não pode estar vazio")
        String endereco,

        @Size(min = 6, max = 255, message = "A senha deve ter no mínimo 6 e no máximo 255 caracteres")
        @NotBlank(message = "Senha não pode estar vazia")
        String password
) {}
