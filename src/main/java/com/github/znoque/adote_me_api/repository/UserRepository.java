package com.github.znoque.adote_me_api.repository;

import com.github.znoque.adote_me_api.model.auth.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Auth, Integer> {

    Auth findByEmail(String email);

    boolean findByIdUser(String id);
}
