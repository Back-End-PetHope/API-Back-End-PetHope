package com.github.znoque.adote_me_api.services;


import com.github.znoque.adote_me_api.dto.UserDto;
import com.github.znoque.adote_me_api.model.auth.Auth;
import com.github.znoque.adote_me_api.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto authenticate(UserDto data) {

        Auth auth = userRepository.findByEmail(data.email());
        if (auth == null) {
            throw new RuntimeException("Usuário não encontrado");
        }

        if (!passwordEncoder.matches(data.password(), auth.getPassword())) {
            throw new RuntimeException("Senha inválida");
        }

        return new UserDto(auth.getEmail(), auth.getPassword());
    }
}
