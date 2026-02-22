package com.luizfilipe.deloitte.crud.service;

import com.luizfilipe.deloitte.crud.model.Usuario;
import com.luizfilipe.deloitte.crud.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public UsuarioService(){
        this.repository = new UsuarioRepository();

    }

    public Usuario criarUsuario(Usuario usuario) {
            return repository.salvar(usuario);
    }


    public List<Usuario> listarUsuarios(){
        return repository.listar();

    }

    public Usuario buscarUsuarioPorId(Long id){
        return repository.buscarPorId(id);
    }

    public void atualizarUsuario(Usuario usuario){
        repository.salvar(usuario);
    }

    public void removerUsuario(Long id){
        repository.remover(id);
    }

}