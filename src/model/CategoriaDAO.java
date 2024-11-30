// src/model/CategoriaDAO.java
package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {


    public void adicionarCategoria(Categoria categoria) {
        String sql = "INSERT INTO categoria (nome_categoria, descricao) VALUES (?, ?)";
        Connection connection = Conexao.getConexao();
        try {
        PreparedStatement stmt = connection.prepareStatement(sql);
  
            stmt.setString(1, categoria.getNomeCategoria());
            stmt.setString(2, categoria.getDescricao());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar categoria", e);
        }
    }

    public void atualizarCategoria(Categoria categoria) {
        String sql = "UPDATE categoria SET nome_categoria = ?, descricao = ? WHERE id_categoria = ?";
        Connection connection = Conexao.getConexao();
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
        String sql = "DELETE FROM categoria WHERE id_categoria = ?";
        Connection connection = Conexao.getConexao();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idCategoria);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar categoria", e);
        }
    }

    // Lista categorias e seus livros
    public List<Categoria> listarCategoriasComLivros() {
        String sql = "SELECT " +
             "c.idcategoria AS id_categoria, " +
             "c.nome AS nome_categoria, " +
             "c.descricao, " +
             "l.idlivro AS id_livro, " +
             "l.nome AS nome_livro " +
             "FROM public.categoria c " +
             "LEFT JOIN public.livro l " +
             "ON c.idcategoria = l.categoria_idcategoria;";

        List<Categoria> categorias = new ArrayList<>();
        Connection connection = Conexao.getConexao();
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
/*                 if (idLivro != 0) {
                    Livro livro = new Livro();
                    livro.setIdLivro(idLivro);
                    livro.setNome(rs.getString("nome"));
                    livro.setIdCategoria(idCategoria);
                    categoria.getLivros().add(livro);
                } */
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar categorias", e);
        }

        return categorias;
    }
}
