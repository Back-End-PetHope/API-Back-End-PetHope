package com.github.znoque.pethope.services;


import com.github.znoque.pethope.dto.UserDto;
import com.github.znoque.pethope.model.Auth;
import com.github.znoque.pethope.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;

    AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public UserDto authenticate(UserDto data) {

        Auth auth = userRepository.findByEmail(data.email());
        if (auth != null) {
            if (auth.getPassword().equals(data.password())) {
                return new UserDto(auth.getEmail(), auth.getPassword());
            }
        }
        throw new RuntimeException("Usuario não encontrado");
    }
}
