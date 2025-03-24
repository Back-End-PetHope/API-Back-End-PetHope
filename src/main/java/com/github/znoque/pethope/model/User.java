package com.github.znoque.pethope.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int userId;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "user_tipo", nullable = false)
    private TypeUser userTipo;

    @Column(name = "user_cpf_cnpj", nullable = false, unique = true, length = 14)
    @NotNull
    private String userCpfCnpj;

    @Column(name = "user_nome", nullable = false, length = 255)
    @NotNull
    private String userNome;

    @Column(name = "user_nome_fantasia", length = 255)
    private String userNomeFantasia;

    @Column(name = "user_responsavel_nome", length = 255)
    private String userResponsavelNome;

    @Column(name = "user_responsavel_cpf", length = 11)
    private String userResponsavelCpf;

    @Column(name = "user_telefone", nullable = false, length = 14)
    @NotNull
    private String userTelefone;

    @Column(name = "user_email", nullable = false, length = 255)
    @NotNull
    private String userEmail;

    @Column(name = "user_logradouro", length = 255)
    private String userLogradouro;

    @Column(name = "user_numero", nullable = false, length = 10)
    @NotNull
    private String userNumero;

    @Column(name = "user_cidade", nullable = false, length = 150)
    @NotNull
    private String userCidade;

    @Column(name = "user_estado", nullable = false, length = 2)
    @NotNull
    private String userEstado;

    @Column(name = "user_cep", nullable = false, length = 9)
    @NotNull
    private String userCep;

    @Column(name = "user_is_prestador_servico", nullable = false)
    private Boolean userIsPrestadorServico = false;

    @Column(name = "user_site", length = 255)
    private String userSite;

    @Column(name = "user_url_instagram", length = 255)
    private String userUrlInstagram;

    @Column(name = "user_url_facebook", length = 255)
    private String userUrlFacebook;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public TypeUser getUserTipo() {
        return userTipo;
    }

    public void setUserTipo(TypeUser userTipo) {
        this.userTipo = userTipo;
    }

    public String getUserCpfCnpj() {
        return userCpfCnpj;
    }

    public void setUserCpfCnpj(String userCpfCnpj) {
        this.userCpfCnpj = userCpfCnpj;
    }

    public String getUserNome() {
        return userNome;
    }

    public void setUserNome(String userNome) {
        this.userNome = userNome;
    }

    public String getUserNomeFantasia() {
        return userNomeFantasia;
    }

    public void setUserNomeFantasia(String userNomeFantasia) {
        this.userNomeFantasia = userNomeFantasia;
    }

    public String getUserResponsavelNome() {
        return userResponsavelNome;
    }

    public void setUserResponsavelNome(String userResponsavelNome) {
        this.userResponsavelNome = userResponsavelNome;
    }

    public String getUserResponsavelCpf() {
        return userResponsavelCpf;
    }

    public void setUserResponsavelCpf(String userResponsavelCpf) {
        this.userResponsavelCpf = userResponsavelCpf;
    }

    public String getUserTelefone() {
        return userTelefone;
    }

    public void setUserTelefone(String userTelefone) {
        this.userTelefone = userTelefone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserLogradouro() {
        return userLogradouro;
    }

    public void setUserLogradouro(String userLogradouro) {
        this.userLogradouro = userLogradouro;
    }

    public String getUserNumero() {
        return userNumero;
    }

    public void setUserNumero(String userNumero) {
        this.userNumero = userNumero;
    }

    public String getUserCidade() {
        return userCidade;
    }

    public void setUserCidade(String userCidade) {
        this.userCidade = userCidade;
    }

    public String getUserEstado() {
        return userEstado;
    }

    public void setUserEstado(String userEstado) {
        this.userEstado = userEstado;
    }

    public String getUserCep() {
        return userCep;
    }

    public void setUserCep(String userCep) {
        this.userCep = userCep;
    }

    public Boolean getUserIsPrestadorServico() {
        return userIsPrestadorServico;
    }

    public void setUserIsPrestadorServico(Boolean userIsPrestadorServico) {
        this.userIsPrestadorServico = userIsPrestadorServico;
    }

    public String getUserSite() {
        return userSite;
    }

    public void setUserSite(String userSite) {
        this.userSite = userSite;
    }

    public String getUserUrlInstagram() {
        return userUrlInstagram;
    }

    public void setUserUrlInstagram(String userUrlInstagram) {
        this.userUrlInstagram = userUrlInstagram;
    }

    public String getUserUrlFacebook() {
        return userUrlFacebook;
    }

    public void setUserUrlFacebook(String userUrlFacebook) {
        this.userUrlFacebook = userUrlFacebook;
    }
}
