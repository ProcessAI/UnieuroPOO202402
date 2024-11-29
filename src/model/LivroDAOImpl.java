package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LivroDAOImpl implements LivroDAO {
    @Override
    public Livro getLivroById(int idlivro) {
        try {

            String sql = "SELECT * FROM livro WHERE idlivro = ?";
            Conexao.testConnection();
            Connection connection = Conexao.getConexao();
            if (connection == null) {
                return null;
            }

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idlivro);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Livro(rs.getInt("idlivro"), rs.getString("nome"), rs.getInt("autor_idautor"), rs.getInt("editora_ideditora"), rs.getInt("categoria_idcategoria"), rs.getInt("reserva_idreserva"));
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
        Conexao.testConnection();
        try {
            Connection connection = Conexao.getConexao();
            if (connection == null) {
                return null;
            }
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);
            return getLivros(stmt);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Livro> getAllLivros() {
        String sql = "SELECT * FROM livro";
        Conexao.testConnection();
        try {
            Connection connection = Conexao.getConexao();
            if (connection == null) {
                return null;
            }
            PreparedStatement stmt = connection.prepareStatement(sql);
            return getLivros(stmt);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ArrayList<Livro> getLivros(PreparedStatement stmt) throws SQLException {
        Conexao.testConnection();
        try (ResultSet rs = stmt.executeQuery()) {
            ArrayList<Livro> livros = new ArrayList<>();
            while (rs.next()) {
                livros.add(new Livro(rs.getInt("idlivro"), rs.getString("nome"), rs.getInt("autor_idautor"), rs.getInt("editora_ideditora"), rs.getInt("categoria_idcategoria"), rs.getInt("reserva_idreserva")));
            }
            return livros;
        }
    }

    @Override
    public boolean inserirLivro(Livro livro) {
        String sql = "INSERT INTO livro (nome, autor_idautor, editora_ideditora, categoria_idcategoria, reserva_idreserva) VALUES (?, ?, ?, ?, ? )";
        Conexao.testConnection();
        try {
            Connection connection = Conexao.getConexao();
            if (connection == null) {
                return false;
            }
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, livro.getNome());
            stmt.setInt(2, livro.getAutor_idautor());
            stmt.setInt(3, livro.getEditora_ideditora());
            stmt.setInt(4, livro.getCategoria_idcategoria());
            stmt.setInt(5, livro.getReserva_idreserva());
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean atualizarLivro(Livro livro) {
        String sql = "UPDATE livro SET nome = ?, autor_idautor = ?, editora_ideditora = ?, categoria_idcategoria = ? WHERE idlivro = ?";
        Conexao.testConnection();
        try {
            Connection connection = Conexao.getConexao();
            if (connection == null) {
                return false;
            }
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, livro.getNome());
            stmt.setInt(2, livro.getAutor_idautor());
            stmt.setInt(3, livro.getEditora_ideditora());
            stmt.setInt(4, livro.getCategoria_idcategoria());
            stmt.setInt(5, livro.getIdlivro());
            stmt.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deletarLivro(int idlivro) {
        String sql = "DELETE FROM livro WHERE idlivro = ?";
        //TODO add logica para vericar se o livro esta emprestado, fk violada


        Conexao.testConnection();
        try {
            Connection connection = Conexao.getConexao();
            if (connection == null) {
                return false;
            }
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idlivro);
            stmt.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

}
