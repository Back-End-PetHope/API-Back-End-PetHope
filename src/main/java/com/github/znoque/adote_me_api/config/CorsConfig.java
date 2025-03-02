package com.github.znoque.adote_me_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //corsConfiguration.addAllowedOrigin("http://localhost:5173"); // Frontend
        corsConfiguration.addAllowedOrigin("*"); // Frontend
        corsConfiguration.addAllowedMethod("*"); // Permite todos os métodos
        corsConfiguration.addAllowedHeader("*"); // Permite todos os headers
        corsConfiguration.setAllowCredentials(true); // Permite credenciais

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return new CorsFilter(source);
    }
}
