// src/view/AdicionarCategoriaView.java
package view;

import controller.CategoriaController;
import model.Categoria;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdicionarCategoriaView extends JFrame {
    private CategoriaController controller;

    public AdicionarCategoriaView() {
        controller = new CategoriaController();

        setTitle("Adicionar Categoria");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

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

        add(nomeLabel);
        add(nomeField);
        add(descricaoLabel);
        add(descricaoField);
        add(adicionarButton);
    }
}
