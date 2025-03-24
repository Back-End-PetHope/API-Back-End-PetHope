package com.github.znoque.pethope.services;

import com.github.znoque.pethope.model.User;
import com.github.znoque.pethope.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> UserFindAll() {
        return userRepository.findAll();
    }

    public Optional<User> UserFindByid(int id) {
        return Optional.ofNullable(userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado com o ID: "+id)));
    }

//    public User UserSave(UserDto data){
//        if(userRepository.existsById(data.id)) {
//            throw new DataIntegrityViolationException("Usuario ja está cadastrado");
//        }
//        User user = new User();
//
//    }
}
