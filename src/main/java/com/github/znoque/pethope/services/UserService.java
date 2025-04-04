package com.github.znoque.pethope.services;

import com.github.znoque.pethope.dto.clinica.ClinicaOrOngRequestDto;
import com.github.znoque.pethope.dto.user.AuthResquestDto;
import com.github.znoque.pethope.dto.user.UserRequestDto;
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

    public AuthResquestDto authenticate(AuthResquestDto data) {
        User user = userRepository.findByEmail(data.email())
                .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado"));

        if (!passwordEncoder.matches(data.password(), user.getSenha())) {
            throw new BadCredentialsException("Senha inválida");
        }

        return new AuthResquestDto(user.getEmail(), user.getSenha());
    }


    public User saveUser(UserRequestDto data) {
        userRepository.findByEmail(data.email())
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
        userRepository.findByEmail(data.email())
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
