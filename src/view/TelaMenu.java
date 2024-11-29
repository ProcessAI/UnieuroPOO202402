package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import model.Conexao;
import java.sql.Connection;

public class TelaMenu extends JFrame {

    public TelaMenu() {
        setTitle("Menu Principal");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal com layout de grade
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 1, 10, 10)); // Ajustado para suportar mais botões

        // Botões com ações correspondentes
        JButton btnLogin = new JButton("Abrir Tela de Login");
        btnLogin.addActionListener((ActionEvent e) -> abrirTelaLogin());

        JButton btnCategoria = new JButton("Abrir CategoriaView");
        btnCategoria.addActionListener((ActionEvent e) -> abrirCategoriaView());

        JButton btnLivro = new JButton("Abrir Tela de Livro");
        btnLivro.addActionListener((ActionEvent e) -> abrirTelaLivro());

        JButton btnReserva = new JButton("Abrir Tela de Reserva");
        btnReserva.addActionListener((ActionEvent e) -> abrirReserva());

        JButton btnEmprestimo = new JButton("Abrir Tela de Empréstimo");
        btnEmprestimo.addActionListener((ActionEvent e) -> abrirEmprestimo());

        JButton btnMulta = new JButton("Abrir Tela de Multa");
        btnMulta.addActionListener((ActionEvent e) -> abrirMulta());

        JButton btnEditoras = new JButton("Abrir Tela de Editoras");
        btnEditoras.addActionListener((ActionEvent e) -> abrirEditoras());

        // Adiciona os botões ao painel
        panel.add(btnLogin);
        panel.add(btnCategoria);
        panel.add(btnLivro);
        panel.add(btnReserva);
        panel.add(btnEmprestimo);
        panel.add(btnMulta);
        panel.add(btnEditoras);

        // Adiciona o painel ao frame
        add(panel);
    }

    // Métodos para abrir diferentes telas
    private void abrirTelaLogin() {
        TelaLogin telaLogin = new TelaLogin();
        telaLogin.setVisible(true);
    }

    private void abrirCategoriaView() {
        CategoriaView categoriaView = new CategoriaView();
        categoriaView.setVisible(true);
    }

    private void abrirTelaLivro() {
        TelaLivro telaLivro = new TelaLivro();
        telaLivro.setVisible(true);
    }

    private void abrirEmprestimo() {
        Connection connection = Conexao.getConexao();
        TelaEmprestimo telaEmprestimo = new TelaEmprestimo(connection);
        telaEmprestimo.setVisible(true);
    }

    private void abrirMulta() {
        TelaMulta telaMulta = new TelaMulta();
        telaMulta.setVisible(true);
    }

    private void abrirReserva() {
        TelaPrincipalReserva telaPrincipalReserva = new TelaPrincipalReserva();
        telaPrincipalReserva.setVisible(true);
    }

/*     private void abrirEditoras() {
        TelaEditoras telaEditoras = new TelaEditoras();
        telaEditoras.setVisible(true);
    }
 */
    public static void main(String[] args) {
        TelaMenu telaMenu = new TelaMenu();
        telaMenu.setVisible(true);
    }
}
