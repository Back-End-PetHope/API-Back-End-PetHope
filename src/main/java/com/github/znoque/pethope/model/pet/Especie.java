package com.github.znoque.pethope.model.pet;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum Especie {
    CACHORRO("Cachorro"),
    GATO("Gato"),
    COELHO("Coelho"),
    PASSARO("Pássaro");

    // Nome formatado da espécie
    private final String displayName;

    Especie (String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Especie fromDisplayName(String displayName) {
        return Arrays.stream(Especie.values())
                .filter(e -> e.getDisplayName().equalsIgnoreCase(displayName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Espécie inválida."));
    }

}
