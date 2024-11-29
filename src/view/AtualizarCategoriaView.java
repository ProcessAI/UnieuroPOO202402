// src/view/AtualizarCategoriaView.java
package view;

import controller.CategoriaController;
import model.Categoria;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AtualizarCategoriaView extends JFrame {
    private CategoriaController controller;

    public AtualizarCategoriaView() {
        controller = new CategoriaController();

        setTitle("Atualizar Categoria");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel idLabel = new JLabel("ID da Categoria:");
        idLabel.setBounds(20, 30, 150, 20);
        JTextField idField = new JTextField();
        idField.setBounds(150, 30, 200, 20);

        JLabel nomeLabel = new JLabel("Nome da Categoria:");
        nomeLabel.setBounds(20, 70, 150, 20);
        JTextField nomeField = new JTextField();
        nomeField.setBounds(150, 70, 200, 20);

        JLabel descricaoLabel = new JLabel("Descrição:");
        descricaoLabel.setBounds(20, 110, 150, 20);
        JTextField descricaoField = new JTextField();
        descricaoField.setBounds(150, 110, 200, 20);

        JButton atualizarButton = new JButton("Atualizar");
        atualizarButton.setBounds(150, 160, 100, 30);
        atualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(idField.getText());
                String nome = nomeField.getText();
                String descricao = descricaoField.getText();

                Categoria categoria = new Categoria();
                categoria.setIdCategoria(id);
                categoria.setNomeCategoria(nome);
                categoria.setDescricao(descricao);
                controller.atualizarCategoria(categoria);
                JOptionPane.showMessageDialog(null, "Categoria atualizada com sucesso!");
                idField.setText("");
                nomeField.setText("");
                descricaoField.setText("");
            }
        });

        add(idLabel);
        add(idField);
        add(nomeLabel);
        add(nomeField);
        add(descricaoLabel);
        add(descricaoField);
        add(atualizarButton);
    }
}
