package com.github.znoque.adote_me_api.controller;


import com.github.znoque.adote_me_api.dto.UserDto;
import com.github.znoque.adote_me_api.model.user.User;
import com.github.znoque.adote_me_api.services.AuthService;
import com.github.znoque.adote_me_api.services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.io.IOException;
import java.util.Map;

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
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidor "+e.getMessage());
        }

    }

    @GetMapping("/user-google-info")
    public Map<String, Object> userInfo(@AuthenticationPrincipal OAuth2User principal) {
        return principal.getAttributes();
    }

    @GetMapping("/redirect-to-google")
    public void redirectLink(HttpServletResponse response) throws IOException {
        response.sendRedirect("/oauth2/authorization/google");
    }

}
