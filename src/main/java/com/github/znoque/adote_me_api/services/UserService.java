package com.github.znoque.adote_me_api.services;

import com.github.znoque.adote_me_api.model.auth.Auth;
import com.github.znoque.adote_me_api.dto.UserDto;
import com.github.znoque.adote_me_api.repository.UserRepository;
import com.github.znoque.adote_me_api.security.SecutiryConfig;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //alterar para protected depois e testar
    public Auth saveUser(UserDto data) {

        Auth isEmailExisting  = userRepository.findByEmail(data.email());
        if (isEmailExisting  != null) {
            throw new RuntimeException("Usuario ja criado");
        }
        Auth auth = new Auth();
        auth.setEmail(data.email());
        auth.setPassword(passwordEncoder.encode(data.password()));
        return userRepository.save(auth);
    }
}
