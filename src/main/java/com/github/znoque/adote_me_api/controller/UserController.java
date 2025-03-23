package com.github.znoque.adote_me_api.controller;


import com.github.znoque.adote_me_api.config.SwaggerDocumentacionConfig;
import com.github.znoque.adote_me_api.dto.auth.AuthDto;
import com.github.znoque.adote_me_api.model.auth.Auth;
import com.github.znoque.adote_me_api.services.AuthService;
import com.github.znoque.adote_me_api.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("user")
public class UserController {

}
