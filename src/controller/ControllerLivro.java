package controller;

import mock.MockAutor;
import mock.MockCategoria;
import mock.MockEditora;
import mock.MockReserva;
import model.Livro;
import model.LivroDAO;
import model.LivroDAOImpl;

import java.util.ArrayList;
import java.util.List;

public class ControllerLivro {
    LivroDAO livroDAO = new LivroDAOImpl();


    public Livro viewToModel(String nome, String autor, String editora, String categoria, String reserva) {
        Livro livro = new Livro();
        livro.setNome(nome);
        livro.setAutor_idautor(MockAutor.getIdAutor(autor));
        livro.setEditora_ideditora(MockEditora.getIdEditora(editora));
        livro.setCategoria_idcategoria(MockCategoria.getIdCategoria(categoria));
        livro.setReserva_idreserva(MockReserva.getIdReserva(reserva));
        return livro;
    }

    public String modelToView(Livro livro) {
        return "Nome: " + livro.getNome() + "\n" +
                "Autor: " + MockAutor.getNomeAutor(livro.getAutor_idautor()) + "\n" +
                "Editora: " + MockEditora.getNomeEditora(livro.getEditora_ideditora()) + "\n" +
                "Categoria: " + MockCategoria.getNomeCategoria(livro.getCategoria_idcategoria()) + "\n" +
                "Reserva: " + MockReserva.getDataReserva(livro.getReserva_idreserva());
    }


    public List<String[]> listarLivros() {
        List<String[]> livros = new ArrayList<>();
        List<Livro> livrosModel = livroDAO.getAllLivros();
        for (Livro livro : livrosModel) {
            String[] livroArray = new String[6];
            livroArray[0] = String.valueOf(livro.getIdlivro());
            livroArray[1] = livro.getNome();
            livroArray[2] = MockAutor.getNomeAutor(livro.getAutor_idautor());
            livroArray[3] = MockEditora.getNomeEditora(livro.getEditora_ideditora());
            livroArray[4] = MockCategoria.getNomeCategoria(livro.getCategoria_idcategoria());
            livroArray[5] = String.valueOf(MockReserva.getStatusById(livro.getReserva_idreserva()));
            livros.add(livroArray);
        }
        return livros;
    }

    // Método para salvar um livro

    public void salvarLivro(String nome, int autorId, int categoriaId, int editoraId, Integer reservaId) {
        Livro livro = new Livro(null, nome, autorId, categoriaId, editoraId, reservaId);
        livroDAO.inserirLivro(livro);
    }

    public void excluirLivro(int id) {
        livroDAO.deletarLivro(id);
    }

    // Métodos para listar autores, categorias, editoras e reservas
    public List<String> listarAutores() {
        return listarEntidades("autor");
    }

    public List<String> listarCategorias() {
        return listarEntidades("categoria");
    }

    public List<String> listarEditoras() {
        return listarEntidades("editora");
    }

    public List<String> listarReservas() {
        return listarEntidades("reserva");
    }

    // Método genérico para listar entidades
    private List<String> listarEntidades(String tabela) {
        List<String> entidades = new ArrayList<>();
        switch (tabela) {
            case "autor" -> entidades = MockAutor.getAll();
            case "categoria" -> entidades = MockCategoria.getAll();
            case "editora" -> entidades = MockEditora.getAll();
            case "reserva" -> entidades = MockReserva.getAll();
        }
        return entidades;
    }
}

