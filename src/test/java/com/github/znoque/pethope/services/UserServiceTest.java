package com.github.znoque.pethope.services;

import com.github.znoque.pethope.dto.user.UserRequestDto;
import com.github.znoque.pethope.model.User;
import com.github.znoque.pethope.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    private UserRequestDto userRequestDto;
    private User user;

//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        userRequestDto = new UserRequestDto("test@example.com", "password123");
//        user = new User(userRequestDto.email(), "encodedPassword");
//    }

//    @Test
//    @DisplayName("Deve lançar exceção quando usuário não for encontrado")
//    void shouldThrowExceptionWhenUserNotFound() {
//        when(userRepository.findByEmail(userRequestDto.email())).thenReturn(Optional.empty());
//
//        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.authenticate(userRequestDto));
//        assertEquals("Usuário não encontrado", exception.getMessage());
//    }
//
//    @Test
//    @DisplayName("Deve lançar exceção quando a senha for inválida")
//    void shouldThrowExceptionWhenPasswordIsInvalid() {
//        when(userRepository.findByEmail(userRequestDto.email())).thenReturn(Optional.ofNullable(user));
//        when(passwordEncoder.matches(userRequestDto.password(), user.getSenha())).thenReturn(false);
//
//        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.authenticate(userRequestDto));
//        assertEquals("Senha inválida", exception.getMessage());
//    }

//    @Test
//    @DisplayName("Deve autenticar o usuário com sucesso")
//    void shouldAuthenticateUserSuccessfully() {
//        when(userRepository.findByEmail(userRequestDto.email())).thenReturn(Optional.ofNullable(user));
//        when(passwordEncoder.matches(userRequestDto.password(), user.getSenha())).thenReturn(true);
//
//        UserRequestDto authenticatedUser = userService.authenticate(userRequestDto);
//
//        assertNotNull(authenticatedUser);
//        assertEquals(userRequestDto.email(), authenticatedUser.email());
//    }

    @Test
    @DisplayName("Deve lançar exceção quando o email já estiver cadastrado")
    void shouldThrowExceptionWhenEmailAlreadyExists() {
        when(userRepository.findByEmail(userRequestDto.email())).thenReturn(Optional.ofNullable(user));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.saveUser(userRequestDto));
        assertEquals("Usuário já criado com o e-mail: test@example.com", exception.getMessage());
    }

    @Test
    @DisplayName("Deve salvar o usuário com sucesso")
    void shouldSaveUserSuccessfully() {
        when(userRepository.findByEmail(userRequestDto.email())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(userRequestDto.password())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User savedUser = userService.saveUser(userRequestDto);

        assertNotNull(savedUser);
        assertEquals(userRequestDto.email(), savedUser.getEmail());
        verify(userRepository, times(1)).save(any(User.class));
    }
}