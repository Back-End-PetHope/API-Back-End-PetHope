package com.github.znoque.pethope.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.znoque.pethope.enums.UsuarioTipo;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "usuario")
public class User implements UserDetails {

    @Id
    @Column(name = "usuario_id", updatable = false, nullable = false)
    private String id;

    @Email
    @NotBlank
    @NotNull
    @Size(min = 11, max = 155)
    @Column(name = "usuario_username", length = 155, nullable = false, unique = true)
    private String username;

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
    @Size(max=14)
    @Column(name = "usuario_cpf_cnpj", nullable = false, unique = true)
    private String cpfCnpj;

    @Column(name = "usuario_razao_social", unique = true)
    private String razaoSocial;

    @NotBlank
    @NotNull
    @Column(name = "usuario_responsavel_nome",nullable = false)
    private String responsavelNome;

    @Size(max=14)
    @Column(name = "usuario_telefone", nullable = false)
    private String telefone;

    @Column(name = "usuario_logradouro")
    private String logradouro;

    @Size(max=150)
    @Column(name = "usuario_cidade", nullable = false)
    private String cidade;

    @Column(name = "usuario_is_prestador_servico",nullable = false)
    private Boolean isPrestadorServico;

    @Column(name = "usuario_site")
    private String site;

    @Column(name = "usuario_url_instagram")
    private String urlInstagram;

    @Column(name = "usuario_url_facebook")
    private String urlFacebook;



    public User(String cpf, String responsavelNome, String telefone,
                String cidade, String logradouro,
                String username, String senha, UsuarioTipo tipo) {
        this.id = String.valueOf(UUID.randomUUID());
        this.cpfCnpj = cpf;
        this.responsavelNome = responsavelNome;
        this.telefone = telefone;
        this.cidade = cidade;
        this.logradouro = logradouro;
        this.username = username;
        this.senha = senha;
        this.tipo = tipo;
        this.isPrestadorServico = this.isPrestadorServico != null ? isPrestadorServico : false;
    }

    public User(String Cnpj, String responsavelNome,
                String telefone, String cidade, String logradouro, String razaoSocial,
                String username, String senha, String site, String urlInstagram, String urlFacebook,
                UsuarioTipo tipo, Boolean isPrestadorServico) {

        this.id = String.valueOf(UUID.randomUUID());
        this.cpfCnpj = Cnpj;
        this.responsavelNome = responsavelNome;
        this.telefone = telefone;
        this.cidade = cidade;
        this.logradouro = logradouro;
        this.razaoSocial = razaoSocial;
        this.username = username;
        this.senha = senha;
        this.site = site;
        this.urlInstagram = urlInstagram;
        this.urlFacebook = urlFacebook;
        this.tipo = tipo;
        this.isPrestadorServico = this.isPrestadorServico != null ? isPrestadorServico : false;
    }

    @Deprecated
    public User(){

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       if(this.tipo == UsuarioTipo.USUARIO) return List.of(new SimpleGrantedAuthority("USER_COMUM"));
       if(this.tipo == UsuarioTipo.ONG) return List.of(new SimpleGrantedAuthority("USER_ONG"));
       else return List.of(new SimpleGrantedAuthority("USER_CLINICA"));
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

}
