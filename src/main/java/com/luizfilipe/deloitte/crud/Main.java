package com.luizfilipe.deloitte.crud;

import com.luizfilipe.deloitte.crud.model.Usuario;
import com.luizfilipe.deloitte.crud.service.UsuarioService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner run(UsuarioService service) {
        return args -> {
            Scanner scanner = new Scanner(System.in);
            int opcao = -1;

            while (opcao != 0) {
                System.out.println("\n=== MENU USUARIOS ===");
                System.out.println("1 - Criar usuario");
                System.out.println("2 - Listar usuarios");
                System.out.println("3 - Buscar usuario por ID");
                System.out.println("4 - Atualizar usuario");
                System.out.println("5 - Remover usuario");
                System.out.println("0 - Sair");
                System.out.print("Escolha uma opcao: ");

                String entradaOpcao = scanner.nextLine();
                try {
                    opcao = Integer.parseInt(entradaOpcao);
                } catch (NumberFormatException e) {
                    System.out.println("Erro: Digite apenas numeros!");
                    continue;
                }

                switch (opcao) {
                    case 1:
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();
                        System.out.print("Email: ");
                        String email = scanner.nextLine();

                        if (nome.isBlank() || email.isBlank()) {
                            System.out.println("Erro: Campos obrigatorios nao preenchidos!");
                        } else {
                            Usuario usuarioSalvo = service.criarUsuario(new Usuario(null, nome, email));
                            System.out.println("ID:  " + usuarioSalvo.getId() + " | Nome: " + usuarioSalvo.getNome() + "| Email: " + usuarioSalvo.getEmail());
                            System.out.println("Usuario criado com sucesso!");;
                        }
                        break;

                    case 2:
                        List<Usuario> usuarios = service.listarUsuarios();
                        if (usuarios.isEmpty()) {
                            System.out.println("Nenhum usuario encontrado.");
                        } else {
                            usuarios.forEach(u -> System.out.println("ID: " + u.getId() + " | Nome: " + u.getNome() + " | Email: " + u.getEmail()));
                        }
                        break;

                    case 3:
                        System.out.print("Digite o ID para buscar: ");
                        String idInputBusca = scanner.nextLine();
                        try {
                            Long idBusca = Long.parseLong(idInputBusca);
                            Usuario found = service.buscarUsuarioPorId(idBusca);
                            if (found != null) {
                                System.out.println("Usuario encontrado!");
                                System.out.println("ID:  " + found.getId() + " | Nome: " + found.getNome() + "| Email: " + found.getEmail());
                            } else {
                                System.out.println("Erro: Usuario nâo encontrado.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Erro: ID invalido!");
                        }
                        break;

                    case 4:
                        System.out.print("Digite o ID para atualizar: ");
                        String idInputUpd = scanner.nextLine();

                        try {
                            Long idUpd = Long.parseLong(idInputUpd);

                            Usuario usuarioExistente = service.buscarUsuarioPorId(idUpd);

                            if (usuarioExistente == null) {
                                System.out.println("Erro: Usuario com ID " + idUpd + " nao encontrado no sistema!");
                            } else {

                                System.out.print("Novo nome: ");
                                String nNome = scanner.nextLine();
                                System.out.print("Novo email: ");
                                String nEmail = scanner.nextLine();

                                if (nNome.isBlank() || nEmail.isBlank()) {
                                    System.out.println("Erro: Campos vazios!");
                                } else {

                                    usuarioExistente.setNome(nNome);
                                    usuarioExistente.setEmail(nEmail);
                                    service.atualizarUsuario(usuarioExistente);

                                    System.out.println("Usuario atualizado com Sucesso!");
                                }
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Erro: ID invalido ou vazio!");
                        }
                        break;

                    case 5:
                        System.out.print("Digite o ID para remover: ");
                        String idInputRem = scanner.nextLine();

                        try {
                            Long idRem = Long.parseLong(idInputRem);
                            Usuario usuarioExistente = service.buscarUsuarioPorId(idRem);

                            if (usuarioExistente == null) {
                                System.out.println("Erro: Usuario com ID " + idRem + " nao encontrado!");
                            } else {
                                service.removerUsuario(idRem);
                                System.out.println("Usuario removido com Sucesso!");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Erro: ID invalido ou vazio!");
                        }
                        break;
                    case 0:
                        System.out.println("Encerrando Sistema...");
                    default:
                        System.out.println("Opção inválida. Digite uma opção válida!");
                }
            }
        };
    }
}