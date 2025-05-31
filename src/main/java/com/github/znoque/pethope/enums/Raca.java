package com.github.znoque.pethope.enums;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.List;

public enum Raca {
    PASTOR_ALEMAO("Pastor Alemão", Especie.CACHORRO),
    LABRADOR("Labrador", Especie.CACHORRO),
    CAO_SRD("Cachorro SRD", Especie.CACHORRO),
    POODLE("Poodle", Especie.CACHORRO),
    GOLDEN_RETRIEVER("Golden Retriver", Especie.CACHORRO),
    ROTTWEILER("Rottweiler", Especie.CACHORRO),

    PERSA("Persa", Especie.GATO),
    SIAMES("Siamês", Especie.GATO),
    ANGORA("Angorá", Especie.GATO),
    GATO_SRD("Gato SRD", Especie.GATO),

    CALOPSITA("Calopsita", Especie.PASSARO),
    PERIQUITO("Periquito", Especie.PASSARO);

    private final Especie especie;
    private final String displayName;

    Raca(String displayName, Especie especie) {
        this.displayName = displayName;
        this.especie = especie;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Especie getEspecie() {
        return especie;
    }

    public static Raca fromDisplayName(String displayName) {
        return Arrays.stream(Raca.values())
                .filter(r -> r.getDisplayName().equalsIgnoreCase(displayName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Raça inválida."));
    }


    public static List<Raca> getRacasByEspecie(Especie especie) {
        return Arrays.stream(values())
                .filter(r -> r.getEspecie() == especie)
                .collect(Collectors.toList());
    }
}
