package com.github.znoque.adote_me_api.controller;


import com.github.znoque.adote_me_api.dto.UserDto;
import com.github.znoque.adote_me_api.model.user.User;
import com.github.znoque.adote_me_api.services.AuthService;
import com.github.znoque.adote_me_api.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("user-auth")
public class UserAuthController {


    private final AuthService authService;

    public UserAuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser (@RequestBody @Valid UserDto data) {

        try {
            UserDto userLogin = authService.authenticate(data);
            return ResponseEntity.status(HttpStatus.OK).body(userLogin);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidor"+e.getMessage());
        }

    }

    @PostMapping("/login/social")
    public ResponseEntity<?> loginSocial() {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
