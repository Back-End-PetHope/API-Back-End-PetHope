package com.github.znoque.pethope.dto.user;

import com.github.znoque.pethope.enums.UsuarioTipo;

public record UserResponseDto(String nome, String email, UsuarioTipo tipo) { }
