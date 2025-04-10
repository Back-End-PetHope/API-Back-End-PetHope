package com.github.znoque.pethope.controller;

import com.github.znoque.pethope.config.SwaggerDocumentationConfig;
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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
            .body(new GlobalPatternResponseDto<>(HttpStatus.OK.getReasonPhrase(),
                listaUser)))
        .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new GlobalPatternResponseDto<>(
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                null)));
  }

  @GetMapping("/{id}")
  @Override
  public ResponseEntity<GlobalPatternResponseDto<User>> findByIdUser(@PathVariable @Valid String id) {
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

  @PatchMapping("/{id}")
  @Operation(summary = SwaggerDocumentationConfig.SUMARIO_UPDATE, description = SwaggerDocumentationConfig.DESCRICAO_UPDATE)
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = SwaggerDocumentationConfig.RESPONSE_200),
      @ApiResponse(responseCode = "404", description = SwaggerDocumentationConfig.RESPONSE_404),
      @ApiResponse(responseCode = "400", description = SwaggerDocumentationConfig.RESPONSE_400),
      @ApiResponse(responseCode = "500", description = SwaggerDocumentationConfig.RESPONSE_500)
  })
  public ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody @Valid UserUpdateRequestDto data) {
    try {
      User updatedUser = userService.updateUser(id, data);
      return ResponseEntity
          .status(HttpStatus.OK)
          .body(new GlobalPatternResponseDto<>(HttpStatus.OK.getReasonPhrase(),
              userService.toGlocalResponseDto(updatedUser)));
    } catch (NoSuchElementException e) {
      return ResponseEntity
          .status(HttpStatus.NOT_FOUND)
          .body(new GlobalPatternResponseDto<>(HttpStatus.NOT_FOUND.getReasonPhrase(),
              "Usuário não encontrado"));
    } catch (DataIntegrityViolationException e) {
      return ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
          .body(new GlobalPatternResponseDto<>(HttpStatus.BAD_REQUEST.getReasonPhrase(),
              "Erro de integridade de dados: " + e.getMessage()));
    }
  }

  @DeleteMapping("/{id}")
  @Operation(summary = SwaggerDocumentationConfig.SUMARIO_DELETE, description = SwaggerDocumentationConfig.DESCRICAO_DELETE)
  @ApiResponses({
      @ApiResponse(responseCode = "204", description = SwaggerDocumentationConfig.RESPONSE_204),
      @ApiResponse(responseCode = "404", description = SwaggerDocumentationConfig.RESPONSE_404),
      @ApiResponse(responseCode = "500", description = SwaggerDocumentationConfig.RESPONSE_500)
  })
  public ResponseEntity<?> deleteUser(@PathVariable String id) {
    try {
      userService.deleteUser(id);
      return ResponseEntity
          .status(HttpStatus.NO_CONTENT)
          .body(new GlobalPatternResponseDto<>(HttpStatus.NO_CONTENT.getReasonPhrase(),
              "Usuário deletado com sucesso"));
    } catch (NoSuchElementException e) {
      return ResponseEntity
          .status(HttpStatus.NOT_FOUND)
          .body(new GlobalPatternResponseDto<>(HttpStatus.NOT_FOUND.getReasonPhrase(),
              "Usuário não encontrado"));
    }
  }

}