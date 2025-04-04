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
    @Size(max=14)
    @Column(name = "usuario_cpf_cnpj", nullable = false, unique = true)
    private String cpfCnpj;

//    @NotBlank
//    @NotNull
//    @Column(name = "usuario_nome", nullable = false)
//    private String nome;

//    @Column(name = "usuario_nome_fantasia")
//    private String nomeFantasia;

    @Column(name = "usuario_razao_social", unique = true)
    private String razaoSocial;

    @NotBlank
    @NotNull
    @Column(name = "usuario_responsavel_nome",nullable = false)
    private String responsavelNome;

//    @Size(max=11)
//    @Column(name = "usuario_responsavel_cpf")
//    private String responsavelCpf;

    //@NotBlank
    //@NotNull
    @Size(max=14)
    @Column(name = "usuario_telefone", nullable = false)
    private String telefone;

    @Column(name = "usuario_logradouro")
    private String logradouro;

//    @NotBlank
//    @NotNull
//    @Size(max=10)
//    @Column(name = "usuario_numero", nullable = false)
//    private String numero;

    //@NotBlank
    //@NotNull
    @Size(max=150)
    @Column(name = "usuario_cidade", nullable = false)
    private String cidade;

//    @NotBlank
//    @NotNull
//    @Size(max=2)
//    @Column(name = "usuario_estado", nullable = false, length = 2)
//    private String estado;

//    @NotBlank
//    @NotNull
//    @Size(max=9)
//    @Column(name = "usuario_cep", nullable = false)
//    private String cep;

    @Column(name = "usuario_is_prestador_servico",nullable = false)
    private Boolean isPrestadorServico;

    @Column(name = "usuario_site")
    private String site;

    @Column(name = "usuario_url_instagram")
    private String urlInstagram;

    @Column(name = "usuario_url_facebook")
    private String urlFacebook;



    public User(String cpfCnpj,String responsavelNome,String telefone,
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

    public User(String cpfCnpj,String responsavelNome,
                String telefone, String cidade, String logradouro, String razaoSocial,
                String email,String senha, String site, String urlInstagram, String urlFacebook,
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
        this.isPrestadorServico = this.isPrestadorServico != null ? isPrestadorServico : false;
    }

    @Deprecated
    public User(){

    }

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

//    public String getNome() {
//        return nome;
//    }

//    public String getNomeFantasia() {
//        return nomeFantasia;
//    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public String getResponsavelNome() {
        return responsavelNome;
    }

//    public String getResponsavelCpf() {
//        return responsavelCpf;
//    }

    public String getTelefone() {
        return telefone;
    }

    public String getLogradouro() {
        return logradouro;
    }

//    public String getNumero() {
//        return numero;
//    }

    public String getCidade() {
        return cidade;
    }

//    public String getEstado() {
//        return estado;
//    }
//
//    public String getCep() {
//        return cep;
//    }

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

}
