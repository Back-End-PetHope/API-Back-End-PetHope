package com.github.znoque.pethope.dto.clinica;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CNPJ;

public record ClinicaUpdateRequestDto(
    @CNPJ @Size(message = "Cpf deve conter até 14 digitos") String cnpj,

    String responsavelNome,
        
    Boolean isPrestadorServico,
        
    @Size(message = "Razao Social deve conter até 150 caracteres") String razaoSocial,
        
    @Size(message = "Site deve conter até 150 caracteres") String site,
        
    @Size(message = "UrlFacebook deve conter até 150 caracteres") String urlFacebook,
        
    @Size(message = "UrlInstagram deve conter até 150 caracteres") String urlInstagram,

    @Email(message = "E-mail inválido") String email,

    @Size(message = "Telefone deve conter até 14 digitos") String telefone,

    @Size(message = "Cidade deve conter até 150 caracteres") String cidade,

    @Size(message = "Logradouro deve conter até 150 caracteres") String endereco,

    @Size(min = 6, max = 255, message = "A senha deve ter no mínimo 6 e no máximo 255 caracteres") String senha) {
}
