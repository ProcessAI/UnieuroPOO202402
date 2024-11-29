package model;

import java.util.List;

public interface ReservaDAO {
    boolean inserirReserva(Reserva reserva);
    boolean atualizarReserva(Reserva reserva);
    boolean cancelarReserva(int idReserva);
    List<Reserva> listarReservas();
}
