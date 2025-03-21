package com.github.znoque.pethope.repository;

import com.github.znoque.pethope.model.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Auth, Integer> {

    Auth findByEmail(String email);

    boolean findByIdUser(String id);
}
