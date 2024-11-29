// src/view/DeletarCategoriaView.java
package view;

import controller.CategoriaController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeletarCategoriaView extends JFrame {
    private CategoriaController controller;

    public DeletarCategoriaView() {
        controller = new CategoriaController();

        setTitle("Deletar Categoria");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel idLabel = new JLabel("ID da Categoria:");
        idLabel.setBounds(20, 30, 150, 20);
        JTextField idField = new JTextField();
        idField.setBounds(150, 30, 200, 20);

        JButton deletarButton = new JButton("Deletar");
        deletarButton.setBounds(150, 80, 100, 30);
        deletarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(idField.getText());
                controller.deletarCategoria(id);
                JOptionPane.showMessageDialog(null, "Categoria deletada com sucesso!");
                idField.setText("");
            }
        });

        add(idLabel);
        add(idField);
        add(deletarButton);
    }
}
