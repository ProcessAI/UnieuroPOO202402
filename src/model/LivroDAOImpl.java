package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LivroDAOImpl implements LivroDAO {
    @Override
    public Livro getLivroById(int idlivro) {
        String sql = "SELECT * FROM livro WHERE idlivro = ?";
        try (Connection connection = Conexao.getConexao()) {
            if (connection == null) {
                return null;
            }
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, idlivro);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return new Livro(rs.getInt("idlivro"), rs.getString("nome"), rs.getInt("autor_idautor"), rs.getInt("editora_ideditora"), rs.getInt("categoria_idcategoria"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Livro> getLivroByNome(String nome) {
        String sql = "SELECT * FROM livro WHERE nome ILIKE '%' || ? || '%'";
        try (Connection connection = Conexao.getConexao()) {
            if (connection == null) {
                return null;
            }
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, nome);
                try (ResultSet rs = stmt.executeQuery()) {
                    ArrayList<Livro> livros = new ArrayList<>();
                    while (rs.next()) {
                        livros.add(new Livro(rs.getInt("idlivro"), rs.getString("nome"), rs.getInt("autor_idautor"), rs.getInt("editora_ideditora"), rs.getInt("categoria_idcategoria")));
                    }
                    return livros;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean inserirLivro(Livro livro) {
        String sql = "INSERT INTO livro (nome, autor_idautor, editora_ideditora, categoria_idcategoria) VALUES (?, ?, ?, ?)";
        try (Connection connection = Conexao.getConexao()) {
            if (connection == null) {
                return false;
            }
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, livro.getNome());
                stmt.setInt(2, livro.getAutor_idautor());
                stmt.setInt(3, livro.getEditora_ideditora());
                stmt.setInt(4, livro.getCategoria_idcategoria());
                stmt.execute();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean atualizarLivro(Livro livro) {
        String sql = "UPDATE livro SET nome = ?, autor_idautor = ?, editora_ideditora = ?, categoria_idcategoria = ? WHERE idlivro = ?";
        try (Connection connection = Conexao.getConexao()) {
            if (connection == null) {
                return false;
            }
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, livro.getNome());
                stmt.setInt(2, livro.getAutor_idautor());
                stmt.setInt(3, livro.getEditora_ideditora());
                stmt.setInt(4, livro.getCategoria_idcategoria());
                stmt.setInt(5, livro.getIdlivro());
                stmt.execute();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deletarLivro(int idlivro) {
        String sql = "DELETE FROM livro WHERE idlivro = ?";
        try (Connection connection = Conexao.getConexao()) {
            if (connection == null) {
                return false;
            }
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, idlivro);
                stmt.execute();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

}
