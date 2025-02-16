package com.github.znoque.adote_me_api.repository;

import com.github.znoque.adote_me_api.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);

    boolean findByIdUser(String id);
}
