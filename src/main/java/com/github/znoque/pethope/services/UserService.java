package com.github.znoque.pethope.services;

import com.github.znoque.pethope.dto.GlobalResponseDto;
import com.github.znoque.pethope.dto.clinica.ClinicaRequestDto;
import com.github.znoque.pethope.dto.ong.OngRequestDto;
import com.github.znoque.pethope.dto.user.AuthResquestDto;
import com.github.znoque.pethope.dto.user.UserRequestDto;
import com.github.znoque.pethope.dto.user.UserResponseDto;
import com.github.znoque.pethope.enums.UsuarioTipo;
import com.github.znoque.pethope.model.User;
import com.github.znoque.pethope.repository.UserRepository;
import com.github.znoque.pethope.security.TokenService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
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

    public List<GlobalResponseDto> listAllUser(){
        List<User> lista = userRepository.findAll();
        return lista.stream().map(this::toGlocalResponseDto).toList();
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
        User user = new User(
                data.cpf(),
                data.responsavelNome(),
                data.telefone(),
                data.cidade(),
                data.endereco(),
                data.email(),
                passwordEncoder.encode(data.password()),
                UsuarioTipo.USUARIO
        );
        return userRepository.save(user);
    }

    public User saveOng(OngRequestDto data) {
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
                UsuarioTipo.ONG,
                data.isPrestadorServico()
        );
        return userRepository.save(user);
    }

    public User saveClinica(ClinicaRequestDto data) {
        userRepository.findByUsername(data.email())
                .ifPresent(existingUser -> {
                    throw new DataIntegrityViolationException("Clinica já criado com o e-mail: " + data.email());
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
                UsuarioTipo.CLINICA,
                data.isPrestadorServico()
        );
        return userRepository.save(user);
    }

    public UserResponseDto toUserResponseDto(User user) {

        List<String> authorities = user.getAuthorities()
                .stream().map(GrantedAuthority::getAuthority).toList();
        return new UserResponseDto(
                user.getId(),
                user.getUsername(),
                user.getTipo(),
                user.getCpfCnpj(),
                user.getResponsavelNome(),
                user.getTelefone(),
                user.getLogradouro(),
                user.getCidade(),
                user.getPrestadorServico(),
                authorities
        );
    }

    public GlobalResponseDto toGlocalResponseDto(User user) {

        List<String> authorities = user.getAuthorities()
                .stream().map(GrantedAuthority::getAuthority).toList();
        return new GlobalResponseDto(
                user.getId(),
                user.getUsername(),
                user.getTipo(),
                user.getCpfCnpj(),
                user.getRazaoSocial(),
                user.getResponsavelNome(),
                user.getTelefone(),
                user.getLogradouro(),
                user.getCidade(),
                user.getSite(),
                user.getUrlInstagram(),
                user.getUrlFacebook(),
                user.getPrestadorServico(),
                authorities
        );
    }




}
