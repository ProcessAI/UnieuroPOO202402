// src/model/CategoriaDAO.java
package model;

import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    private Connection connection;

    public CategoriaDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    public void adicionarCategoria(Categoria categoria) {
        String sql = "INSERT INTO categorias (nome_categoria, descricao) VALUES (?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, categoria.getNomeCategoria());
            stmt.setString(2, categoria.getDescricao());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar categoria", e);
        }
    }

    public void atualizarCategoria(Categoria categoria) {
        String sql = "UPDATE categorias SET nome_categoria = ?, descricao = ? WHERE id_categoria = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, categoria.getNomeCategoria());
            stmt.setString(2, categoria.getDescricao());
            stmt.setInt(3, categoria.getIdCategoria());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar categoria", e);
        }
    }

    // Deleta uma categoria pelo ID
    public void deletarCategoria(int idCategoria) {
        String sql = "DELETE FROM categorias WHERE id_categoria = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idCategoria);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar categoria", e);
        }
    }

    // Lista categorias e seus livros
    public List<Categoria> listarCategoriasComLivros() {
        String sql = "SELECT c.id_categoria, c.nome_categoria, c.descricao, l.id_livro, l.nome " +
                     "FROM categorias c LEFT JOIN livro l ON c.id_categoria = l.id_categoria";
        List<Categoria> categorias = new ArrayList<>();

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            Categoria categoria = null;
            while (rs.next()) {
                int idCategoria = rs.getInt("id_categoria");
                if (categoria == null || categoria.getIdCategoria() != idCategoria) {
                    categoria = new Categoria();
                    categoria.setIdCategoria(idCategoria);
                    categoria.setNomeCategoria(rs.getString("nome_categoria"));
                    categoria.setDescricao(rs.getString("descricao"));
                    categorias.add(categoria);
                }

                int idLivro = rs.getInt("id_livro");
                if (idLivro != 0) {
                    Livro livro = new Livro();
                    livro.setIdLivro(idLivro);
                    livro.setNome(rs.getString("nome"));
                    livro.setIdCategoria(idCategoria);
                    categoria.getLivros().add(livro);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar categorias", e);
        }

        return categorias;
    }
}
