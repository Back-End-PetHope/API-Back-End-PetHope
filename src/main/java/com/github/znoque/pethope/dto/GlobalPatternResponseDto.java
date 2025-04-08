package com.github.znoque.pethope.dto;

public record GlobalPatternResponseDto<T>(String message, int statusCode, T data) {
}
