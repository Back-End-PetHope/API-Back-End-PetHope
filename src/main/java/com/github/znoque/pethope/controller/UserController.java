package com.github.znoque.pethope.controller;


import com.github.znoque.pethope.config.SwaggerDocumentationConfig;
import com.github.znoque.pethope.dto.clinica.ClinicaOrOngRequestDto;
import com.github.znoque.pethope.dto.clinica.ClinicaOrOngResponseDto;
import com.github.znoque.pethope.dto.GlobalResponseDto;
import com.github.znoque.pethope.dto.user.AuthResponseDto;
import com.github.znoque.pethope.dto.user.AuthResquestDto;
import com.github.znoque.pethope.dto.user.UserRequestDto;
import com.github.znoque.pethope.dto.user.UserResponseDto;
import com.github.znoque.pethope.model.User;
import com.github.znoque.pethope.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Tag(name = SwaggerDocumentationConfig.TAG_USER)
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    @Operation(
            summary = SwaggerDocumentationConfig.SUMARIO_USER,
            description = SwaggerDocumentationConfig.DESCRICAO_USER
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = SwaggerDocumentationConfig.RESPONSE_201),
            @ApiResponse(responseCode = "422", description = SwaggerDocumentationConfig.RESPONSE_422),
            @ApiResponse(responseCode = "500", description = SwaggerDocumentationConfig.RESPONSE_500)
    })
    public ResponseEntity<?> createUser(@RequestBody @Valid UserRequestDto data) {
        User user = userService.saveUser(data);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new GlobalResponseDto<>(HttpStatus.CREATED.getReasonPhrase(),
                        HttpStatus.CREATED.value(),
                        new UserResponseDto(user.getResponsavelNome(),user.getEmail(),user.getTipo())));
    }

    //Documentar ainda
    @PostMapping("/create/ClinicaOrOng")
    public ResponseEntity<?> createClinicaOrOng(@RequestBody @Valid ClinicaOrOngRequestDto data) {
        User user = userService.saveClinicaOrOng(data);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new GlobalResponseDto<>(HttpStatus.CREATED.getReasonPhrase(),
                        HttpStatus.CREATED.value(),
                        new ClinicaOrOngResponseDto(user.getRazaoSocial(),user.getEmail(),user.getTipo())));
    }

    @PostMapping("/login")
    @Operation(summary = SwaggerDocumentationConfig.SUMARIO_LOGIN, description = SwaggerDocumentationConfig.DESCRICAO_LOGIN)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = SwaggerDocumentationConfig.RESPONSE_200),
            @ApiResponse(responseCode = "404", description = SwaggerDocumentationConfig.RESPONSE_404),
            @ApiResponse(responseCode = "500", description = SwaggerDocumentationConfig.RESPONSE_500)
    })
    public ResponseEntity<?> loginUser(@RequestBody @Valid AuthResquestDto data) {
        AuthResquestDto user = userService.authenticate(data);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new GlobalResponseDto<>(HttpStatus.OK.getReasonPhrase(),
                        HttpStatus.OK.value(),
                        new AuthResponseDto(user.password())));
    }


}
