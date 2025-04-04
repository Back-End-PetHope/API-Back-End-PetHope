package com.github.znoque.pethope.dto;

public record GlobalResponseDto<T>(String message, int statusCode, T data) {
}
