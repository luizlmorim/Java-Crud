package com.luizfilipe.deloitte.crud.repository;

import com.luizfilipe.deloitte.crud.model.Usuario;
import com.luizfilipe.deloitte.crud.model.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsuarioRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Usuario salvar(Usuario usuario){
        if(usuario.getId() == null){
            entityManager.persist((usuario));
            return usuario;
        } else {
            return  entityManager.merge(usuario);
        }
    }

    public List<Usuario> listar(){
        return entityManager.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
    }

    public Usuario buscarPorId(Long id){
        return entityManager.find(Usuario.class, id);
    }

    @Transactional
    public void remover(Long id){
        Usuario u = buscarPorId(id);
        if(u != null){
            entityManager.remove(u);
        }

    }

}