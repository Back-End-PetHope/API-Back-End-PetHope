package com.github.znoque.pethope.services;

import com.github.znoque.pethope.dto.user.AuthResquestDto;
import com.github.znoque.pethope.dto.user.UserRequestDto;
import com.github.znoque.pethope.enums.UsuarioTipo;
import com.github.znoque.pethope.model.User;
import com.github.znoque.pethope.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AuthenticationManager authenticationManager;


    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    private AuthResquestDto authResquestDto;
    private UserRequestDto userRequestDto;
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        authResquestDto = new AuthResquestDto("test@example.com", "password123");
        userRequestDto = new UserRequestDto(
                "12345678901", // CPF fictício formatado
                "João Silva",
                "11999999999",
                "São Paulo",
                "Rua Exemplo, 123",
                "test@example.com",
                "password123"
        );

        user = new User(
                userRequestDto.cpf(),
                userRequestDto.responsavelNome(),
                userRequestDto.telefone(),
                userRequestDto.cidade(),
                userRequestDto.endereco(),
                userRequestDto.email(),
                "encodedPassword", // Aqui assumo que a senha será codificada antes de ser salva
                UsuarioTipo.USUARIO
        );
    }

    @Test
    @DisplayName("Deve lançar exceção quando usuário não for encontrado")
    void shouldThrowExceptionWhenUserNotFound() {
        when(userRepository.findByUsername(authResquestDto.email()))
                .thenReturn(Optional.empty());
        when(authenticationManager.authenticate(any()))
                .thenThrow(new RuntimeException("Usuário não encontrado"));
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                userService.authenticate(authResquestDto));
        assertEquals("Usuário não encontrado", exception.getMessage());
    }


    @Test
    @DisplayName("Deve lançar exceção quando a senha for inválida")
    void shouldThrowExceptionWhenPasswordIsInvalid() {
        when(userRepository.findByUsername(authResquestDto.email()))
                .thenReturn(Optional.of(user));
        when(authenticationManager.authenticate(any()))
                .thenThrow(new BadCredentialsException("Senha inválida"));
        Exception exception = assertThrows(BadCredentialsException.class, () ->
                userService.authenticate(authResquestDto));
        assertEquals("Senha inválida", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando o email já estiver cadastrado")
    void shouldThrowExceptionWhenEmailAlreadyExists() {
        when(userRepository.findByUsername(userRequestDto.email())).thenReturn(Optional.ofNullable(user));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.saveUser(userRequestDto));
        assertEquals("Usuário já criado com o e-mail: test@example.com", exception.getMessage());
    }

    @Test
    @DisplayName("Deve salvar o usuário com sucesso")
    void shouldSaveUserSuccessfully() {
        when(userRepository.findByUsername(userRequestDto.email())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(userRequestDto.password())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User savedUser = userService.saveUser(userRequestDto);

        assertNotNull(savedUser);
        assertEquals(userRequestDto.email(), savedUser.getUsername());
        verify(userRepository, times(1)).save(any(User.class));
    }
}