package com.github.znoque.pethope.dto.user.ong;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CNPJ;

public record OngUpdateRequestDto(

        @CNPJ(message = "CNPJ deve ser válido")
        String cnpj,

        String responsavelNome,

        Boolean isPrestadorServico,

        @Size(max = 150, message = "Razão Social deve conter até 150 caracteres")
        String razaoSocial,

        @Size(max = 150, message = "Site deve conter até 150 caracteres")
        String site,

        @Size(max = 150, message = "URL do Facebook deve conter até 150 caracteres")
        String urlFacebook,

        @Size(max = 150, message = "URL do Instagram deve conter até 150 caracteres")
        String urlInstagram,

        @Email(message = "E-mail inválido")
        String email,

        @Size(max = 14, message = "Telefone deve conter até 14 dígitos")
        String telefone,

        @Size(max = 150, message = "Cidade deve conter até 150 caracteres")
        String cidade,

        @Size(max = 150, message = "Logradouro deve conter até 150 caracteres")
        String endereco,

        @Size(min = 6, max = 255, message = "A senha deve ter no mínimo 6 e no máximo 255 caracteres")
        String senha
) {}
