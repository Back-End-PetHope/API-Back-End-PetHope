package com.github.znoque.pethope.model.pet;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tb_pet")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pet_id")
    private int id;

    @Column(name = "pet_nome", nullable = false, length = 50)
    @NotNull
    private String nome;

    @Column(name = "pet_descricao", nullable = false, length = 255)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(name = "pet_especie")
    @NotNull
    private Especie especie;

    @Enumerated(EnumType.STRING)
    @Column(name="pet_raca", nullable = false)
    @NotNull
    private Raca raca;

    @Column(name = "pet_idade")
    private int idade;

    @Enumerated(EnumType.STRING)
    @Column(name = "pet_sexo", nullable = false)
    @NotNull
    private Sexo sexo;

    @Column(name = "pet_ativo")
    private boolean ativo = true;

    @Column(name = "pet_status")
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

    public Raca getRaca() {
        return raca;
    }

    public void setRaca(Raca raca) {
        this.raca = raca;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }
}
