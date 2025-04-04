package com.github.znoque.pethope.dto.clinica;

import com.github.znoque.pethope.enums.UsuarioTipo;

public record ClinicaOrOngResponseDto(String razaoSocial, String email, UsuarioTipo tipo) {
}
