package view;

import controller.ReservaController;

import javax.swing.*;
import java.awt.*;

public class TelaAtualizarReserva extends JPanel {

    public TelaAtualizarReserva(JFrame parent) {
        setLayout(new GridLayout(4, 2, 10, 10));

        JLabel lblIdReserva = new JLabel("ID da Reserva:");
        JTextField txtIdReserva = new JTextField();

        JLabel lblDataReserva = new JLabel("Nova Data (YYYY-MM-DD):");
        JTextField txtDataReserva = new JTextField();

        JLabel lblIdUsuario = new JLabel("Novo ID do Usuário:");
        JTextField txtIdUsuario = new JTextField();

        JButton btnAtualizar = new JButton("Atualizar");
        btnAtualizar.addActionListener(e -> {
            try {
                int idReserva = Integer.parseInt(txtIdReserva.getText());
                String novaData = txtDataReserva.getText();
                int novoIdUsuario = Integer.parseInt(txtIdUsuario.getText());

                ReservaController controller = new ReservaController();
                if (controller.atualizarReserva(idReserva, novaData, novoIdUsuario)) {
                    JOptionPane.showMessageDialog(this, "Reserva atualizada com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao atualizar reserva.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID e dados devem ser válidos.");
            }
        });

        add(lblIdReserva);
        add(txtIdReserva);
        add(lblDataReserva);
        add(txtDataReserva);
        add(lblIdUsuario);
        add(txtIdUsuario);
        add(new JLabel());
        add(btnAtualizar);
    }
}
