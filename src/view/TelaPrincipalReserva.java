package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

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
        JButton btnListaReservas = new JButton("Listar Reservas Feitas");
        JButton btnCancelarReserva = new JButton("Cancelar Reserva");
        JButton btnAtualizarReserva = new JButton("Atualizar Reserva");
        
        menuPanel.add(btnListaReservas);
        menuPanel.add(btnReserva);
        menuPanel.add(btnAtualizarReserva);
        menuPanel.add(btnCancelarReserva);
        add(menuPanel, BorderLayout.NORTH);

        // Adicionando telas ao CardLayout
        cardPanel.add(new TelaReserva(), "Reserva");
        cardPanel.add(new TelaListarReservas(), "ListaReservas");
        cardPanel.add(new TelaAtualizarReserva(), "AtualizarReserva");
        cardPanel.add(new TelaCancelarReserva(), "CancelarReserva");
        

        // Ação dos botões de navegação
        btnReserva.addActionListener(e -> cardLayout.show(cardPanel, "Reserva"));
        btnListaReservas.addActionListener(e -> cardLayout.show(cardPanel, "ListaReservas"));
        btnCancelarReserva.addActionListener(e -> cardLayout.show(cardPanel, "CancelarReserva"));
        btnAtualizarReserva.addActionListener(e -> cardLayout.show(cardPanel, "AtualizarReserva"));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaPrincipalReserva telaPrincipal = new TelaPrincipalReserva();
            telaPrincipal.setVisible(true);
        });
    }
}
