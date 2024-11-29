// src/controller/CategoriaController.java
package controller;

import java.util.List;
import model.Categoria;
import model.CategoriaDAO;

public class CategoriaController {
    private CategoriaDAO categoriaDAO;

    public CategoriaController() {
        this.categoriaDAO = new CategoriaDAO();
    }

    public void adicionarCategoria(Categoria categoria) {
        categoriaDAO.adicionarCategoria(categoria);
    }

    public void atualizarCategoria(Categoria categoria) {
        categoriaDAO.atualizarCategoria(categoria);
    }

    public void deletarCategoria(int idCategoria) {
        categoriaDAO.deletarCategoria(idCategoria);
    }

    public List<Categoria> listarCategoriasComLivros() {
        return categoriaDAO.listarCategoriasComLivros();
    }
}
