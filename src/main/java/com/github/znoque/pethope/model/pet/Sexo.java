package com.github.znoque.pethope.model.pet;

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
}
