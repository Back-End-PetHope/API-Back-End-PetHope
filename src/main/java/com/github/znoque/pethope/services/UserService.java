package com.github.znoque.pethope.services;

import com.github.znoque.pethope.dto.GlobalResponseDto;
import com.github.znoque.pethope.dto.clinica.ClinicaRequestDto;
import com.github.znoque.pethope.dto.ong.OngRequestDto;
import com.github.znoque.pethope.dto.user.AuthResquestDto;
import com.github.znoque.pethope.dto.user.UserRequestDto;
import com.github.znoque.pethope.dto.user.UserResponseDto;
import com.github.znoque.pethope.dto.user.UserUpdateRequestDto;
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
import java.util.stream.Collectors;

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

    public List<GlobalResponseDto> listAllUser() {
        List<User> lista = userRepository.findAll();
        return lista.stream().map(this::toGlobalResponseDto).toList();
    }

    public UserResponseDto listByIdUser(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado: " + id));
        if (!user.getTipo().equals(UsuarioTipo.USUARIO)) {
            throw new IllegalArgumentException("ID invalido para essa operação.");
        } else {
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
                    user.getAuthorities().stream()
                            .map(GrantedAuthority::getAuthority)
                            .collect(Collectors.toList())
            );
        }
    }

    public GlobalResponseDto listByIdClinica(String id) {
        User clinica = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Clinica não encontrada: " + id));
        if (!clinica.getTipo().equals(UsuarioTipo.CLINICA)) {
            throw new IllegalArgumentException("ID invalido para essa operação.");
        } else {
            return new GlobalResponseDto(
                    clinica.getId(),
                    clinica.getUsername(),
                    clinica.getTipo(),
                    clinica.getCpfCnpj(),
                    clinica.getRazaoSocial(),
                    clinica.getResponsavelNome(),
                    clinica.getTelefone(),
                    clinica.getLogradouro(),
                    clinica.getCidade(),
                    clinica.getSite(),
                    clinica.getUrlInstagram(),
                    clinica.getUrlFacebook(),
                    clinica.getPrestadorServico(),
                    clinica.getAuthorities().stream()
                            .map(GrantedAuthority::getAuthority)
                            .collect(Collectors.toList())
            );
        }

    }

    public GlobalResponseDto listByIdOng(String id) {
        User ong = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ong não encontrada: " + id));
        if (!ong.getTipo().equals(UsuarioTipo.ONG)) {
            throw new IllegalArgumentException("ID invalido para essa operação.");
        } else {
            return new GlobalResponseDto(
                    ong.getId(),
                    ong.getUsername(),
                    ong.getTipo(),
                    ong.getCpfCnpj(),
                    ong.getRazaoSocial(),
                    ong.getResponsavelNome(),
                    ong.getTelefone(),
                    ong.getLogradouro(),
                    ong.getCidade(),
                    ong.getSite(),
                    ong.getUrlInstagram(),
                    ong.getUrlFacebook(),
                    ong.getPrestadorServico(),
                    ong.getAuthorities().stream()
                            .map(GrantedAuthority::getAuthority)
                            .collect(Collectors.toList())
            );
        }

    }

    public String authenticate(AuthResquestDto data) {

        var userNamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(userNamePassword);
        String token = tokenService.generateToken((User) auth.getPrincipal());
        User user = userRepository.findByUsername(data.email())
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
                UsuarioTipo.USUARIO);
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
                data.isPrestadorServico());
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
                data.isPrestadorServico());
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
                authorities);
    }

    public GlobalResponseDto toGlobalResponseDto(User user) {

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
                authorities);
    }

    public User updateUser(String id, UserUpdateRequestDto data) {
      User user = userRepository.findById(id)
          .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado"));

      userRepository.findByUsername(data.email())
          .ifPresent(existingUser -> {
            throw new DataIntegrityViolationException("O nome de usuário já está em uso");
          });

      if (data.cpf() != null)
        user.setCpfCnpj(data.cpf());
      if (data.responsavelNome() != null)
        user.setResponsavelNome(data.responsavelNome());
      if (data.telefone() != null)
        user.setTelefone(data.telefone());
      if (data.cidade() != null)
        user.setCidade(data.cidade());
      if (data.endereco() != null)
        user.setLogradouro(data.endereco());
      if (data.email() != null)
        user.setUsername(data.email());
      if (data.password() != null)
        user.setSenha(passwordEncoder.encode(data.password()));

      return userRepository.save(user);
    }
    public User updateClinica(String id, ClinicaRequestDto data) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Clinica não encontrada"));

        userRepository.findByUsername(data.email())
                .ifPresent(existingUser -> {
                    throw new DataIntegrityViolationException("O nome de usuário já está em uso");
                });

        if (data.cnpj() != null)
            user.setCpfCnpj(data.cnpj());
        if (data.responsavelNome() != null)
            user.setResponsavelNome(data.responsavelNome());
        if (data.telefone() != null)
            user.setTelefone(data.telefone());
        if (data.cidade() != null)
            user.setCidade(data.cidade());
        if (data.endereco() != null)
            user.setLogradouro(data.endereco());
        if (data.razaoSocial() != null)
            user.setRazaoSocial(data.razaoSocial());
        if (data.site() != null)
            user.setSite(data.site());
        if (data.urlFacebook() != null)
            user.setUrlFacebook(data.urlFacebook());
        if (data.urlInstagram() != null)
            user.setUrlInstagram(data.urlInstagram());
        if (data.senha() != null)
            user.setSenha(passwordEncoder.encode(data.senha()));

        return userRepository.save(user);
    }
    public User updateOng(String id, OngRequestDto data) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Ong não encontrada"));

        userRepository.findByUsername(data.email())
                .ifPresent(existingUser -> {
                    throw new DataIntegrityViolationException("O nome de usuário já está em uso");
                });

        if (data.cnpj() != null)
            user.setCpfCnpj(data.cnpj());
        if (data.responsavelNome() != null)
            user.setResponsavelNome(data.responsavelNome());
        if (data.telefone() != null)
            user.setTelefone(data.telefone());
        if (data.cidade() != null)
            user.setCidade(data.cidade());
        if (data.endereco() != null)
            user.setLogradouro(data.endereco());
        if (data.razaoSocial() != null)
            user.setRazaoSocial(data.razaoSocial());
        if (data.site() != null)
            user.setSite(data.site());
        if (data.urlFacebook() != null)
            user.setUrlFacebook(data.urlFacebook());
        if (data.urlInstagram() != null)
            user.setUrlInstagram(data.urlInstagram());
        if (data.senha() != null)
            user.setSenha(passwordEncoder.encode(data.senha()));

        return userRepository.save(user);
    }

    public void deleteUser(String id) {
      User user = userRepository.findById(id)
          .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado"));
      userRepository.delete(user);
    }
    public void deleteClinica(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Clinica não encontrada"));
        userRepository.delete(user);
    }
    public void deleteOng(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Ong não encontrada"));
        userRepository.delete(user);
    }

}