package com.github.znoque.pethope.Enum;

import java.util.Arrays;

public enum Temperamento {
    TRANQUILO("Tranquilo"),
    AGITADO("Agitado"),
    BRINCALHAO("Brincalhão"),
    TIMIDO("Tímido"),
    CURIOSO("Curioso"),
    PROTETOR("Protetor"),
    CARINHOSO("Carinhoso"),
    INDEPENDENTE("Independente"),
    SOCIAVEL("Sociável"),
    OBEDIENTE("Obediente"),
    ARREDIO("Arredio");

    private final String displayName;

    Temperamento(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Temperamento fromDisplayName(String displayName) {
        return Arrays.stream(Temperamento.values())
                .filter(t -> t.getDisplayName().equalsIgnoreCase(displayName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Temperamento inválido."));
    }

}
