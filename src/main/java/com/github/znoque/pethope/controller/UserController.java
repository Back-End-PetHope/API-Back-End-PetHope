package com.github.znoque.pethope.controller;

import com.github.znoque.pethope.config.UserApi;
import com.github.znoque.pethope.dto.GlobalResponseDto;
import com.github.znoque.pethope.dto.ResponseDto;
import com.github.znoque.pethope.dto.clinica.ClinicaRequestDto;
import com.github.znoque.pethope.dto.ong.OngRequestDto;
import com.github.znoque.pethope.dto.user.*;
import com.github.znoque.pethope.model.User;
import com.github.znoque.pethope.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/users")
@RestController
public class UserController implements UserApi {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @Override
    public ResponseEntity<ResponseDto<List<GlobalResponseDto>>> findAllUser() {
        return Optional.ofNullable(userService.listAllUser())
                .filter(list -> !list.isEmpty())
                .map(listaUser -> ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseDto<>(
                                listaUser)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseDto<>(
                                null)));
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<ResponseDto<UserResponseDto>> findByIdUser(@PathVariable @Valid String id) {
        return Optional.ofNullable(userService.listByIdUser(id))
                .map(result -> ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseDto<>(result)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseDto<>(null)));
    }

    @GetMapping("/clinica/{id}")
    @Override
    public ResponseEntity<ResponseDto<GlobalResponseDto>> findByIdClinica(@PathVariable @Valid String id) {
        return Optional.ofNullable(userService.listByIdClinica(id))
                .map(result -> ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseDto<>(result)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseDto<>(null)));
    }

    @GetMapping("/ong/{id}")
    @Override
    public ResponseEntity<ResponseDto<GlobalResponseDto>> findByIdOng(@PathVariable @Valid String id) {
        return Optional.ofNullable(userService.listByIdOng(id))
                .map(result -> ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseDto<>(result)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseDto<>(null)));
    }

    @PostMapping()
    @Override
    public ResponseEntity<?> createUser(@RequestBody @Valid UserRequestDto data) {
        User user = userService.saveUser(data);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto<>(
                        userService.toUserResponseDto(user)));
    }

    @PostMapping("/clinica")
    @Override
    public ResponseEntity<?> createClinica(@RequestBody @Valid ClinicaRequestDto data) {
        User user = userService.saveClinica(data);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto<>(
                        userService.toGlobalResponseDto(user)));
    }

    @PostMapping("/ong")
    @Override
    public ResponseEntity<?> createOng(@RequestBody @Valid OngRequestDto data) {
        User user = userService.saveOng(data);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto<>(
                        userService.toGlobalResponseDto(user)));
    }

    @PostMapping("/login")
    @Override
    public ResponseEntity<?> loginUser(@RequestBody @Valid AuthResquestDto data) {
        String token = userService.authenticate(data);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto<>(
                        new AuthResponseDto(token)));
    }

    @PatchMapping("/{id}")

    public ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody @Valid UserUpdateRequestDto data) {
        User updatedUser = userService.updateUser(id, data);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto<>(
                        userService.toGlobalResponseDto(updatedUser)));
    }

    @PatchMapping("/clinica/{id}")
    public ResponseEntity<?> updateClinica(@PathVariable String id, @RequestBody @Valid ClinicaRequestDto data) {
        User updatedUser = userService.updateClinica(id, data);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto<>(
                        userService.toGlobalResponseDto(updatedUser)));
    }

    @PatchMapping("/ong/{id}")
    public ResponseEntity<?> updateOng(@PathVariable String id, @RequestBody @Valid OngRequestDto data) {
        User updatedUser = userService.updateOng(id, data);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto<>(
                        userService.toGlobalResponseDto(updatedUser)));
    }

    @DeleteMapping("/{id}")

    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(new ResponseDto<>(
                        "Usuário deletado com sucesso"));
    }

    @DeleteMapping("/clinica/{id}")
    public ResponseEntity<?> deleteClinica(@PathVariable String id) {
        userService.deleteClinica(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(new ResponseDto<>(
                        "Clinica deletada com sucesso"));

    }

    @DeleteMapping("/ong/{id}")
    public ResponseEntity<?> deleteOng(@PathVariable String id) {
        userService.deleteOng(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(new ResponseDto<>(
                        "Ong deletada com sucesso"));
    }

}