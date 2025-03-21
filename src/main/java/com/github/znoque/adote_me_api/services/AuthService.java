package com.github.znoque.adote_me_api.services;


import com.github.znoque.adote_me_api.dto.UserDto;
import com.github.znoque.adote_me_api.model.auth.Auth;
import com.github.znoque.adote_me_api.repository.UserRepository;
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
