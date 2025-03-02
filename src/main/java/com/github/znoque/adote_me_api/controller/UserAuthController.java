package com.github.znoque.adote_me_api.controller;


import com.github.znoque.adote_me_api.dto.UserDto;
import com.github.znoque.adote_me_api.model.user.User;
import com.github.znoque.adote_me_api.services.AuthService;
import com.github.znoque.adote_me_api.services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.io.IOException;

@RestController
@RequestMapping("auth")
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

    @GetMapping("/social")
    public void loginSocial(HttpServletResponse response) throws IOException {
        response.sendRedirect("/auth/login/oauth2/code/google");
    }
// http://localhost:8080
//    @GetMapping("/social")
//    public ResponseEntity<String> loginSocial() {
//        return ResponseEntity.ok("Redirecione manualmente para: http://localhost:8080/auth/login/oauth2/code/google");
//    }



}
