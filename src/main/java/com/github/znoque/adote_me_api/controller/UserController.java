package com.github.znoque.adote_me_api.controller;


import com.github.znoque.adote_me_api.config.SwaggerDocumentacionConfig;
import com.github.znoque.adote_me_api.dto.UserDto;
import com.github.znoque.adote_me_api.model.auth.Auth;
import com.github.znoque.adote_me_api.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("auth")
@Tag(name = SwaggerDocumentacionConfig.TAG_USER)
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
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
    public ResponseEntity<?> createUser(@RequestBody @Valid UserDto data) {

        try {
            Auth auth = userService.saveUser(data);
            return ResponseEntity.status(HttpStatus.CREATED).body(auth);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
        } catch (Exception e) {
            logger.error("Ocorreu um erro inesperado", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
