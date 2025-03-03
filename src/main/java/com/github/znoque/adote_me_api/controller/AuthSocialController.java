package com.github.znoque.adote_me_api.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
@RequestMapping("social")
public class AuthSocialController {


    @GetMapping
    public void loginSocial(HttpServletResponse response) throws IOException {
        response.sendRedirect("/auth/login/oauth2/code/google");
    }
// http://localhost:8080
//    @GetMapping("/social")
//    public ResponseEntity<String> loginSocial() {
//        return ResponseEntity.ok("Redirecione manualmente para: http://localhost:8080/auth/login/oauth2/code/google");
//    }
}
