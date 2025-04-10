package com.github.znoque.pethope.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public record UserUpdateRequestDto(

    @CPF @Size(message = "Cpf deve conter até 14 digitos") String cpf,

    String responsavelNome,

    String username,

    @Size(message = "Telefone deve conter até 14 digitos") String telefone,

    @Size(message = "Cidade deve conter até 150 caracteres") String cidade,

    @Size(message = "Logradouro deve conter até 150 caracteres") String endereco,

    @Email(message = "E-mail inválido") String email,

    @Size(min = 6, max = 255, message = "A senha deve ter no mínimo 6 e no máximo 255 caracteres") String password)

{
}