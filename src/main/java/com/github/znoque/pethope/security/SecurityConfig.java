package com.github.znoque.pethope.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

  private final SecurityFilter securityFilter;

  public SecurityConfig(SecurityFilter securityFilter) {
    this.securityFilter = securityFilter;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(auth -> auth.requestMatchers(
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/v1/users/login",
            "/v1/users",
            "/v1/users/{id}",
            "/v1/users/clinica/{id}",
            "/v1/users/ong/{id}",
            "/v1/users/clinica",
            "/v1/users/ong",
            "/v1/pets",
            "/v1/pets/{id}",
            "/v1/pets/especies",
            "/v1/pets/racas",
            "/v1/pets/temperamentos",
            "/v1/pets/sexos",
            "/v1/pets/filtros")
            .permitAll()
            .anyRequest().authenticated())
        .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authConf) throws Exception {
    return authConf.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
