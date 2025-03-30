package com.github.znoque.pethope.services;

import com.github.znoque.pethope.dto.UserRequestDto;
import com.github.znoque.pethope.model.User;
import com.github.znoque.pethope.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserRequestDto authenticate(UserRequestDto data) {
        User user = userRepository.findByEmail(data.email())
                .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado"));

        if (!passwordEncoder.matches(data.password(), user.getPassword())) {
            throw new BadCredentialsException("Senha inválida");
        }

        return new UserRequestDto(user.getEmail(), user.getPassword());
    }

    public User saveUser(UserRequestDto data) {
        userRepository.findByEmail(data.email())
                .ifPresent(existingUser -> {
                    throw new DataIntegrityViolationException("Usuário já criado com o e-mail: " + data.email());
                });

        User user = new User(data.email(), passwordEncoder.encode(data.password()));
        return userRepository.save(user);
    }
}
