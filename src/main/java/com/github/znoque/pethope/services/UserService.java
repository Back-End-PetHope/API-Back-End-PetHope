package com.github.znoque.pethope.services;

import com.github.znoque.pethope.dto.user.GlobalUserResponseDto;
import com.github.znoque.pethope.dto.user.clinica.ClinicaRequestDto;
import com.github.znoque.pethope.dto.user.ong.OngRequestDto;
import com.github.znoque.pethope.dto.user.AuthResquestDto;
import com.github.znoque.pethope.dto.user.UserRequestDto;
import com.github.znoque.pethope.dto.user.UserResponseDto;
import com.github.znoque.pethope.dto.user.UserUpdateRequestDto;
import com.github.znoque.pethope.enums.UsuarioTipo;
import com.github.znoque.pethope.mapper.UserMapper;
import com.github.znoque.pethope.model.User;
import com.github.znoque.pethope.repository.UserRepository;
import com.github.znoque.pethope.security.TokenService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder,
                AuthenticationManager authenticationManager, TokenService tokenService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    public List<GlobalUserResponseDto> listAllUser() {
        List<User> lista = userRepository.findAll();
        return lista.stream().map(userMapper::toGlobalUserResponseDto).toList();
    }

    public UserResponseDto findUserById(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado."));
        if (!user.getTipo().equals(UsuarioTipo.USUARIO)) {
            throw new IllegalArgumentException("ID inválido para essa operação.");
        } else {
            return userMapper.toUserResponseDto(user);
        }
    }

    public GlobalUserResponseDto findClinicaById(String id) {
        User clinica = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Clínica não encontrada."));
        if (!clinica.getTipo().equals(UsuarioTipo.CLINICA)) {
            throw new IllegalArgumentException("ID inválido para essa operação.");
        } else {
            return userMapper.toGlobalUserResponseDto(clinica);
        }

    }

    public GlobalUserResponseDto findOngById(String id) {
        User ong = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ong não encontrada: " + id));
        if (!ong.getTipo().equals(UsuarioTipo.ONG)) {
            throw new IllegalArgumentException("ID inválido para essa operação.");
        } else {
            return userMapper.toGlobalUserResponseDto(ong);
        }

    }

    public String authenticate(AuthResquestDto data) {

        var userNamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(userNamePassword);
        String token = tokenService.generateToken((User) auth.getPrincipal());
        User user = userRepository.findByUsername(data.email())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        return token;
    }

    public UserResponseDto saveUser(UserRequestDto data) {
        userRepository.findByUsername(data.email())
                .ifPresent(existingUser -> {
                    throw new DataIntegrityViolationException("Usuário já criado com o e-mail: " + data.email());
                });
        String senha = passwordEncoder.encode(data.password());

        User user = userMapper.toUser(data, senha);

        User persistedUser = userRepository.save(user);

        return userMapper.toUserResponseDto(persistedUser);
    }

    public GlobalUserResponseDto saveOng(OngRequestDto data) {
        userRepository.findByUsername(data.email())
                .ifPresent(existingUser -> {
                    throw new DataIntegrityViolationException("Ong já criada com o e-mail: " + data.email());
                });

        String senha = passwordEncoder.encode(data.senha());

        User user = userMapper.toUserOng(data, senha);

        User persistedUser = userRepository.save(user);

        return userMapper.toGlobalUserResponseDto(persistedUser);
    }

    public GlobalUserResponseDto saveClinica(ClinicaRequestDto data) {
        userRepository.findByUsername(data.email())
                .ifPresent(existingUser -> {
                    throw new DataIntegrityViolationException("Clinica já criada com o e-mail: " + data.email());
                });

        String senha = passwordEncoder.encode(data.senha());

        User user = userMapper.toUserClinica(data, senha);

        User persistedUser = userRepository.save(user);
        return userMapper.toGlobalUserResponseDto(persistedUser);
    }

    public UserResponseDto updateUser(String id, UserUpdateRequestDto userUpdateRequestDto) {
      User user = userRepository.findById(id)
          .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado."));

      userRepository.findByUsername(userUpdateRequestDto.email())
          .ifPresent(existingUser -> {
            throw new DataIntegrityViolationException("O nome de usuário já está em uso");
          });

      user.atualizaUsuarioCom(userUpdateRequestDto, passwordEncoder.encode(userUpdateRequestDto.password()));

      User updatedUser = userRepository.save(user);

      return userMapper.toUserResponseDto(updatedUser);
    }
    public GlobalUserResponseDto updateClinica(String id, ClinicaRequestDto clinicaRequestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Clínica não encontrada."));

        userRepository.findByUsername(clinicaRequestDto.email())
                .ifPresent(existingUser -> {
                    throw new DataIntegrityViolationException("O nome de usuário já está em uso");
                });

        user.atualizaClinicaCom(clinicaRequestDto, passwordEncoder.encode(clinicaRequestDto.senha()));

        User updatedUser = userRepository.save(user);

        return userMapper.toGlobalUserResponseDto(updatedUser);
    }
    public GlobalUserResponseDto updateOng(String id, OngRequestDto ongRequestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ong não encontrada."));

        userRepository.findByUsername(ongRequestDto.email())
                .ifPresent(existingUser -> {
                    throw new DataIntegrityViolationException("O nome de usuário já está em uso");
                });

        user.atualizaOngCom(ongRequestDto, passwordEncoder.encode(ongRequestDto.senha()));

        User updatedUser = userRepository.save(user);

        return userMapper.toGlobalUserResponseDto(updatedUser);
    }

    public void deleteUser(String id) {
      User user = userRepository.findById(id)
          .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
      userRepository.delete(user);
    }
    public void deleteClinica(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Clinica não encontrada"));
        userRepository.delete(user);
    }
    public void deleteOng(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ong não encontrada"));
        userRepository.delete(user);
    }
}