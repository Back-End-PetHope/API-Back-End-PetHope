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
            "/users/login",
            "/users",
            "/users/clinica",
            "/users/create",
            "/users/ong",
            "/users/{id}", "/users/ong/{id}", "/users/clinica/{id}")
            .permitAll()
            .anyRequest().authenticated())
        .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
        .oauth2Login(form -> form.defaultSuccessUrl("/oauth/auth-google-info", true));
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
