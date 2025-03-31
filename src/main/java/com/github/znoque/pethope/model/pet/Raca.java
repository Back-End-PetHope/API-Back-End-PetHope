package com.github.znoque.pethope.model.pet;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.List;

public enum Raca {
    // Cachorro
    PASTOR_ALEMAO(Especie.CACHORRO),
    LABRADOR(Especie.CACHORRO),
    CAO_SRD(Especie.CACHORRO),
    POODLE(Especie.CACHORRO),
    GOLDEN_RETRIEVER(Especie.CACHORRO),
    ROTTWEILER(Especie.CACHORRO),

    // Gato
    PERSA(Especie.GATO),
    SIAMES(Especie.GATO),
    ANGORA(Especie.GATO),
    GATO_SRD(Especie.GATO),

    // Pássaro
    CALOPSITA(Especie.PASSARO),
    PERIQUITO(Especie.PASSARO);

    private final Especie especie;

    Raca(Especie especie) {
        this.especie = especie;
    }

    public Especie getEspecie() {
        return especie;
    }

    public static List<Raca> getRacasByEspecie(Especie especie) {
        return Arrays.stream(values()).filter(r -> r.getEspecie() == especie)
                .collect(Collectors.toList());
    }
}
