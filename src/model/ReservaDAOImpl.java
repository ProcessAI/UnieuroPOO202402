package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAOImpl implements ReservaDAO {

    @Override
    public boolean inserirReserva(Reserva reserva) {
        String sql = "INSERT INTO reserva (data_reserva, usuario_idusuario) VALUES (?, ?)";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, reserva.getDataReserva());
            stmt.setInt(2, reserva.getIdUsuario());
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean atualizarReserva(Reserva reserva) {
    	String sql = "UPDATE reserva SET data_reserva = ?, usuario_idusuario = ? WHERE idreserva = ?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, reserva.getDataReserva());
            stmt.setInt(2, reserva.getIdUsuario());
            stmt.setInt(3, reserva.getIdReserva());
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean cancelarReserva(int idReserva) {
        String sql = "DELETE FROM reserva WHERE idreserva = ?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idReserva);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Reserva> listarReservas() {
        List<Reserva> reservas = new ArrayList<>();
        String sql = "SELECT idreserva, data_reserva, usuario_idusuario FROM reserva";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Reserva reserva = new Reserva(
                    rs.getInt("idreserva"),
                    rs.getDate("data_reserva"),
                    rs.getInt("usuario_idusuario")
                );
                reservas.add(reserva);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservas;
    }
}
