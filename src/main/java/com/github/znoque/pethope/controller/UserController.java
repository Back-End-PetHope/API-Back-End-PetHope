package com.github.znoque.pethope.controller;


import com.github.znoque.pethope.config.UserApi;
import com.github.znoque.pethope.dto.GlobalResponseDto;
import com.github.znoque.pethope.dto.clinica.ClinicaRequestDto;
import com.github.znoque.pethope.dto.GlobalPatternResponseDto;
import com.github.znoque.pethope.dto.ong.OngRequestDto;
import com.github.znoque.pethope.dto.user.AuthResponseDto;
import com.github.znoque.pethope.dto.user.AuthResquestDto;
import com.github.znoque.pethope.dto.user.UserRequestDto;
import com.github.znoque.pethope.model.User;
import com.github.znoque.pethope.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/users")
@RestController

public class UserController implements UserApi {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    @Override
    public ResponseEntity<GlobalPatternResponseDto<List<GlobalResponseDto>>> findAllUser(){
        return Optional.ofNullable(userService.listAllUser())
                .filter(list ->!list.isEmpty())
                .map(listaUser -> ResponseEntity.status(HttpStatus.OK)
                        .body(new GlobalPatternResponseDto<>(HttpStatus.OK.getReasonPhrase(),
                                listaUser)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new GlobalPatternResponseDto<>(
                                HttpStatus.NOT_FOUND.getReasonPhrase(),
                                null)));
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<GlobalPatternResponseDto<User>> findByIdUser(@PathVariable @Valid String id){
        return userService.listByIdUser(id)
                .map(result -> ResponseEntity.status(HttpStatus.OK).body(new GlobalPatternResponseDto<>(
                        HttpStatus.OK.getReasonPhrase(),
                        result)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping()
    @Override
    public ResponseEntity<?> createUser(@RequestBody @Valid UserRequestDto data) {
        User user = userService.saveUser(data);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new GlobalPatternResponseDto<>(HttpStatus.CREATED.getReasonPhrase(),
                        userService.toUserResponseDto(user)));
    }

    @PostMapping("/clinica")
    @Override
    public ResponseEntity<?> createClinica(@RequestBody @Valid ClinicaRequestDto data) {
        User user = userService.saveClinica(data);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new GlobalPatternResponseDto<>(HttpStatus.CREATED.getReasonPhrase(),
                        userService.toGlocalResponseDto(user)));
    }

    @PostMapping("/ong")
    @Override
    public ResponseEntity<?> createOng(@RequestBody @Valid OngRequestDto data) {
        User user = userService.saveOng(data);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new GlobalPatternResponseDto<>(HttpStatus.CREATED.getReasonPhrase(),
                        userService.toGlocalResponseDto(user)));
    }

    @PostMapping("/login")
    @Override
    public ResponseEntity<?> loginUser(@RequestBody @Valid AuthResquestDto data) {
        String token = userService.authenticate(data);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new GlobalPatternResponseDto<>(HttpStatus.OK.getReasonPhrase(),
                        new AuthResponseDto(token)));
    }

}
