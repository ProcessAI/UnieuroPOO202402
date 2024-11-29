// src/view/MainView.java
package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView extends JFrame {
    public MainView() {
        setTitle("Gerenciamento de Categorias e Livros");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JButton adicionarButton = new JButton("Adicionar Categoria");
        adicionarButton.setBounds(100, 30, 200, 30);
        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdicionarCategoriaView().setVisible(true);
            }
        });

        JButton atualizarButton = new JButton("Atualizar Categoria");
        atualizarButton.setBounds(100, 70, 200, 30);
        atualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AtualizarCategoriaView().setVisible(true);
            }
        });

        JButton deletarButton = new JButton("Deletar Categoria");
        deletarButton.setBounds(100, 110, 200, 30);
        deletarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DeletarCategoriaView().setVisible(true);
            }
        });

        // Botão para Listar Categorias
        JButton listarButton = new JButton("Listar Categorias e Livros");
        listarButton.setBounds(100, 150, 200, 30);
        listarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ListarCategoriasView().setVisible(true);
            }
        });

        add(adicionarButton);
        add(atualizarButton);
        add(deletarButton);
        add(listarButton);
    }

    public static void main(String[] args) {
        MainView mainView = new MainView();
        mainView.setVisible(true);
    }
}
