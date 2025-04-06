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
    User user = new User(
        data.cpf(),
        data.responsavelNome(),
        data.telefone(),
        data.cidade(),
        data.endereco(),
        data.email(),
        passwordEncoder.encode(data.password()),
        data.tipo());
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
        data.isPrestadorServico());
    return userRepository.save(user);
  }

  public User updateUser(String id, UserRequestDto data) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado"));

    userRepository.findByEmail(data.email())
        .filter(existingUser -> !existingUser.getId().equals(id))
        .ifPresent(existingUser -> {
          throw new DataIntegrityViolationException("E-mail já está em uso por outro usuário: " + data.email());
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
      user.setEmail(data.email());
    if (data.password() != null)
      user.setSenha(passwordEncoder.encode(data.password()));
    if (data.tipo() != null)
      user.setTipo(data.tipo());

    return userRepository.save(user);
  }

  public void deleteUser(String id) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado"));
    userRepository.delete(user);
  }

}