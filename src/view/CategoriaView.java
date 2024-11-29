// src/view/CategoriaView.java
package view;

import controller.CategoriaController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import model.Categoria;
import model.Livro;

public class CategoriaView extends JFrame {
    private CategoriaController controller;

    public CategoriaView() {
        controller = new CategoriaController();

        setTitle("Gerenciamento de Categorias e Livros");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();

        // Painel para Adicionar Categoria
        JPanel adicionarPanel = new JPanel();
        adicionarPanel.setLayout(null);

        JLabel nomeLabel = new JLabel("Nome da Categoria:");
        nomeLabel.setBounds(20, 30, 150, 20);
        JTextField nomeField = new JTextField();
        nomeField.setBounds(150, 30, 200, 20);

        JLabel descricaoLabel = new JLabel("Descrição:");
        descricaoLabel.setBounds(20, 70, 150, 20);
        JTextField descricaoField = new JTextField();
        descricaoField.setBounds(150, 70, 200, 20);

        JButton adicionarButton = new JButton("Adicionar");
        adicionarButton.setBounds(150, 120, 100, 30);
        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText();
                String descricao = descricaoField.getText();

                Categoria categoria = new Categoria();
                categoria.setNomeCategoria(nome);
                categoria.setDescricao(descricao);
                controller.adicionarCategoria(categoria);
                JOptionPane.showMessageDialog(null, "Categoria adicionada com sucesso!");
                nomeField.setText("");
                descricaoField.setText("");
            }
        });

        adicionarPanel.add(nomeLabel);
        adicionarPanel.add(nomeField);
        adicionarPanel.add(descricaoLabel);
        adicionarPanel.add(descricaoField);
        adicionarPanel.add(adicionarButton);

        // Painel para Atualizar Categoria
        JPanel atualizarPanel = new JPanel();
        atualizarPanel.setLayout(null);

        JLabel atualizarIdLabel = new JLabel("ID da Categoria:");
        atualizarIdLabel.setBounds(20, 30, 150, 20);
        JTextField atualizarIdField = new JTextField();
        atualizarIdField.setBounds(150, 30, 200, 20);

        JLabel atualizarNomeLabel = new JLabel("Nome da Categoria:");
        atualizarNomeLabel.setBounds(20, 70, 150, 20);
        JTextField atualizarNomeField = new JTextField();
        atualizarNomeField.setBounds(150, 70, 200, 20);

        JLabel atualizarDescricaoLabel = new JLabel("Descrição:");
        atualizarDescricaoLabel.setBounds(20, 110, 150, 20);
        JTextField atualizarDescricaoField = new JTextField();
        atualizarDescricaoField.setBounds(150, 110, 200, 20);

        JButton atualizarButton = new JButton("Atualizar");
        atualizarButton.setBounds(150, 160, 100, 30);
        atualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(atualizarIdField.getText());
                String nome = atualizarNomeField.getText();
                String descricao = atualizarDescricaoField.getText();

                Categoria categoria = new Categoria();
                categoria.setIdCategoria(id);
                categoria.setNomeCategoria(nome);
                categoria.setDescricao(descricao);
                controller.atualizarCategoria(categoria);
                JOptionPane.showMessageDialog(null, "Categoria atualizada com sucesso!");
                atualizarIdField.setText("");
                atualizarNomeField.setText("");
                atualizarDescricaoField.setText("");
            }
        });

        atualizarPanel.add(atualizarIdLabel);
        atualizarPanel.add(atualizarIdField);
        atualizarPanel.add(atualizarNomeLabel);
        atualizarPanel.add(atualizarNomeField);
        atualizarPanel.add(atualizarDescricaoLabel);
        atualizarPanel.add(atualizarDescricaoField);
        atualizarPanel.add(atualizarButton);

        // Painel para Deletar Categoria
        JPanel deletarPanel = new JPanel();
        deletarPanel.setLayout(null);

        JLabel deletarIdLabel = new JLabel("ID da Categoria:");
        deletarIdLabel.setBounds(20, 30, 150, 20);
        JTextField deletarIdField = new JTextField();
        deletarIdField.setBounds(150, 30, 200, 20);

        JButton deletarButton = new JButton("Deletar");
        deletarButton.setBounds(150, 80, 100, 30);
        deletarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(deletarIdField.getText());
                controller.deletarCategoria(id);
                JOptionPane.showMessageDialog(null, "Categoria deletada com sucesso!");
                deletarIdField.setText("");
            }
        });

        deletarPanel.add(deletarIdLabel);
        deletarPanel.add(deletarIdField);
        deletarPanel.add(deletarButton);

        // Painel para Listar Categorias
        JPanel listarPanel = new JPanel();
        listarPanel.setLayout(null);

        JTextArea outputArea = new JTextArea(20, 40);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBounds(20, 20, 500, 300);

        JButton listarButton = new JButton("Listar Categorias e Livros");
        listarButton.setBounds(180, 350, 200, 30);
        listarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                outputArea.setText("");
                for (Categoria categoria : controller.listarCategoriasComLivros()) {
                    outputArea.append("Categoria: " + categoria.getNomeCategoria() + " - " + categoria.getDescricao() + "\n");
                    for (Livro livro : categoria.getLivros()) {
                        outputArea.append("  Livro: " + livro.getNome() + "\n");
                    }
                    outputArea.append("\n");
                }
            }
        });

        listarPanel.add(scrollPane);
        listarPanel.add(listarButton);

        // Adicionando painéis ao JTabbedPane
        tabbedPane.add("Adicionar Categoria", adicionarPanel);
        tabbedPane.add("Atualizar Categoria", atualizarPanel);
        tabbedPane.add("Deletar Categoria", deletarPanel);
        tabbedPane.add("Listar Categorias", listarPanel);

        add(tabbedPane);
    }

    public static void main(String[] args) {
        CategoriaView view = new CategoriaView();
        view.setVisible(true);
    }
}
