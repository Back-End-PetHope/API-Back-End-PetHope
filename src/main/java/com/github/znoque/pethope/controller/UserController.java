package com.github.znoque.pethope.controller;


import com.github.znoque.pethope.docs.UserApi;
import com.github.znoque.pethope.dto.user.GlobalUserResponseDto;
import com.github.znoque.pethope.dto.ResponseDto;
import com.github.znoque.pethope.dto.user.clinica.ClinicaRequestDto;
import com.github.znoque.pethope.dto.user.ong.OngRequestDto;
import com.github.znoque.pethope.dto.user.*;

import com.github.znoque.pethope.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/users")
@RestController
public class UserController implements UserApi {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    @GetMapping
    public ResponseEntity<ResponseDto<List<GlobalUserResponseDto>>> getAll() {
        List<GlobalUserResponseDto> globalUserResponseDtos = userService.listAllUser();
        return ResponseEntity.ok(new ResponseDto<>(globalUserResponseDtos));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<UserResponseDto>> getUserById(@PathVariable @Valid String id) {
        UserResponseDto userResponseDto = userService.findUserById(id);
        return ResponseEntity.ok(new ResponseDto<>(userResponseDto));
    }

    @Override
    @GetMapping("/clinica/{id}")
    public ResponseEntity<ResponseDto<GlobalUserResponseDto>> getClinicaById(@PathVariable @Valid String id) {
        GlobalUserResponseDto clinicaResponseDto = userService.findClinicaById(id);
        return ResponseEntity.ok(new ResponseDto<>(clinicaResponseDto));
    }

    @Override
    @GetMapping("/ong/{id}")
    public ResponseEntity<ResponseDto<GlobalUserResponseDto>> getOngById(@PathVariable @Valid String id) {
        GlobalUserResponseDto ongResponseDto = userService.findOngById(id);
        return ResponseEntity.ok(new ResponseDto<>(ongResponseDto));
    }

    @Override
    @PostMapping()
    public ResponseEntity<ResponseDto<UserResponseDto>> createUser(@RequestBody @Valid UserRequestDto data) {
        UserResponseDto userResponseDto = userService.saveUser(data);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto<>(userResponseDto));
    }

    @Override
    @PostMapping("/clinica")
    public ResponseEntity<ResponseDto<GlobalUserResponseDto>> createClinica(@RequestBody @Valid ClinicaRequestDto data) {
        GlobalUserResponseDto globalUserResponseDto = userService.saveClinica(data);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto<>(globalUserResponseDto));
    }

    @Override
    @PostMapping("/ong")
    public ResponseEntity<ResponseDto<GlobalUserResponseDto>> createOng(@RequestBody @Valid OngRequestDto data) {
        GlobalUserResponseDto globalUserResponseDto = userService.saveOng(data);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto<>(globalUserResponseDto));
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<ResponseDto<UserResponseDto>> updateUser(@PathVariable String id, @RequestBody @Valid UserUpdateRequestDto data) {
        UserResponseDto updatedUser = userService.updateUser(id, data);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto<>(updatedUser));
    }

    @Override
    @PatchMapping("/clinica/{id}")
    public ResponseEntity<ResponseDto<GlobalUserResponseDto>> updateClinica(@PathVariable String id, @RequestBody @Valid ClinicaRequestDto data) {
        GlobalUserResponseDto updatedUser = userService.updateClinica(id, data);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto<>(updatedUser));
    }

    @Override
    @PatchMapping("/ong/{id}")
    public ResponseEntity<ResponseDto<GlobalUserResponseDto>> updateOng(@PathVariable String id, @RequestBody @Valid OngRequestDto data) {
        GlobalUserResponseDto updatedUser = userService.updateOng(id, data);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto<>(updatedUser));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<String>> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(new ResponseDto<>("Usuário deletado com sucesso"));
    }

    @Override
    @DeleteMapping("/clinica/{id}")
    public ResponseEntity<ResponseDto<String>> deleteClinica(@PathVariable String id) {
        userService.deleteClinica(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(new ResponseDto<>("Clinica deletada com sucesso"));
    }

    @Override
    @DeleteMapping("/ong/{id}")
    public ResponseEntity<ResponseDto<String>> deleteOng(@PathVariable String id) {
        userService.deleteOng(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(new ResponseDto<>("Ong deletada com sucesso"));
    }

    @Override
    @PostMapping("/login")
    public ResponseEntity<ResponseDto<AuthResponseDto>> login(@RequestBody @Valid AuthResquestDto data) {
        String token = userService.authenticate(data);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto<>(new AuthResponseDto(token)));
    }
}