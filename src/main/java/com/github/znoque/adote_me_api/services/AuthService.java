package com.github.znoque.adote_me_api.services;


import com.github.znoque.adote_me_api.dto.UserDto;
import com.github.znoque.adote_me_api.model.user.User;
import com.github.znoque.adote_me_api.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;

    AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public UserDto authenticate(UserDto data) {

        User user = userRepository.findByEmail(data.email());
        if (user != null) {
            if (user.getPassword().equals(data.password())) {
                return new UserDto(user.getEmail(), user.getPassword());
            }
        }
        throw new RuntimeException("Usuario não encontrado");
    }
}
