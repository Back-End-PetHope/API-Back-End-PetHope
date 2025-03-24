package com.github.znoque.pethope.repository;

import com.github.znoque.pethope.model.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<Auth,String> {

    Auth findByEmail(String email);
}
