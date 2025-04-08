package com.github.znoque.pethope.dto.user;

import com.github.znoque.pethope.enums.UsuarioTipo;

import java.util.List;

public record UserResponseDto(String id,
                              String username,
                              UsuarioTipo tipo,
                              String cpfCnpj,
                              String responsavelNome,
                              String telefone,
                              String logradouro,
                              String cidade,
                              boolean prestadorServico,
                              List<String> authorities) {
}
