package com.luizfilipe.deloitte.crud.service;

import com.luizfilipe.deloitte.crud.model.Usuario;
import com.luizfilipe.deloitte.crud.repository.UsuarioRepository;

import java.util.List;

public class UsuarioService {

    private UsuarioRepository repository;

    public UsuarioService(){

        this.repository = new UsuarioRepository();

    }

    public Usuario criarUsuario(Usuario usuario) {

        if (usuario.getNome() == null || usuario.getNome().isBlank()) {

            System.out.println("Nome obrigatório");

            return usuario;

        }

        if (usuario.getEmail() == null || usuario.getEmail().isBlank()) {

            System.out.println("Email obrigatório");

            return usuario;

        }

        repository.salvar(usuario);

        return usuario;

    }


    public List<Usuario> listarUsuarios(){

        return repository.listar();

    }


    public Usuario buscarUsuarioPorId(Long id){

        return repository.buscarPorId(id);

    }


    public void atualizarUsuario(Usuario usuario){

        repository.atualizar(usuario);

    }


    public void removerUsuario(Long id){

        repository.remover(id);

    }

}