package com.github.znoque.pethope.services;

import com.github.znoque.pethope.dto.clinica.ClinicaOrOngRequestDto;
import com.github.znoque.pethope.dto.user.AuthResponseDto;
import com.github.znoque.pethope.dto.user.AuthResquestDto;
import com.github.znoque.pethope.dto.user.UserRequestDto;
import com.github.znoque.pethope.model.User;
import com.github.znoque.pethope.repository.UserRepository;
import com.github.znoque.pethope.security.TokenService;
import jakarta.persistence.EntityNotFoundException;
import org.antlr.v4.runtime.Token;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                AuthenticationManager authenticationManager, TokenService tokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    public List<User> listAllUser(){
        return userRepository.findAll();
    }

    public Optional<User> listByIdUser(String id){
        return Optional.ofNullable(userRepository.findById(id))
                .orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado: "+ id));
    }

    public String authenticate(AuthResquestDto data) {

        var userNamePassword = new UsernamePasswordAuthenticationToken(data.username(),data.password());
        var auth = this.authenticationManager.authenticate(userNamePassword);
        String token = tokenService.generateToken((User) auth.getPrincipal());
        User user = userRepository.findByUsername(data.username())
                .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado"));
        return token;

    }


    public User saveUser(UserRequestDto data) {
        userRepository.findByUsername(data.email())
                .ifPresent(existingUser -> {
                    throw new DataIntegrityViolationException("Usuário já criado com o e-mail: " + data.email());
                });
        System.out.println(data.tipo());
        User user = new User(
                data.cpf(),
                data.responsavelNome(),
                data.telefone(),
                data.cidade(),
                data.endereco(),
                data.email(),
                passwordEncoder.encode(data.password()),
                data.tipo()
        );
        return userRepository.save(user);
    }

    public User saveClinicaOrOng(ClinicaOrOngRequestDto data) {
        userRepository.findByUsername(data.email())
                .ifPresent(existingUser -> {
                    throw new DataIntegrityViolationException("Ong já criado com o e-mail: " + data.email());
                });
        User user = new User(
                data.cnpj(),
                data.responsavelNome(),
                data.telefone(),
                data.cidade(),
                data.endereco(),
                data.razaoSocial(),
                data.email(),
                passwordEncoder.encode(data.senha()),
                data.site(),
                data.urlFacebook(),
                data.urlInstagram(),
                data.tipo(),
                data.isPrestadorServico()
        );
        System.out.println("ClinicaOrOngRequestDto "+data);
        return userRepository.save(user);
    }

}
