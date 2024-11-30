package model;

import java.sql.Date;

public class Reserva {
    private int idReserva;
    private Date dataReserva;
    private int idUsuario;

    // Construtores
    public Reserva(int idReserva, Date dataReserva, int idUsuario) {
        this.idReserva = idReserva;
        this.dataReserva = dataReserva;
        this.idUsuario = idUsuario;
    }

    public Reserva(Date dataReserva, int idUsuario) {
        this.dataReserva = dataReserva;
        this.idUsuario = idUsuario;
    }

    // Getters e Setters
    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public Date getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(Date dataReserva) {
        this.dataReserva = dataReserva;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
