package com.github.znoque.pethope.model;


import com.github.znoque.pethope.dto.user.UserUpdateRequestDto;
import com.github.znoque.pethope.dto.user.clinica.ClinicaRequestDto;
import com.github.znoque.pethope.dto.user.ong.OngRequestDto;

import com.github.znoque.pethope.enums.UsuarioTipo;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "usuario")
public class User implements UserDetails {

  @Id

  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(name = "usuario_id", updatable = false, nullable = false)
  private String id;

  @Email
  @NotBlank
  @Size(min = 11, max = 155)
  @Column(name = "usuario_username", length = 155, nullable = false, unique = true)
  private String username;

  @NotBlank
  @Size(min = 6, max = 255)
  @Column(name = "usuario_senha", nullable = false, columnDefinition = "VARCHAR(255) COLLATE utf8mb4_bin")
  private String senha;

  @NotNull
  @Column(name = "usuario_tipo", nullable = false)
  @Enumerated(EnumType.STRING)
  private UsuarioTipo tipo = UsuarioTipo.USUARIO;

  @NotBlank
  @Size(max = 14)
  @Column(name = "usuario_cpf_cnpj", nullable = false, unique = true)
  private String cpfCnpj;

  @Column(name = "usuario_razao_social", unique = true)
  @Size(max = 255)
  private String razaoSocial;

  @NotBlank
  @Size(max = 255)
  @Column(name = "usuario_responsavel_nome", nullable = false)
  private String responsavelNome;

  @Size(max = 14)
  @Column(name = "usuario_telefone", nullable = false)
  private String telefone;

  @Size(max = 255)
  @Column(name = "usuario_logradouro")
  private String logradouro;

  @Size(max = 150)
  @Column(name = "usuario_cidade", nullable = false)
  private String cidade;

  @Column(name = "usuario_is_prestador_servico", nullable = false)
  private boolean isPrestadorServico = false;

  @Column(name = "usuario_site")
  @Size(max = 255)
  private String site;

  @Column(name = "usuario_url_instagram")
  @Size(max = 255)
  private String urlInstagram;

  @Column(name = "usuario_url_facebook")
  @Size(max = 255)
  private String urlFacebook;

  @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Pet> pets = new ArrayList<>();


  public User(String username, String senha, UsuarioTipo tipo, String cpfCnpj, String razaoSocial,
              String responsavelNome, String telefone, String logradouro, String cidade,
              boolean isPrestadorServico, String site, String urlInstagram, String urlFacebook) {
    this.username = username;
    this.senha = senha;
    this.tipo = tipo;
    this.cpfCnpj = cpfCnpj;
    this.razaoSocial = razaoSocial;
    this.responsavelNome = responsavelNome;
    this.telefone = telefone;
    this.logradouro = logradouro;
    this.cidade = cidade;
    this.isPrestadorServico = isPrestadorServico;
    this.site = site;
    this.urlInstagram = urlInstagram;
    this.urlFacebook = urlFacebook;
  }

