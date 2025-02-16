package com.github.znoque.adote_me_api.services;

import com.github.znoque.adote_me_api.model.user.User;
import com.github.znoque.adote_me_api.dto.UserDto;
import com.github.znoque.adote_me_api.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //alterar para protected depois e testar
    public User saveUser(UserDto data) {

        User isEmailExisting  = userRepository.findByEmail(data.email());
        if (isEmailExisting  != null) {
            throw new RuntimeException("Usuario ja criado");
        }
        User user = new User();
        user.setEmail(data.email());
        user.setPassword(data.password());
        return userRepository.save(user);
    }
}
