package com.github.znoque.pethope.dto.clinica;

import com.github.znoque.pethope.enums.UsuarioTipo;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ClinicaOrOngRequestDto(

                                @NotBlank(message = "CNPJ não pode ser vazio")
                                @Size(message = "CNPJ deve conter até 14 digitos")
                                String cnpj,

                                @NotBlank(message = "Nome do Proprietário não pode ser vazio")
                                String responsavelNome,

                                @NotBlank(message = "Telefone não pode ser vazio")
                                @Size(message = "Telefone deve conter até 14 digitos")
                                String telefone,

                                @NotBlank(message = "Cidade não pode ser vazio")
                                @Size(message = "Cidade deve conter até 150 caracteres")
                                String cidade,

                                @NotBlank(message = "Logradouro não pode ser vazio")
                                @Size(message = "Logradouro deve conter até 150 caracteres")
                                String endereco,

                                @NotBlank(message = "Razao Social não pode ser vazio")
                                String razaoSocial,

                                @Email(message = "E-mail inválido")
                                @NotBlank(message = "E-mail não pode ser vazio")
                                String email,

                                @NotBlank(message = "Senha não pode ser vazia")
                                @Size(min = 6, max = 255, message = "A senha deve ter no mínimo 6 e no máximo 255 caracteres")
                                String senha,

                                @NotBlank(message = "Site não pode ser vazio")
                                @Size(message = "Site deve conter até 150 caracteres")
                                String site,

                                @NotBlank(message = "UrlFacebook não pode ser vazio")
                                @Size(message = "UrlFacebook deve conter até 150 caracteres")
                                String urlFacebook,

                                @NotBlank(message = "UrlInstagram não pode ser vazio")
                                @Size(message = "UrlInstagram deve conter até 150 caracteres")
                                String urlInstagram,

                                @Enumerated(EnumType.STRING)
                                UsuarioTipo tipo,

                                boolean isPrestadorServico) {
}
