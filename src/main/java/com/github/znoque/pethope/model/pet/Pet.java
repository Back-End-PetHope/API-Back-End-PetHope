package com.github.znoque.pethope.model.pet;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tb_pet")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "name",nullable = false,length = 50)
    @NotNull
    private String nome;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Especie especie;

    @Column(name="raca",nullable = false,length = 50)
    @NotNull
    private String raca;

    @Column(name = "idade")
    private int idade;

    @Enumerated(EnumType.STRING)
    private Porte porte;

    @Column(name = "temperamento",nullable = false,length = 50)
    private String temperamento;

    @Column(name = "doenca",nullable = false)
    private String doenca;

    @Column(name = "necessidadeEspecifica",nullable = false)
    private String necessidadeEspecifica;

    @Column(name = "ativo")
    private boolean ativo = true;

    @Column(name = "disponibilidade")
    private boolean disponibilidade = true;

    //private User userID;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Porte getPorte() {
        return porte;
    }

    public void setPorte(Porte porte) {
        this.porte = porte;
    }

    public String getTemperamento() {
        return temperamento;
    }

    public void setTemperamento(String temperamento) {
        this.temperamento = temperamento;
    }

    public String getDoenca() {
        return doenca;
    }

    public void setDoenca(String doenca) {
        this.doenca = doenca;
    }

    public String getNecessidadeEspecifica() {
        return necessidadeEspecifica;
    }

    public void setNecessidadeEspecifica(String necessidadeEspecifica) {
        this.necessidadeEspecifica = necessidadeEspecifica;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public boolean isDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }
}
