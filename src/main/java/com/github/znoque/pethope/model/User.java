package com.github.znoque.pethope.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.UUID;

@Entity
@Table(name = "usuario")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "usuario_id", updatable = false, nullable = false)
    private final UUID id;

    @Email
    @NotBlank
    @Size(min = 11, max = 155)
    @Column(name = "usuario_email", length = 155, nullable = false, unique = true)
    private final String email;

    @NotBlank
    @Size(min = 6, max = 255)
    @Column(name = "usuario_password", nullable = false)
    private final String password;

    public User(String email, String userPassword) {
        this.id = UUID.randomUUID();
        this.email = email;
        this.password = userPassword;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Auth{" +
                "id=" + id +
                ", email='" + email + '\'' +
                '}';
    }
}
