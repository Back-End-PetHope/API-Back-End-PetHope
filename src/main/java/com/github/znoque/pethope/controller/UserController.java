package com.github.znoque.pethope.controller;


import com.github.znoque.pethope.config.SwaggerDocumentationConfig;
import com.github.znoque.pethope.dto.UserRequestDto;
import com.github.znoque.pethope.dto.UserResponseDto;
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
        UserResponseDto userResponseDto = new UserResponseDto(user.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDto);
    }

    @PostMapping("/login")
    @Operation(summary = SwaggerDocumentationConfig.SUMARIO_LOGIN, description = SwaggerDocumentationConfig.DESCRICAO_LOGIN)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = SwaggerDocumentationConfig.RESPONSE_200),
            @ApiResponse(responseCode = "404", description = SwaggerDocumentationConfig.RESPONSE_404),
            @ApiResponse(responseCode = "500", description = SwaggerDocumentationConfig.RESPONSE_500)
    })
    public ResponseEntity<?> loginUser(@RequestBody @Valid UserRequestDto data) {
        userService.authenticate(data);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
