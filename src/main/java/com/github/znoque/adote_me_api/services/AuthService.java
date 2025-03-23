package com.github.znoque.adote_me_api.services;


import com.github.znoque.adote_me_api.dto.auth.AuthDto;
import com.github.znoque.adote_me_api.model.auth.Auth;
import com.github.znoque.adote_me_api.repository.AuthRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;

    AuthService(AuthRepository authRepository, PasswordEncoder passwordEncoder) {
        this.authRepository = authRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthDto authenticate(AuthDto data) {

        Auth auth = authRepository.findByEmail(data.email());
        if (auth == null) {
            throw new RuntimeException("Usuário não encontrado");
        }

        if (!passwordEncoder.matches(data.password(), auth.getPassword())) {
            throw new RuntimeException("Senha inválida");
        }

        return new AuthDto(auth.getEmail(), auth.getPassword());
    }

    //alterar para protected depois e testar
    public Auth saveUser(AuthDto data) {

        Auth isEmailExisting  = authRepository.findByEmail(data.email());
        if (isEmailExisting  != null) {
            throw new RuntimeException("Usuario ja criado");
        }
        Auth auth = new Auth();
        auth.setEmail(data.email());
        auth.setPassword(passwordEncoder.encode(data.password()));
        return authRepository.save(auth);
    }
}
