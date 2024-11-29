package controller;

import model.EditoraDAO;
import model.Editora;
import view.EditoraView; 

import java.util.List;

public class EditoraController {

    private EditoraDAO editoraDAO;
    private EditoraView view; 

    public EditoraController(EditoraView view) {
        editoraDAO = new EditoraDAO();
        this.view = view;  
    }

    public void adicionarEditora(String nome) {
        Editora editora = new Editora(nome);
        editoraDAO.addEditora(editora);
        atualizarTabelaNaView();  
    }

    public List<Editora> listarEditoras() {
        return editoraDAO.listarEditoras();
    }

    public void atualizarEditora(int id, String novoNome) {
        Editora editora = new Editora(id, novoNome);
        editoraDAO.updateEditora(editora);
        atualizarTabelaNaView();  
    }

    public void deletarEditora(int id) {
        editoraDAO.deleteEditora(id);
        atualizarTabelaNaView();  
    }

    public void atualizarTabelaNaView() {
        List<Editora> editoras = listarEditoras();  
        view.atualizarTabela(editoras);  
    }
}
