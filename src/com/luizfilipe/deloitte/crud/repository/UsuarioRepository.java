package com.luizfilipe.deloitte.crud.repository;

import com.luizfilipe.deloitte.crud.model.Usuario;

//banco de dados
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//listas
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class UsuarioRepository {

    private static final String URL = "jdbc:h2:file:./data/testdb;DB_CLOSE_ON_EXIT=FALSE;";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    //Garante que a tabela existe
    //Antes de qualquer operação de CRUD
   public UsuarioRepository() {
        criarTabela();
    }

    // acessar o banco usamos esse método
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    //criando a tabela
    public void criarTabela() {
        String sql = """
        CREATE TABLE IF NOT EXISTS usuarios (
            id BIGINT AUTO_INCREMENT PRIMARY KEY,
            nome VARCHAR(100) NOT NULL,
            email VARCHAR(100) NOT NULL
        )
        """;

        try (Connection conn = getConnection();
             var stmt = conn.createStatement()) {

            stmt.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //salavar user ( CREATE )
    public void salvar(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nome, email) VALUES (?, ?)";

        try (Connection conn = getConnection();
             var pstmt = conn.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, usuario.getNome());
            pstmt.setString(2, usuario.getEmail());

            pstmt.executeUpdate();

            // Captura o ID gerado
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    usuario.setId(rs.getLong(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //lendo todos os usuários ( READ )
    public List<Usuario> listar() {
        List<com.luizfilipe.deloitte.crud.model.Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";

        try (Connection conn = getConnection();
             var stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                var usuario = new com.luizfilipe.deloitte.crud.model.Usuario();
                usuario.setId(rs.getLong("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));

                usuarios.add(usuario);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    //Buscando por ID
    public com.luizfilipe.deloitte.crud.model.Usuario buscarPorId(Long id) {
        String sql = "SELECT * FROM usuarios WHERE id = ?";
        com.luizfilipe.deloitte.crud.model.Usuario usuario = null;

        try (Connection conn = getConnection();
             var pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                usuario = new com.luizfilipe.deloitte.crud.model.Usuario();
                usuario.setId(rs.getLong("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuario;
    }

    //atualiza usuario
    public void atualizar(com.luizfilipe.deloitte.crud.model.Usuario usuario) {
        String sql = "UPDATE usuarios SET nome = ?, email = ? WHERE id = ?";

        try (Connection conn = getConnection();
             var pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, usuario.getNome());
            pstmt.setString(2, usuario.getEmail());
            pstmt.setLong(3, usuario.getId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //remove usuario
    public void remover(Long id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";

        try (Connection conn = getConnection();
             var pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }







}
