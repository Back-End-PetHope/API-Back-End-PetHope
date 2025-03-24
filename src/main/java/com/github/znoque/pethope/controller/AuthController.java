package com.github.znoque.pethope.controller;


import com.github.znoque.pethope.config.SwaggerDocumentacionConfig;
import com.github.znoque.pethope.dto.AuthDto;
import com.github.znoque.pethope.model.Auth;
import com.github.znoque.pethope.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("auth")
@Tag(name = SwaggerDocumentacionConfig.TAG_AUTH)
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/create")
    @Operation(
            summary = SwaggerDocumentacionConfig.SUMARIO_USER,
            description = SwaggerDocumentacionConfig.DESCRICAO_USER
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201",description = SwaggerDocumentacionConfig.RESPONSE_201),
            @ApiResponse(responseCode = "422",description = SwaggerDocumentacionConfig.RESPONSE_422),
            @ApiResponse(responseCode = "500",description = SwaggerDocumentacionConfig.RESPONSE_500)
    })
    public ResponseEntity<?> createUser(@RequestBody @Valid AuthDto data) {

        try {
            Auth auth = authService.saveUser(data);
            return ResponseEntity.status(HttpStatus.CREATED).body(auth);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
        } catch (Exception e) {
            logger.error("Ocorreu um erro inesperado", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    @Operation(summary = SwaggerDocumentacionConfig.SUMARIO_LOGIN, description = SwaggerDocumentacionConfig.DESCRICAO_LOGIN)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = SwaggerDocumentacionConfig.RESPONSE_200),
            @ApiResponse(responseCode = "404", description = SwaggerDocumentacionConfig.RESPONSE_404),
            @ApiResponse(responseCode = "500", description = SwaggerDocumentacionConfig.RESPONSE_500)
    })
    public ResponseEntity<?> loginUser (@RequestBody @Valid AuthDto data) {

        try {
            AuthDto userLogin = authService.authenticate(data);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidor "+e.getMessage());
        }

    }

    @GetMapping("/auth-google-info")
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
        response.sendRedirect("/api/oauth2/authorization/google");
    }

}
