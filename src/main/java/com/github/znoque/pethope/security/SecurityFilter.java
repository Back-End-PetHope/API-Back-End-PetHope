package com.github.znoque.pethope.security;

import com.github.znoque.pethope.repository.UserRepository;
import com.github.znoque.pethope.services.AuthService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final AuthService authService;
    private final UserRepository userRepository;

    public SecurityFilter(TokenService tokenService, AuthService authService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.authService = authService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = this.recoverToken(request);
        if(token != null) {
            String username = tokenService.validateToken(token);
            UserDetails user = authService.loadUserByUsername(username);
            var auth = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(request,response);
    }

    public String recoverToken(HttpServletRequest req) {
        String authHeader = req.getHeader("Authorization");
        if(authHeader != null) {
            return authHeader.replace("Bearer ", "");
        }
        return null;
    }

    public Boolean validatedToken(HttpServletRequest token) {
        String tokenUser = this.recoverToken(token);
        if(tokenUser != null) {
            String username = tokenService.validateToken(tokenUser);
            UserDetails user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));
            var auth = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
            return true;
        }
        return false;
    }
}
