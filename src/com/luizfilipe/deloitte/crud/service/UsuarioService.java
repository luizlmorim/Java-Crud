package com.luizfilipe.deloitte.crud.service;

import com.luizfilipe.deloitte.crud.model.Usuario;
import com.luizfilipe.deloitte.crud.repository.UsuarioRepository;

import java.util.List;

public class UsuarioService {

    private UsuarioRepository repository;

    public UsuarioService(){
        this.repository = new UsuarioRepository();
        this.repository.criarTabela();
    }

    //CRIAR USUÁRIO
    public void criarUsuario(Usuario usuario) {
        if (usuario.getNome() == null || usuario.getNome().isBlank()) {
            System.out.println("Nome do usuário é obrigatório.");
            return;
        }

        if (usuario.getEmail() == null || usuario.getEmail().isBlank()) {
            System.out.println("Email do usuário é obrigatório.");
            return;
        }

        repository.salvar(usuario);
        System.out.println("Usuário criado com sucesso!");
    }

    // lista usuários
    public List<Usuario> listarUsuarios() {
        return repository.listar();
    }

    //busca por id
    public Usuario buscarUsuarioPorId(Long id) {
        return repository.buscarPorId(id);
    }

    // ATUALIZAR USUÁRIO - service análisa se pode atualizar ou não
    public void atualizarUsuario(Usuario usuario) {
        Usuario existente = repository.buscarPorId(usuario.getId());

        if (existente == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        repository.atualizar(usuario);
        System.out.println("Usuário atualizado com sucesso!");
    }

    public void removerUsuario(Long id) {
        Usuario existente = repository.buscarPorId(id);

        if (existente == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        repository.remover(id);
        System.out.println("Usuário removido com sucesso!");
    }





}
