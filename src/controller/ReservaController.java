package controller;

import model.Reserva;
import model.ReservaDAO;
import model.ReservaDAOImpl;

import java.sql.Date;
import java.util.List;

public class ReservaController {

    private final ReservaDAO reservaDAO;

    public ReservaController() {
        this.reservaDAO = new ReservaDAOImpl();
    }

    public boolean cadastrarReserva(String dataReserva, int idUsuario) {
        try {
            Date data = Date.valueOf(dataReserva); // Converter String para Date
            Reserva reserva = new Reserva(data, idUsuario);
            return reservaDAO.inserirReserva(reserva);
        } catch (IllegalArgumentException e) {
            System.out.println("Data inválida. Use o formato YYYY-MM-DD.");
            return false;
        }
    }

    public boolean atualizarReserva(int idReserva, String novaData, int novoIdUsuario) {
        try {
            Date data = Date.valueOf(novaData); // Converter String para Date
            Reserva reserva = new Reserva(idReserva, data, novoIdUsuario);
            return reservaDAO.atualizarReserva(reserva);
        } catch (IllegalArgumentException e) {
            System.out.println("Data inválida. Use o formato YYYY-MM-DD.");
            return false;
        }
    }

    public boolean cancelarReserva(int idReserva) {
        return reservaDAO.cancelarReserva(idReserva);
    }

    public List<Reserva> listarReservas() {
        return reservaDAO.listarReservas();
    }
}
