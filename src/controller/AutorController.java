import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class AutorController {
    private AutorDAO autorDAO;
    private DefaultTableModel tableModel;

    public AutorController(AutorDAO autorDAO, DefaultTableModel tableModel) {
        this.autorDAO = autorDAO;
        this.tableModel = tableModel;
    }

    // Método para adicionar autor
    public void addAutor(String nome, String nacionalidade, String dataNascimento) {
        if (!nome.isEmpty() && !dataNascimento.isEmpty()) {
            Autor autor = new Autor(0, nome, nacionalidade, dataNascimento);
            autorDAO.addAutor(autor);
            JOptionPane.showMessageDialog(null, "Autor adicionado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, preencha os campos obrigatórios.");
        }
    }

    // Método para carregar autores na tabela
    public void loadAutores() {
        List<Autor> autores = autorDAO.loadAutores();
        // Limpa a tabela antes de adicionar novos dados
        tableModel.setRowCount(0);
        for (Autor autor : autores) {
            tableModel.addRow(new Object[]{autor.getId(), autor.getNome(), autor.getNacionalidade(), autor.getDataNascimento()});
        }
    }
}
