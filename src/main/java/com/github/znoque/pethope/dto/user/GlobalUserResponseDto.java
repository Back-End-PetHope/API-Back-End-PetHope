package com.github.znoque.pethope.dto.user;

import com.github.znoque.pethope.enums.UsuarioTipo;

import java.util.List;

public record GlobalUserResponseDto(
    String id,
    String email,
    UsuarioTipo tipo,
    String cpfCnpj,
    String razaoSocial,
    String responsavelNome,
    String telefone,
    String logradouro,
    String cidade,
    String site,
    String urlInstagram,
    String urlFacebook,
    boolean prestadorServico,
    List<String> authorities) {
}
