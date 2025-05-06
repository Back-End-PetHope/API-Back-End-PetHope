package com.github.znoque.pethope.model;

import com.github.znoque.pethope.dto.pet.PetRequestDto;
import com.github.znoque.pethope.enums.Especie;
import com.github.znoque.pethope.enums.Raca;
import com.github.znoque.pethope.enums.Sexo;
import com.github.znoque.pethope.enums.Temperamento;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;


@Entity
@Table(name = "pet")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pet_id")
    private int id;

    @NotBlank
    @Size(max = 50)
    @Column(name = "pet_nome", nullable = false, length = 50)
    private String nome;

    @Enumerated(EnumType.STRING)
    @NotBlank
    @Column(name = "pet_especie", nullable = false)
    private Especie especie;

    @Enumerated(EnumType.STRING)
    @NotBlank
    @Column(name = "pet_raca", nullable = false)
    private Raca raca;

    @Column(name = "pet_idade", nullable = false)
    private int idade;

    @Enumerated(EnumType.STRING)
    @Column(name = "pet_temperamento")
    private Temperamento temperamento;

    @Column(name = "pet_ativo")
    private boolean ativo = true;

    @Column(name = "pet_status")
    private boolean disponivel = false;

    @NotBlank
    @Size(max = 255)
    @Column(name = "pet_descricao", nullable = false)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(name = "pet_sexo", nullable = false)
    private Sexo sexo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", nullable = false)
    private User usuario;

    @Deprecated
    public Pet() {
    }

    private Pet(
            String nome,
            String descricao,
            Especie especie,
            Raca raca,
            int idade,
            Sexo sexo,
            Temperamento temperamento,
            boolean ativo,
            boolean disponivel,
            User usuario) {
        this.nome = nome;
        this.descricao = descricao;
        this.especie = especie;
        this.raca = raca;
        this.idade = idade;
        this.sexo = sexo;
        this.temperamento = temperamento;
        this.ativo = ativo;
        this.disponivel = disponivel;
        this.usuario = usuario;
    }

    public void inativar() {
        this.ativo = false;
    }

    public void atualizarCom(PetRequestDto dto) {
        this.nome = dto.nome();
        this.descricao = dto.descricao();
        this.especie = dto.especie();
        this.raca = dto.raca();
        this.idade = dto.idade();
        this.sexo = dto.sexo();
        this.temperamento = dto.temperamento();
        this.ativo = dto.ativo();
        this.disponivel = dto.disponivel();
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Especie getEspecie() {
        return especie;
    }

    public Raca getRaca() {
        return raca;
    }

    public int getIdade() {
        return idade;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public Temperamento getTemperamento() {
        return temperamento;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public User getUsuario() { return usuario; }

    public void setUsuario(User usuario) { this.usuario = usuario;}

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", especie=" + especie +
                ", raca=" + raca +
                ", idade=" + idade +
                ", sexo=" + sexo +
                ", temperamento=" + temperamento +
                ", ativo=" + ativo +
                ", disponivel=" + disponivel +
                '}';
    }

    public static class Builder {
        private String nome;
        private String descricao;
        private Especie especie;
        private Raca raca;
        private int idade;
        private Sexo sexo;
        private Temperamento temperamento;
        private boolean ativo = true;
        private boolean disponivel = false;
        private User usuario;

        public Builder comNome(String nome) {
            this.nome = nome;
            return this;
        }

        public Builder comDescricao(String descricao) {
            this.descricao = descricao;
            return this;
        }

        public Builder comEspecie(Especie especie) {
            this.especie = especie;
            return this;
        }

        public Builder comRaca(Raca raca) {
            this.raca = raca;
            return this;
        }

        public Builder comIdade(int idade) {
            this.idade = idade;
            return this;
        }

        public Builder comSexo(Sexo sexo) {
            this.sexo = sexo;
            return this;
        }

        public Builder comTemperamento(Temperamento temperamento) {
            this.temperamento = temperamento;
            return this;
        }

        public Builder estaAtivo(boolean ativo) {
            this.ativo = ativo;
            return this;
        }

        public Builder estaDisponivel(boolean disponivel) {
            this.disponivel = disponivel;
            return this;
        }

        public Builder comUsuario(User usuario) {
            this.usuario = usuario;
            return this;
        }

        public Pet build() {
            return new Pet(nome, descricao, especie, raca, idade, sexo, temperamento, ativo, disponivel, usuario);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
