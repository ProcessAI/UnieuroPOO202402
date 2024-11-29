package view;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import model.Reserva;
import model.ReservaDAOImpl;

public class TelaListarReservas extends JPanel {
    
    private JTable table;

    public TelaListarReservas() {
        setLayout(new BorderLayout());

        // Criar a tabela para exibir as reservas
        String[] columnNames = {"ID Reserva", "Data Reserva", "ID Usuário"};
        ReservaDAOImpl reservaDAO = new ReservaDAOImpl();
        List<Reserva> reservas = reservaDAO.listarReservas();
        
        // Converte a lista de reservas para uma matriz de objetos que pode ser usada na JTable
        Object[][] data = new Object[reservas.size()][3];
        for (int i = 0; i < reservas.size(); i++) {
            Reserva reserva = reservas.get(i);
            data[i][0] = reserva.getIdReserva();
            data[i][1] = reserva.getDataReserva();
            data[i][2] = reserva.getIdUsuario();
        }

        // Criar a tabela e adicionar na interface
        table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }
}
