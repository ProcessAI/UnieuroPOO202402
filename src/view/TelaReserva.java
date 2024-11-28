package view;

import javax.swing.*;

import controller.ReservaController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaReserva extends JPanel {

    private JTextField txtDataReserva;
    private JTextField txtIdUsuario;
    private JButton btnSalvar;

    public TelaReserva() {
        setLayout(new GridLayout(3, 2));

        JLabel lblDataReserva = new JLabel("Data da Reserva (YYYY-MM-DD):");
        txtDataReserva = new JTextField();
        
        JLabel lblIdUsuario = new JLabel("ID do Usuário:");
        txtIdUsuario = new JTextField();
        
        btnSalvar = new JButton("Salvar");

        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dataReserva = txtDataReserva.getText();
                try {
                    int idUsuario = Integer.parseInt(txtIdUsuario.getText());
                    // Chamando o controller para salvar a reserva
                    ReservaController controller = new ReservaController();
                    boolean sucesso = controller.cadastrarReserva(dataReserva, idUsuario);

                    if (sucesso) {
                        JOptionPane.showMessageDialog(null, "Reserva cadastrada com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao cadastrar reserva.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "O ID do usuário deve ser um número inteiro.");
                }
            }
        });

        add(lblDataReserva);
        add(txtDataReserva);
        add(lblIdUsuario);
        add(txtIdUsuario);
        add(new JLabel()); // Espaço em branco
        add(btnSalvar);
    }
}
