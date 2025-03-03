package com.github.znoque.adote_me_api.controller;


import com.github.znoque.adote_me_api.config.SwaggerDocumentacionConfig;
import com.github.znoque.adote_me_api.dto.UserDto;
import com.github.znoque.adote_me_api.model.user.User;
import com.github.znoque.adote_me_api.services.AuthService;
import com.github.znoque.adote_me_api.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = SwaggerDocumentacionConfig.TAG_AUTH)
public class UserAuthController {


    private final AuthService authService;

    public UserAuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    @Operation(summary = SwaggerDocumentacionConfig.SUMARIO_LOGIN, description = SwaggerDocumentacionConfig.DESCRICAO_LOGIN)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = SwaggerDocumentacionConfig.RESPONSE_200),
            @ApiResponse(responseCode = "404", description = SwaggerDocumentacionConfig.RESPONSE_404),
            @ApiResponse(responseCode = "500", description = SwaggerDocumentacionConfig.RESPONSE_500)
    })
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
    @Operation(summary = SwaggerDocumentacionConfig.SUMARIO_GOOGLE_INFO, description = SwaggerDocumentacionConfig.DESCRICAO_GOOGLE_INFO)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = SwaggerDocumentacionConfig.RESPONSE_200),
            @ApiResponse(responseCode = "500", description = SwaggerDocumentacionConfig.RESPONSE_500)
    })
    public Map<String, Object> userInfo(@AuthenticationPrincipal OAuth2User principal) {
        return principal.getAttributes();
    }

    @GetMapping("/redirect-to-google")
    @Operation(summary = SwaggerDocumentacionConfig.SUMARIO_GOOGLE_REDIRECT, description = SwaggerDocumentacionConfig.DESCRICAO_GOOGLE_REDIRECT)
    public void redirectLink(HttpServletResponse response) throws IOException {
        response.sendRedirect("/oauth2/authorization/google");
    }

}
