package com.github.znoque.pethope.enums;

import java.util.Arrays;

public enum Sexo {
    M("Macho"),
    F("Fêmea");

    private final String displayName;

    Sexo(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Sexo fromDisplayName(String displayName) {
        return Arrays.stream(Sexo.values())
                .filter(s -> s.getDisplayName().equalsIgnoreCase(displayName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Sexo inválido."));
    }

}
