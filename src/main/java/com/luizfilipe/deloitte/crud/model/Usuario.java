package com.luizfilipe.deloitte.crud.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String nome;


    @Column(nullable = false)
    private String email;


    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;


    public Usuario(){}


    public Usuario(Long id, String nome, String email){

        if (nome == null || nome.isBlank())
            throw new IllegalArgumentException("Nome obrigatório");

        if (email == null || email.isBlank())
            throw new IllegalArgumentException("Email obrigatório");

        this.id = id;
        this.nome = nome;
        this.email = email;
    }


    @PrePersist
    public void prePersist(){

        this.dataCriacao = LocalDateTime.now();

    }


    // getters setters

    public Long getId(){ return id; }

    public String getNome(){ return nome; }

    public String getEmail(){ return email; }

    public LocalDateTime getDataCriacao(){ return dataCriacao; }

}