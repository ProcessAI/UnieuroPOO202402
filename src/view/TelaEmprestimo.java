package view;

import controller.ControllerEmprestimo;
import java.awt.*;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

public class TelaEmprestimo extends JFrame {
    private JTextField usuarioIdField, livroIdField, dataDevolucaoField, buscaField;
    private JButton emprestarButton, atualizarButton, excluirButton, buscarButton;
    private JTable tabelaEmprestimos;
    private Connection con;

    public TelaEmprestimo(Connection con) {
        this.con = con;
        setTitle("Gestão de Empréstimos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Campos de entrada
        usuarioIdField = new JTextField(20);
        livroIdField = new JTextField(20);
        dataDevolucaoField = new JTextField(20);
        buscaField = new JTextField(20);

        // Botões de ação
        emprestarButton = new JButton("Emprestar");
        atualizarButton = new JButton("Atualizar");
        excluirButton = new JButton("Excluir");
        buscarButton = new JButton("Buscar");

        // Tabela para exibir empréstimos
        tabelaEmprestimos = new JTable();

        // Ações dos botões
        emprestarButton.addActionListener(e -> realizarEmprestimo());
        atualizarButton.addActionListener(e -> atualizarEmprestimo());
        excluirButton.addActionListener(e -> excluirEmprestimo());
        buscarButton.addActionListener(e -> buscarEmprestimo());

        // Layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));
        panel.add(new JLabel("ID Usuário:"));
        panel.add(usuarioIdField);
        panel.add(new JLabel("ID Livro:"));
        panel.add(livroIdField);
        panel.add(new JLabel("Data Devolução:"));
        panel.add(dataDevolucaoField);
        panel.add(emprestarButton);
        panel.add(atualizarButton);
        panel.add(excluirButton);
        panel.add(new JLabel("Buscar por ID Empréstimo:"));
        panel.add(buscaField);
        panel.add(buscarButton);

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(tabelaEmprestimos), BorderLayout.CENTER);
    }

    private void realizarEmprestimo() {
        try {
            System.out.println("Lendo dados do empréstimo...");
            int usuarioId = Integer.parseInt(usuarioIdField.getText());
            int livroId = Integer.parseInt(livroIdField.getText());
            Date dataDevolucao = new SimpleDateFormat("yyyy-MM-dd").parse(dataDevolucaoField.getText());

            System.out.println("Usuário ID: " + usuarioId);
            System.out.println("Livro ID: " + livroId);
            System.out.println("Data Devolução: " + dataDevolucao);

            ControllerEmprestimo controllerEmprestimo = new ControllerEmprestimo(con);
            boolean sucesso = controllerEmprestimo.realizarEmprestimo(usuarioId, livroId, dataDevolucao);
            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Empréstimo realizado com sucesso!");
                carregarEmprestimos();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao realizar o empréstimo.");
            }
        } catch (Exception ex) {
            System.err.println("Erro ao realizar empréstimo: " + ex.getMessage());
            JOptionPane.showMessageDialog(this, "Dados inválidos!");
        }
    }

    private void atualizarEmprestimo() {
        try {
            System.out.println("Atualizando empréstimo...");
            int idEmprestimo = Integer.parseInt(buscaField.getText()); // Pega o ID do empréstimo a ser atualizado
            int usuarioId = Integer.parseInt(usuarioIdField.getText()); // ID do usuário
            int livroId = Integer.parseInt(livroIdField.getText()); // ID do livro
            Date dataDevolucao = new SimpleDateFormat("yyyy-MM-dd").parse(dataDevolucaoField.getText()); // Data de devolução

            ControllerEmprestimo controllerEmprestimo = new ControllerEmprestimo(con);
            boolean sucesso = controllerEmprestimo.atualizarEmprestimo(idEmprestimo, usuarioId, livroId, dataDevolucao);
            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Empréstimo atualizado com sucesso!");
                carregarEmprestimos();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao atualizar o empréstimo.");
            }
        } catch (Exception ex) {
            System.err.println("Erro ao atualizar empréstimo: " + ex.getMessage());
            JOptionPane.showMessageDialog(this, "Erro ao atualizar empréstimo!");
        }
    }


    private void excluirEmprestimo() {
        try {
            System.out.println("Excluindo empréstimo...");
            int idEmprestimo = Integer.parseInt(buscaField.getText());
            ControllerEmprestimo controllerEmprestimo = new ControllerEmprestimo(con);
            boolean sucesso = controllerEmprestimo.excluirEmprestimo(idEmprestimo);
            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Empréstimo excluído com sucesso!");
                carregarEmprestimos();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao excluir o empréstimo.");
            }
        } catch (Exception ex) {
            System.err.println("Erro ao excluir empréstimo: " + ex.getMessage());
            JOptionPane.showMessageDialog(this, "Erro ao excluir empréstimo!");
        }
    }

    private void carregarEmprestimos() {
        ControllerEmprestimo controllerEmprestimo = new ControllerEmprestimo(con);
        Object[][] dados = controllerEmprestimo.listarEmprestimos();
        tabelaEmprestimos.setModel(new javax.swing.table.DefaultTableModel(
                dados,
                new String[]{"ID", "Usuário", "Livro", "Data Devolução"}
        ));
    }
    
    private void buscarEmprestimo() {
        try {
            int idEmprestimo = Integer.parseInt(buscaField.getText());
            ControllerEmprestimo controllerEmprestimo = new ControllerEmprestimo(con);
            Object[][] dados = controllerEmprestimo.buscarEmprestimo(idEmprestimo);
            
            // Verifica se encontrou algum resultado
            if (dados.length > 0) {
                tabelaEmprestimos.setModel(new javax.swing.table.DefaultTableModel(
                        dados,
                        new String[]{"ID", "Usuário", "Livro", "Data Devolução"}
                ));
            } else {
                JOptionPane.showMessageDialog(this, "Empréstimo não encontrado!");
            }
        } catch (Exception ex) {
            System.err.println("Erro ao buscar empréstimo: " + ex.getMessage());
            JOptionPane.showMessageDialog(this, "Erro ao buscar empréstimo.");
        }
    }

}
