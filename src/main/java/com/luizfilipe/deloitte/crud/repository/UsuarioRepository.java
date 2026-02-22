package com.luizfilipe.deloitte.crud.repository;

import com.luizfilipe.deloitte.crud.model.Usuario;
import com.luizfilipe.deloitte.crud.util.JPAUtil;

import jakarta.persistence.EntityManager;
import java.util.List;

public class UsuarioRepository {

    // CREATE
    public Usuario salvar(Usuario usuario){

        EntityManager em = JPAUtil.getEntityManager();

        try {

            em.getTransaction().begin();

            em.persist(usuario);

            em.getTransaction().commit();

            return usuario;

        } finally {

            em.close();

        }

    }


    // READ (agora lista TODOS)
    public List<Usuario> listar(){

        EntityManager em = JPAUtil.getEntityManager();

        try {

            return em.createQuery(
                    "SELECT u FROM Usuario u",
                    Usuario.class
            ).getResultList();

        } finally {

            em.close();

        }

    }


    // READ BY ID
    public Usuario buscarPorId(Long id){

        EntityManager em = JPAUtil.getEntityManager();

        try {

            return em.find(Usuario.class, id);

        } finally {

            em.close();

        }

    }


    // UPDATE
    public void atualizar(Usuario usuario){

        EntityManager em = JPAUtil.getEntityManager();

        try {

            em.getTransaction().begin();

            em.merge(usuario);

            em.getTransaction().commit();

        } finally {

            em.close();

        }

    }


    // DELETE FÍSICO (REMOVE DO BANCO)
    public void remover(Long id){

        EntityManager em = JPAUtil.getEntityManager();

        try {

            Usuario usuario = em.find(Usuario.class, id);

            if(usuario != null){

                em.getTransaction().begin();

                em.remove(usuario);

                em.getTransaction().commit();

            }

        } finally {

            em.close();

        }

    }

}