  @Deprecated
  public User() {
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

  public boolean getPrestadorServico() {
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

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    if (this.tipo == UsuarioTipo.USUARIO)
      return List.of(new SimpleGrantedAuthority("USER_COMUM"));
    if (this.tipo == UsuarioTipo.ONG)
      return List.of(new SimpleGrantedAuthority("USER_ONG"));
    else
      return List.of(new SimpleGrantedAuthority("USER_CLINICA"));
  }

  @Override
  public String getPassword() {
    return this.senha;
  }

  @Override
  public String getUsername() {
    return this.username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return UserDetails.super.isAccountNonExpired();
  }

  @Override
  public boolean isAccountNonLocked() {
    return UserDetails.super.isAccountNonLocked();
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return UserDetails.super.isCredentialsNonExpired();
  }

  @Override
  public boolean isEnabled() {
    return UserDetails.super.isEnabled();
  }

  @Override
  public String toString() {
    return "User{" +
            "id='" + id + '\'' +
            ", email='" + username + '\'' +
            ", tipo=" + tipo +
            ", cpfCnpj='" + cpfCnpj + '\'' +
            ", razaoSocial='" + razaoSocial + '\'' +
            ", responsavelNome='" + responsavelNome + '\'' +
            ", telefone='" + telefone + '\'' +
            ", logradouro='" + logradouro + '\'' +
            ", cidade='" + cidade + '\'' +
            ", isPrestadorServico=" + isPrestadorServico +
            ", site='" + site + '\'' +
            ", urlInstagram='" + urlInstagram + '\'' +
            ", urlFacebook='" + urlFacebook + '\'' +
            '}';
  }

  public void atualizaUsuarioCom(UserUpdateRequestDto userUpdateRequestDto, String senha) {
    this.cpfCnpj = userUpdateRequestDto.cpf();
    this.responsavelNome = userUpdateRequestDto.responsavelNome();
    this.telefone = userUpdateRequestDto.telefone();
    this.cidade = userUpdateRequestDto.cidade();
    this.logradouro = userUpdateRequestDto.endereco();
    this.username = userUpdateRequestDto.email();
    this.senha = senha;
  }

  public void atualizaClinicaCom(ClinicaRequestDto clinicaRequestDto, String senha) {
    this.cpfCnpj = clinicaRequestDto.cnpj();
    this.responsavelNome = clinicaRequestDto.responsavelNome();
    this.telefone = clinicaRequestDto.telefone();
    this.cidade = clinicaRequestDto.cidade();
    this.logradouro = clinicaRequestDto.endereco();
    this.razaoSocial = clinicaRequestDto.razaoSocial();
    this.site = clinicaRequestDto.site();
    this.urlFacebook = clinicaRequestDto.urlFacebook();
    this.urlInstagram = clinicaRequestDto.urlInstagram();
    this.username = clinicaRequestDto.email();
    this.senha = senha;
  }

  public void atualizaOngCom(OngRequestDto ongRequestDto, String senha) {
    this.cpfCnpj = ongRequestDto.cnpj();
    this.responsavelNome = ongRequestDto.responsavelNome();
    this.telefone = ongRequestDto.telefone();
    this.cidade = ongRequestDto.cidade();
    this.logradouro = ongRequestDto.endereco();
    this.razaoSocial = ongRequestDto.razaoSocial();
    this.site = ongRequestDto.site();
    this.urlFacebook = ongRequestDto.urlFacebook();
    this.urlInstagram = ongRequestDto.urlInstagram();
    this.username = ongRequestDto.email();
    this.senha = senha;
  }

  public static class Builder {
    private String username;
    private String senha;
    private UsuarioTipo tipo = UsuarioTipo.USUARIO;
    private String cpfCnpj;
    private String razaoSocial;
    private String responsavelNome;
    private String telefone;
    private String logradouro;
    private String cidade;
    private boolean isPrestadorServico = false;
    private String site;
    private String urlInstagram;
    private String urlFacebook;

    public Builder comUsername(String username) {
      this.username = username;
      return this;
    }

    public Builder comSenha(String senha) {
      this.senha = senha;
      return this;
    }

    public Builder doTipo(UsuarioTipo tipo) {
      this.tipo = tipo;
      return this;
    }

    public Builder comCpfCnpj(String cpfCnpj) {
      this.cpfCnpj = cpfCnpj;
      return this;
    }

    public Builder comRazaoSocial(String razaoSocial) {
      this.razaoSocial = razaoSocial;
      return this;
    }

    public Builder comResponsavelNome(String responsavelNome) {
      this.responsavelNome = responsavelNome;
      return this;
    }

    public Builder comTelefone(String telefone) {
      this.telefone = telefone;
      return this;
    }

    public Builder comLogradouro(String logradouro) {
      this.logradouro = logradouro;
      return this;
    }

    public Builder comCidade(String cidade) {
      this.cidade = cidade;
      return this;
    }

    public Builder doTipoPrestadorServico(boolean isPrestadorServico) {
      this.isPrestadorServico = isPrestadorServico;
      return this;
    }

    public Builder comSite(String site) {
      this.site = site;
      return this;
    }

    public Builder comUrlInstagram(String urlInstagram) {
      this.urlInstagram = urlInstagram;
      return this;
    }

    public Builder comUrlFacebook(String urlFacebook) {
      this.urlFacebook = urlFacebook;
      return this;
    }

    public User build() {
      return new User(username, senha, tipo, cpfCnpj, razaoSocial, responsavelNome,
        telefone, logradouro, cidade, isPrestadorServico, site, urlInstagram, urlFacebook);
    }
  }

  public static User.Builder builder() {
    return new User.Builder();
  }
}