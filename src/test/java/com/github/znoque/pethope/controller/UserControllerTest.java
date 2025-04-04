package com.github.znoque.pethope.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.znoque.pethope.dto.UserRequestDto;
import com.github.znoque.pethope.model.User;
import com.github.znoque.pethope.security.TestSecurityConfig;
import com.github.znoque.pethope.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.NoSuchElementException;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@Import(TestSecurityConfig.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private UserRequestDto userRequestDto;
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userRequestDto = new UserRequestDto("test@example.com", "password123");
        user = new User(userRequestDto.email(), "encodedPassword123");
    }

    @Test
    @DisplayName("Deve criar o usuário com sucesso e retornar 201 Created")
    void shouldCreateUserSucessfullyAndReturn201Created() throws Exception {
        when(userService.saveUser(userRequestDto)).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.post("/users/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email").value("test@example.com"));
    }


    @Test
    @DisplayName("Deve falhar quando o email já existe e retornar 422 Unprocessable Entity")
    void shouldFailAtCreateUserAndReturn422UnprocessableEntity() throws Exception {
        String errorMessage = "E-mail já está em uso";
        when(userService.saveUser(userRequestDto)).thenThrow(new DataIntegrityViolationException(errorMessage));

        mockMvc.perform(MockMvcRequestBuilders.post("/users/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequestDto)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.status").value(422))
                .andExpect(jsonPath("$.message").value(errorMessage));
    }

    @Test
    @DisplayName("Deve falhar quando ocorrer um erro inesperado ao criar usuário e retornar 500 Internal Server Error")
    void shouldFailAtCreateUserAndReturn500InternalServerError() throws Exception {
        String errorMessage = "Ocorreu um erro inesperado";
        when(userService.saveUser(userRequestDto)).thenThrow(new RuntimeException(errorMessage));

        mockMvc.perform(MockMvcRequestBuilders.post("/users/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequestDto)))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.status").value(500))
                .andExpect(jsonPath("$.message").value(errorMessage));
    }

    @Test
    @DisplayName("Deve logar usuário com sucesso e retornar 200 OK")
    void shouldLoginUserSuccessfullyAndReturn200OK() throws Exception {
        when(userService.authenticate(userRequestDto)).thenReturn(userRequestDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequestDto)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deve falhar quando o usuário não é encontrado e retornar 404 Not Found")
    void shouldFailtAtLoginWhenUserDoesNotExistsAndReturn404NotFound() throws Exception {
        String errorMessage = "Usuário não encontrado";
        when(userService.authenticate(userRequestDto)).thenThrow(new NoSuchElementException(errorMessage));

        mockMvc.perform(MockMvcRequestBuilders.post("/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequestDto)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value(errorMessage));
    }

    @Test
    @DisplayName("Deve falhar quando ocorrer um erro inesperado ao logar usuário e retornar 500 Internal Server Error")
    void shouldFailAtLoginUserAndReturn500InternalServerError() throws Exception {
        String errorMessage = "Ocorreu um erro inesperado";
        when(userService.authenticate(userRequestDto)).thenThrow(new RuntimeException(errorMessage));

        mockMvc.perform(MockMvcRequestBuilders.post("/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequestDto)))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.status").value(500))
                .andExpect(jsonPath("$.message").value(errorMessage));
    }
}