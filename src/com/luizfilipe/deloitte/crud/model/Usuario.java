package com.luizfilipe.deloitte.crud.model;

public class Usuario {

        private Long id;
        private String nome;
        private String email;

        // Construtor vazio
        public Usuario() {
        }

        // Construtor completo
        //adicionando válidação no método constutor
        // Construtor completo com validação
        public Usuario(Long id, String nome, String email) {

            if (nome == null || nome.isBlank()) {
                throw new IllegalArgumentException("Nome não pode ser vazio");
            }

            if (email == null || email.isBlank()) {
                throw new IllegalArgumentException("Email não pode ser vazio");
            }

            this.id = id;
            this.nome = nome;
            this.email = email;
        }


    // Getters e Setters
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }


}
