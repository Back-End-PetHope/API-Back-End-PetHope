package com.github.znoque.pethope.security;

import com.github.znoque.pethope.enums.UsuarioTipo;
import com.github.znoque.pethope.model.User;
import com.github.znoque.pethope.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SecurityFilterTest {

    @InjectMocks
    private SecurityFilter securityFilter;

    @Mock
    private TokenService tokenService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    private final String validToken = "valid.jwt.token";
    private final String username = "testuser@email.com";

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new User(
                "12345678901",                // cpfCnpj
                "Responsável",                // responsavelNome
                "11999999999",                // telefone
                "São Paulo",                  // cidade
                "Rua X, 123",                 // logradouro
                username,                     // email (email)
                "encrypted-password",         // senha
                UsuarioTipo.USUARIO           // tipo
        );

        SecurityContextHolder.clearContext();
    }

    @Test
    @DisplayName("Deve autenticar e setar o usuário no contexto de segurança com token válido")
    void shouldAuthenticateUserWithValidToken() throws Exception {
        when(request.getHeader("Authorization")).thenReturn("Bearer " + validToken);
        when(tokenService.validateToken(validToken)).thenReturn(username);
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        securityFilter.doFilterInternal(request, response, filterChain);

        assertNotNull(SecurityContextHolder.getContext().getAuthentication());
        assertEquals(username, SecurityContextHolder.getContext().getAuthentication().getName());

        verify(filterChain, times(1)).doFilter(request, response);
    }

    @Test
    @DisplayName("Não deve autenticar se não houver token")
    void shouldNotAuthenticateWithoutToken() throws Exception {
        when(request.getHeader("Authorization")).thenReturn(null);

        securityFilter.doFilterInternal(request, response, filterChain);

        assertNull(SecurityContextHolder.getContext().getAuthentication());
        verify(filterChain, times(1)).doFilter(request, response);
    }

    @Test
    @DisplayName("Deve lançar exceção se o usuário não for encontrado")
    void shouldThrowExceptionWhenUserNotFound() {
        when(request.getHeader("Authorization")).thenReturn("Bearer " + validToken);
        when(tokenService.validateToken(validToken)).thenReturn(username);
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        assertThrows(Exception.class, () ->
                securityFilter.doFilterInternal(request, response, filterChain)
        );
    }
}
