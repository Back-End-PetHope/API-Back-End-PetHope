package com.github.znoque.pethope.controller;

import com.github.znoque.pethope.config.UserApi;
import com.github.znoque.pethope.dto.GlobalResponseDto;
import com.github.znoque.pethope.dto.clinica.ClinicaRequestDto;
import com.github.znoque.pethope.dto.GlobalPatternResponseDto;
import com.github.znoque.pethope.dto.ong.OngRequestDto;
import com.github.znoque.pethope.dto.user.AuthResponseDto;
import com.github.znoque.pethope.dto.user.AuthResquestDto;
import com.github.znoque.pethope.dto.user.UserRequestDto;
import com.github.znoque.pethope.dto.user.UserResponseDto;
import com.github.znoque.pethope.dto.user.UserUpdateRequestDto;
import com.github.znoque.pethope.model.User;
import com.github.znoque.pethope.services.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
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
  public ResponseEntity<GlobalPatternResponseDto<List<GlobalResponseDto>>> findAllUser() {
    return Optional.ofNullable(userService.listAllUser())
        .filter(list -> !list.isEmpty())
        .map(listaUser -> ResponseEntity.status(HttpStatus.OK)
            .body(new GlobalPatternResponseDto<>(
                listaUser)))
        .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new GlobalPatternResponseDto<>(
                null)));
  }

  @GetMapping("/{id}")
  @Override
  public ResponseEntity<GlobalPatternResponseDto<UserResponseDto>> findByIdUser(@PathVariable @Valid String id) {
    return Optional.ofNullable(userService.listByIdUser(id))
            .map(result -> ResponseEntity.status(HttpStatus.OK)
                    .body(new GlobalPatternResponseDto<>(result)))
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new GlobalPatternResponseDto<>(null)));
  }

  @GetMapping("/clinica/{id}")
  @Override
  public ResponseEntity<GlobalPatternResponseDto<GlobalResponseDto>> findByIdClinica(@PathVariable @Valid String id) {
    return Optional.ofNullable(userService.listByIdClinica(id))
            .map(result -> ResponseEntity.status(HttpStatus.OK)
                    .body(new GlobalPatternResponseDto<>(result)))
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new GlobalPatternResponseDto<>(null)));
  }

  @GetMapping("/ong/{id}")
  @Override
  public ResponseEntity<GlobalPatternResponseDto<GlobalResponseDto>> findByIdOng(@PathVariable @Valid String id) {
    return Optional.ofNullable(userService.listByIdOng(id))
            .map(result -> ResponseEntity.status(HttpStatus.OK)
                    .body(new GlobalPatternResponseDto<>(result)))
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new GlobalPatternResponseDto<>(null)));
  }

  @PostMapping()
  @Override
  public ResponseEntity<?> createUser(@RequestBody @Valid UserRequestDto data) {
    User user = userService.saveUser(data);
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(new GlobalPatternResponseDto<>(
            userService.toUserResponseDto(user)));
  }

  @PostMapping("/clinica")
  @Override
  public ResponseEntity<?> createClinica(@RequestBody @Valid ClinicaRequestDto data) {
    User user = userService.saveClinica(data);
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(new GlobalPatternResponseDto<>(
            userService.toGlocalResponseDto(user)));
  }

  @PostMapping("/ong")
  @Override
  public ResponseEntity<?> createOng(@RequestBody @Valid OngRequestDto data) {
    User user = userService.saveOng(data);
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(new GlobalPatternResponseDto<>(
            userService.toGlocalResponseDto(user)));
  }

  @PostMapping("/login")
  @Override
  public ResponseEntity<?> loginUser(@RequestBody @Valid AuthResquestDto data) {
    String token = userService.authenticate(data);
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(new GlobalPatternResponseDto<>(
            new AuthResponseDto(token)));
  }

  @PatchMapping("/{id}")

  public ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody @Valid UserUpdateRequestDto data) {
    try {
      User updatedUser = userService.updateUser(id, data);
      return ResponseEntity
          .status(HttpStatus.OK)
          .body(new GlobalPatternResponseDto<>(
              userService.toGlocalResponseDto(updatedUser)));
    } catch (NoSuchElementException e) {
      return ResponseEntity
          .status(HttpStatus.NOT_FOUND)
          .body(new GlobalPatternResponseDto<>(
              "Usuário não encontrado"));
    } catch (DataIntegrityViolationException e) {
      return ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
          .body(new GlobalPatternResponseDto<>(
              "Erro de integridade de dados: " + e.getMessage()));
    }
  }
  @PatchMapping("/clinica/{id}")
  public ResponseEntity<?> updateClinica(@PathVariable String id, @RequestBody @Valid ClinicaRequestDto data) {
    try {
      User updatedUser = userService.updateClinica(id, data);
      return ResponseEntity
          .status(HttpStatus.OK)
          .body(new GlobalPatternResponseDto<>(
              userService.toGlocalResponseDto(updatedUser)));
    } catch (NoSuchElementException e) {
      return ResponseEntity
          .status(HttpStatus.NOT_FOUND)
          .body(new GlobalPatternResponseDto<>(
              "Clinica não encontrada"));
    } catch (DataIntegrityViolationException e) {
      return ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
          .body(new GlobalPatternResponseDto<>(
              "Erro de integridade de dados: " + e.getMessage()));
    }
  }
  @PatchMapping("/ong/{id}")
  public ResponseEntity<?> updateOng(@PathVariable String id, @RequestBody @Valid OngRequestDto data) {
    try {
      User updatedUser = userService.updateOng(id, data);
      return ResponseEntity
          .status(HttpStatus.OK)
          .body(new GlobalPatternResponseDto<>(
              userService.toGlocalResponseDto(updatedUser)));
    } catch (NoSuchElementException e) {
      return ResponseEntity
          .status(HttpStatus.NOT_FOUND)
          .body(new GlobalPatternResponseDto<>(
              "Ong não encontrada"));
    } catch (DataIntegrityViolationException e) {
      return ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
          .body(new GlobalPatternResponseDto<>(
              "Erro de integridade de dados: " + e.getMessage()));
    }
  }

  @DeleteMapping("/{id}")

  public ResponseEntity<?> deleteUser(@PathVariable String id) {
    try {
      userService.deleteUser(id);
      return ResponseEntity
          .status(HttpStatus.NO_CONTENT)
          .body(new GlobalPatternResponseDto<>(
              "Usuário deletado com sucesso"));
    } catch (NoSuchElementException e) {
      return ResponseEntity
          .status(HttpStatus.NOT_FOUND)
          .body(new GlobalPatternResponseDto<>(
              "Usuário não encontrado"));
    }
  }

  @DeleteMapping("/clinica/{id}")
  public ResponseEntity<?> deleteClinica(@PathVariable String id) {
    try {
      userService.deleteClinica(id);
      return ResponseEntity
          .status(HttpStatus.NO_CONTENT)
          .body(new GlobalPatternResponseDto<>(
              "Clinica deletada com sucesso"));
    } catch (NoSuchElementException e) {
      return ResponseEntity
          .status(HttpStatus.NOT_FOUND)
          .body(new GlobalPatternResponseDto<>(
              "Clinica não encontrada"));
    }
  }
  @DeleteMapping("/ong/{id}")
  public ResponseEntity<?> deleteOng(@PathVariable String id) {
    try {
      userService.deleteOng(id);
      return ResponseEntity
          .status(HttpStatus.NO_CONTENT)
          .body(new GlobalPatternResponseDto<>(
              "Ong deletada com sucesso"));
    } catch (NoSuchElementException e) {
      return ResponseEntity
          .status(HttpStatus.NOT_FOUND)
          .body(new GlobalPatternResponseDto<>(
              "Ong não encontrada"));
    }
  }

}