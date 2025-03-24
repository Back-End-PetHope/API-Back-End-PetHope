package com.github.znoque.pethope.controller;

import com.github.znoque.pethope.dto.UserDto;
import com.github.znoque.pethope.model.User;
import com.github.znoque.pethope.services.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUser() {
        List<User> user = userService.UserFindAll();
        logger.info("Total de user retornados: {}", user.size());
        return user;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getByIdUser(@PathVariable @Valid int id) {
        try{
            return userService.UserFindByid(id)
                    .map(result -> ResponseEntity.status(HttpStatus.OK).body(result))
                    .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao buscar usuário: " + e.getMessage());
        }
    }

//    @PostMapping("/create")
//    public ResponseEntity<?> createUser(@RequestBody @Valid UserDto data) {
//        try {
//            User user = userService.UserSave(data);
//            return ResponseEntity.status(HttpStatus.CREATED).body(user);
//        } catch (DataIntegrityViolationException e) {
//            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
//        } catch (Exception e) {
//            logger.error("Ocorreu um erro inesperado", e);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
}
