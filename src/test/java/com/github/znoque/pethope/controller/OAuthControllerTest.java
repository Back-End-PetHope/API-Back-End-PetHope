package com.github.znoque.pethope.controller;


import com.github.znoque.pethope.security.TestSecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Map;
import java.util.Set;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oauth2Login;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OAuthController.class)
@Import(TestSecurityConfig.class)
class OAuthControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("Deve retornar as informações do usuário autenticado com sucesso e retornar 200 OK")
    void shouldReturnUserInfoSuccessfullyAndReturn200OK() throws Exception {
        OAuth2User mockUser = new DefaultOAuth2User(
                Set.of(new SimpleGrantedAuthority("ROLE_USER")),
                Map.of("sub", "1234567890", "name", "Test User", "email", "test@example.com"),
                "sub"
        );

        mockMvc.perform(MockMvcRequestBuilders.get("/oauth/auth-google-info")
                        .with(oauth2Login().oauth2User(mockUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sub").value("1234567890"))
                .andExpect(jsonPath("$.name").value("Test User"))
                .andExpect(jsonPath("$.email").value("test@example.com"));
    }

    @Test
    @DisplayName("Deve redirecionar corretamente para o Google")
    void shouldRedirectToGoogle() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/oauth/redirect-to-google"))
                .andExpect(status().isFound())
                .andExpect(header().string("Location", "/api/oauth2/authorization/google"));
    }
}