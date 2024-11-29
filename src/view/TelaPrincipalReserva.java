package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPrincipalReserva extends JFrame {

    private CardLayout cardLayout;
    private JPanel cardPanel;

    public TelaPrincipalReserva() {
        // Configurações da janela principal
        setTitle("Tela Principal - Reserva");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // CardLayout para gerenciar diferentes telas
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        add(cardPanel, BorderLayout.CENTER);

        // Criação do menu de navegação
        JPanel menuPanel = new JPanel();
        JButton btnReserva = new JButton("Nova Reserva");
        JButton btnListaReservas = new JButton("Listar Reservas");
        JButton btnCancelarReserva = new JButton("Cancelar Reserva");

        menuPanel.add(btnReserva);
        menuPanel.add(btnListaReservas);
        menuPanel.add(btnCancelarReserva);
        add(menuPanel, BorderLayout.NORTH);

        // Adicionando telas à CardLayout
        TelaReserva telaReserva = new TelaReserva(); // Certifique-se que esta tela seja um JPanel
        TelaListarReservas telaListaReservas = new TelaListarReservas(); // Certifique-se que esta tela seja um JPanel
        TelaCancelarReserva telaCancelarReserva = new TelaCancelarReserva(); // Certifique-se que esta tela seja um JPanel

        cardPanel.add(telaReserva, "Reserva"); // Tela de Reserva
        cardPanel.add(telaListaReservas, "ListaReservas"); // Tela de Listar Reservas
        cardPanel.add(telaCancelarReserva, "CancelarReserva"); // Tela de Cancelar Reserva

        // Ação dos botões de navegação
        btnReserva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Reserva");
            }
        });

        btnListaReservas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "ListaReservas");
            }
        });

        btnCancelarReserva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "CancelarReserva");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TelaPrincipalReserva telaPrincipal = new TelaPrincipalReserva();
                telaPrincipal.setVisible(true);
            }
        });
    }
}
