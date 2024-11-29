package view;

import controller.CategoriaController;
import model.Categoria;
import model.Livro;

import javax.swing.*;
import java.awt.*;

public class ListarCategoriasView extends JFrame {
    private CategoriaController controller;

    public ListarCategoriasView() {
        controller = new CategoriaController();

        setTitle("Listar Categorias e Livros");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());

        JTextArea outputArea = new JTextArea(20, 40);
        outputArea.setEditable(false);

        for (Categoria categoria : controller.listarCategoriasComLivros()) {
            outputArea.append("Categoria: " + categoria.getNomeCategoria() + " - " + categoria.getDescricao() + "\n");
            for (Livro livro : categoria.getLivros()) {
                outputArea.append("  Livro: " + livro.getNome() + "\n");
            }
            outputArea.append("\n");
        }

        add(new JScrollPane(outputArea));
    }
}
