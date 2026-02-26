package com.luizfilipe.deloitte.crud.controller;

import com.luizfilipe.deloitte.crud.model.Usuario;
import com.luizfilipe.deloitte.crud.repository.UsuarioRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioRepository repository;

    public UsuarioController(UsuarioRepository repository) {
        this.repository = repository;
    }

    // LISTAR
    @GetMapping
    public String listar(Model model) {
        List<Usuario> usuarios = repository.listar();
        model.addAttribute("usuarios", usuarios);
        return "usuarios"; // nome do HTML
    }

    // ABRIR FORMULÁRIO
    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "form";
    }

    // SALVAR
    @PostMapping
    public String salvar(@ModelAttribute Usuario usuario) {
        repository.salvar(usuario);
        return "redirect:/usuarios";
    }

    // EDITAR
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Usuario usuario = repository.buscarPorId(id);
        model.addAttribute("usuario", usuario);
        return "form";
    }

    //Buscar por ID
    @GetMapping("/{id}")
    public String buscarPorId(@PathVariable Long id, Model model) {
        Usuario usuario = repository.buscarPorId(id);
        model.addAttribute("usuario", usuario);
        return "usuario-detalhe"; // nova página HTML
    }

    @GetMapping("/buscar")
    public String buscarPorIdForm(@RequestParam(required = false) Long id, Model model) {

        if (id == null) {
            model.addAttribute("erro", "Informe um ID para buscar.");
            return "usuarios"; // volta para lista
        }

        Usuario usuario = repository.buscarPorId(id);

        if (usuario == null) {
            model.addAttribute("erro", "Usuário não encontrado.");
            return "usuarios";
        }

        model.addAttribute("usuario", usuario);
        return "usuario-detalhe";
    }

    // EXCLUIR
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        repository.remover(id);
        return "redirect:/usuarios";
    }
}