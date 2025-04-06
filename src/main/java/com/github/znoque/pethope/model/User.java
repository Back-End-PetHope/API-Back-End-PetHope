package com.github.znoque.pethope.model;

import com.github.znoque.pethope.enums.UsuarioTipo;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

@Entity
@Table(name = "usuario")
public class User {

  @Id
  @Column(name = "usuario_id", updatable = false, nullable = false)
  private String id;

  @Email
  @NotBlank
  @NotNull
  @Size(min = 11, max = 155)
  @Column(name = "usuario_email", length = 155, nullable = false, unique = true)
  private String email;

  @NotBlank
  @NotNull
  @Size(min = 6, max = 255)
  @Column(name = "usuario_senha", nullable = false)
  private String senha;

  @NotNull
  @Column(name = "usuario_tipo", nullable = false)
  @Enumerated(EnumType.STRING)
  private UsuarioTipo tipo;

  @NotBlank
  @NotNull
  @Size(max = 14)
  @Column(name = "usuario_cpf_cnpj", nullable = false, unique = true)
  private String cpfCnpj;

  @Column(name = "usuario_razao_social", unique = true)
  private String razaoSocial;

  @NotBlank
  @NotNull
  @Column(name = "usuario_responsavel_nome", nullable = false)
  private String responsavelNome;

  @Size(max = 14)
  @Column(name = "usuario_telefone", nullable = false)
  private String telefone;

  @Column(name = "usuario_logradouro")
  private String logradouro;

  @Size(max = 150)
  @Column(name = "usuario_cidade", nullable = false)
  private String cidade;

  @Column(name = "usuario_is_prestador_servico", nullable = false)
  private Boolean isPrestadorServico;

  @Column(name = "usuario_site")
  private String site;

  @Column(name = "usuario_url_instagram")
  private String urlInstagram;

  @Column(name = "usuario_url_facebook")
  private String urlFacebook;

  // Construtores
  public User(String cpfCnpj, String responsavelNome, String telefone,
      String cidade, String logradouro,
      String email, String senha, UsuarioTipo tipo) {
    this.id = String.valueOf(UUID.randomUUID());
    this.cpfCnpj = cpfCnpj;
    this.responsavelNome = responsavelNome;
    this.telefone = telefone;
    this.cidade = cidade;
    this.logradouro = logradouro;
    this.email = email;
    this.senha = senha;
    this.tipo = tipo;
    this.isPrestadorServico = this.isPrestadorServico != null ? isPrestadorServico : false;
  }

  public User(String cpfCnpj, String responsavelNome,
      String telefone, String cidade, String logradouro, String razaoSocial,
      String email, String senha, String site, String urlInstagram, String urlFacebook,
      UsuarioTipo tipo, boolean isPrestadorServico) {
    this.id = String.valueOf(UUID.randomUUID());
    this.cpfCnpj = cpfCnpj;
    this.responsavelNome = responsavelNome;
    this.telefone = telefone;
    this.cidade = cidade;
    this.logradouro = logradouro;
    this.razaoSocial = razaoSocial;
    this.email = email;
    this.senha = senha;
    this.site = site;
    this.urlInstagram = urlInstagram;
    this.urlFacebook = urlFacebook;
    this.tipo = tipo;
    this.isPrestadorServico = isPrestadorServico;
  }

  @Deprecated
  public User() {
  }

  // Getters
  public String getEmail() {
    return email;
  }

  public String getSenha() {
    return senha;
  }

  public String getId() {
    return id;
  }

  public UsuarioTipo getTipo() {
    return tipo;
  }

  public String getCpfCnpj() {
    return cpfCnpj;
  }

  public String getRazaoSocial() {
    return razaoSocial;
  }

  public String getResponsavelNome() {
    return responsavelNome;
  }

  public String getTelefone() {
    return telefone;
  }

  public String getLogradouro() {
    return logradouro;
  }

  public String getCidade() {
    return cidade;
  }

  public Boolean getPrestadorServico() {
    return isPrestadorServico;
  }

  public String getSite() {
    return site;
  }

  public String getUrlInstagram() {
    return urlInstagram;
  }

  public String getUrlFacebook() {
    return urlFacebook;
  }

  // Setters necessários para o updateUser
  public void setEmail(String email) {
    this.email = email;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public void setTipo(UsuarioTipo tipo) {
    this.tipo = tipo;
  }

  public void setCpfCnpj(String cpfCnpj) {
    this.cpfCnpj = cpfCnpj;
  }

  public void setRazaoSocial(String razaoSocial) {
    this.razaoSocial = razaoSocial;
  }

  public void setResponsavelNome(String responsavelNome) {
    this.responsavelNome = responsavelNome;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }

  public void setLogradouro(String logradouro) {
    this.logradouro = logradouro;
  }

  public void setCidade(String cidade) {
    this.cidade = cidade;
  }

  public void setPrestadorServico(Boolean isPrestadorServico) {
    this.isPrestadorServico = isPrestadorServico;
  }

  public void setSite(String site) {
    this.site = site;
  }

  public void setUrlInstagram(String urlInstagram) {
    this.urlInstagram = urlInstagram;
  }

  public void setUrlFacebook(String urlFacebook) {
    this.urlFacebook = urlFacebook;
  }
}