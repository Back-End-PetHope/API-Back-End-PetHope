package com.github.znoque.pethope.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_auth")
public class Auth {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_auth")
    private String idUser;

    @Email
    @NotNull
    @NotBlank
    @Column(name = "email_auth",length = 155,nullable = false)
    @Size(min = 11, max = 155)
    private String email;

    @NotNull
    @NotBlank
    @Column(name = "password_auth",nullable = false)
    @Size(min = 6, max = 255)
    private String password;

    public String getId() {
        return idUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
