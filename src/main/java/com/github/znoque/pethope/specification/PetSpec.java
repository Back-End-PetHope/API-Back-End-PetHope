package com.github.znoque.pethope.specification;

import com.github.znoque.pethope.Enum.Especie;
import com.github.znoque.pethope.model.pet.Pet;
import com.github.znoque.pethope.Enum.Raca;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;

import java.util.ArrayList;
import java.util.List;

public class PetSpec {

    public static Specification<Pet> filters (String especieStr, String racaStr, Integer idadeMin, Integer idadeMax) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (especieStr != null && !especieStr.isBlank()) {
                Especie especie = Especie.fromDisplayName(especieStr);
                predicates.add(cb.equal(root.get("especie"), especie));
            }

            if (racaStr != null && !racaStr.isBlank()) {
                Raca raca = Raca.fromDisplayName(racaStr);
                predicates.add(cb.equal(root.get("raca"), raca));
            }

            if (idadeMin != null && idadeMax != null) {
                predicates.add(cb.between(root.get("idade"), idadeMin, idadeMax));
            } else if (idadeMin != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("idade"), idadeMin));
            } else if (idadeMax != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("idade"), idadeMax));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
