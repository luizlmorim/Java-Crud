import com.luizfilipe.deloitte.crud.model.Usuario;
import com.luizfilipe.deloitte.crud.service.UsuarioService;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        UsuarioService service = new UsuarioService();
        Scanner scanner = new Scanner(System.in);

        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n=== MENU USUÁRIOS ===");
            System.out.println("1 - Criar usuário");
            System.out.println("2 - Listar usuários");
            System.out.println("3 - Buscar usuário por ID");
            System.out.println("4 - Atualizar usuário");
            System.out.println("5 - Remover usuário");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            //LEITURA SEGURA DA OPÇÃO
            String entradaOpcao = scanner.nextLine();
            try {
                opcao = Integer.parseInt(entradaOpcao);
            } catch (NumberFormatException e) {
                System.out.println("Digite apenas números!");
                continue;
            }

            switch (opcao) {

                case 1:
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();

                    System.out.print("Email: ");
                    String email = scanner.nextLine();

                    Usuario usuario = new Usuario(null, nome, email);

                    Usuario usuarioCriado = service.criarUsuario(usuario);

                    if (usuarioCriado != null) {
                        System.out.println("Usuário criado com sucesso!");
                        System.out.println(
                                "ID: " + usuarioCriado.getId() +
                                        " | Nome: " + usuarioCriado.getNome() +
                                        " | Email: " + usuarioCriado.getEmail()
                        );
                    }
                    break;

                case 2:
                    List<Usuario> usuarios = service.listarUsuarios();

                    if (usuarios.isEmpty()) {
                        System.out.println("Nenhum usuário cadastrado.");
                    } else {
                        usuarios.forEach(u ->
                                System.out.println(
                                        "ID: " + u.getId() +
                                                " | Nome: " + u.getNome() +
                                                " | Email: " + u.getEmail()
                                )
                        );
                    }
                    break;

                case 3:
                    System.out.print("ID do usuário: ");
                    String entradaIdBusca = scanner.nextLine();

                    try {
                        Long idBusca = Long.parseLong(entradaIdBusca);
                        Usuario usuarioEncontrado = service.buscarUsuarioPorId(idBusca);

                        if (usuarioEncontrado == null) {
                            System.out.println("Usuário não encontrado.");
                        } else {
                            System.out.println(
                                    "ID: " + usuarioEncontrado.getId() +
                                            " | Nome: " + usuarioEncontrado.getNome() +
                                            " | Email: " + usuarioEncontrado.getEmail()
                            );
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("ID inválido!");
                    }
                    break;

                case 4:
                    System.out.print("ID do usuário: ");
                    String entradaIdAtualizar = scanner.nextLine();

                    try {
                        Long idAtualizar = Long.parseLong(entradaIdAtualizar);

                        System.out.print("Novo nome: ");
                        String novoNome = scanner.nextLine();

                        System.out.print("Novo email: ");
                        String novoEmail = scanner.nextLine();

                        Usuario usuarioAtualizado =
                                new Usuario(idAtualizar, novoNome, novoEmail);

                        service.atualizarUsuario(usuarioAtualizado);

                    } catch (NumberFormatException e) {
                        System.out.println("ID inválido!");
                    }
                    break;

                case 5:
                    System.out.print("ID do usuário: ");
                    String entradaIdRemover = scanner.nextLine();

                    try {
                        Long idRemover = Long.parseLong(entradaIdRemover);

                        Usuario usuarioParaRemover = service.buscarUsuarioPorId(idRemover);

                        if (usuarioParaRemover == null) {
                            System.out.println("Usuário não encontrado.");
                            break;
                        }

                        System.out.println("Usuário encontrado:");
                        System.out.println(
                                "ID: " + usuarioParaRemover.getId() +
                                        " | Nome: " + usuarioParaRemover.getNome() +
                                        " | Email: " + usuarioParaRemover.getEmail()
                        );

                        System.out.print("Tem certeza que deseja excluir? (S/N): ");
                        String confirmacao = scanner.nextLine();

                        if (confirmacao.equalsIgnoreCase("S")) {
                            service.removerUsuario(idRemover);
                        } else {
                            System.out.println("Exclusão cancelada.");
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("ID inválido!");
                    }
                    break;

                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }

        scanner.close();
    }
}
