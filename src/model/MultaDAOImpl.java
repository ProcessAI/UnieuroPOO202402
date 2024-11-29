package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MultaDAOImpl implements MultaDAO {

    @Override
    public List<Multa> listarMultas() {
        // Consulta ajustada com base na estrutura do banco
        String sql = "SELECT multa.idmulta, multa.valor_base, multa.valor_atual, multa.taxa_diaria, " +
                     "emprestimo.usuario_idusuario, usuario.usuarionome " +
                     "FROM multa " +
                     "JOIN emprestimo ON multa.emprestimo_idemprestimo = emprestimo.idemprestimo " +
                     "JOIN usuario ON emprestimo.usuario_idusuario = usuario.idusuario;";

        List<Multa> multas = new ArrayList<>();

        try (Connection connection = Conexao.getConexao();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Multa multa = new Multa(
                    rs.getInt("idmulta"),
                    rs.getDouble("valor_base"),  // valor base da multa
                    rs.getDouble("valor_atual"), // valor atualizado da multa
                    rs.getDouble("taxa_diaria")  // taxa diária
                );
                // Setando o nome do usuário da multa
                multa.setNomeUsuario(rs.getString("usuarionome"));
                multas.add(multa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return multas;
    }

    @Override
    public boolean cadastrarMulta(Multa multa) {
        // Consulta de inserção ajustada conforme a estrutura da tabela multa
        String sql = "INSERT INTO multa (valor_base, valor_atual, taxa_diaria, emprestimo_idemprestimo) VALUES (?, ?, ?, ?)";

        try (Connection connection = Conexao.getConexao();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, multa.getValorBase());
            stmt.setDouble(2, multa.getValorAtual());
            stmt.setDouble(3, multa.getTaxaDiaria());
            stmt.setInt(4, multa.getEmprestimoId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean excluirMulta(int id) {
        String sql = "DELETE FROM multa WHERE idmulta = ?";
        try (Connection connection = Conexao.getConexao();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void calcularMultas() {
        // Considerando que você tenha a coluna `data_ultima_atualizacao` na tabela multa
        String sql = "UPDATE multa SET valor_atual = GREATEST(0, (CURRENT_DATE - data_ultima_atualizacao) * 5.0) " +
                     "WHERE emprestimo_idemprestimo = ?";

        try (Connection connection = Conexao.getConexao();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

