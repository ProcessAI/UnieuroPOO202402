package view;

import javax.swing.*;

import controller.ReservaController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.ReservaController;

public class TelaCancelarReserva extends JPanel {

    private JTextField txtIdReservaCancelar;
    private JButton btnCancelar;

    public TelaCancelarReserva() {
        setLayout(new GridLayout(2, 2));

        JLabel lblIdReserva = new JLabel("ID da Reserva para Cancelar:");
        txtIdReservaCancelar = new JTextField();
        
        btnCancelar = new JButton("Cancelar Reserva");

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idReserva;
                try {
                    idReserva = Integer.parseInt(txtIdReservaCancelar.getText());
                    // Lógica de cancelamento de reserva
                    ReservaController controller = new ReservaController();
                    boolean sucesso = controller.cancelarReserva(idReserva);

                    if (sucesso) {
                        JOptionPane.showMessageDialog(null, "Reserva cancelada com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao cancelar a reserva.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "O ID da reserva deve ser um número.");
                }
            }
        });

        add(lblIdReserva);
        add(txtIdReservaCancelar);
        add(new JLabel()); // Espaço em branco
        add(btnCancelar);
    }
}
