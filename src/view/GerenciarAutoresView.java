package autor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class GerenciarAutoresView {

    private JFrame frame;
    private JPanel panel;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField nomeField, nacionalidadeField, dataNascimentoField;
    private JButton addButton, refreshButton;

    public GerenciarAutoresView() {
        frame = new JFrame("Gerenciar Autores");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Painel principal
        panel = new JPanel(new BorderLayout());

        // Tabela para exibir os autores
        tableModel = new DefaultTableModel(new String[]{"ID", "Nome", "Nacionalidade", "Data de Nascimento"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Painel para formulários
        JPanel formPanel = new JPanel(new GridLayout(4, 2));
        nomeField = new JTextField();
        nacionalidadeField = new JTextField();
        dataNascimentoField = new JTextField();
        formPanel.add(new JLabel("Nome:"));
        formPanel.add(nomeField);
        formPanel.add(new JLabel("Nacionalidade:"));
        formPanel.add(nacionalidadeField);
        formPanel.add(new JLabel("Data de Nascimento (yyyy-mm-dd):"));
        formPanel.add(dataNascimentoField);

        addButton = new JButton("Adicionar");
        formPanel.add(addButton);

        panel.add(formPanel, BorderLayout.NORTH);

        // Botão para atualizar a tabela
        refreshButton = new JButton("Atualizar");
        panel.add(refreshButton, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);
    }

    public void setController(AutorController controller) {
        // Listener para adicionar autor
        addButton.addActionListener(e -> {
            String nome = nomeField.getText();
            String nacionalidade = nacionalidadeField.getText();
            String dataNascimento = dataNascimentoField.getText();
            controller.addAutor(nome, nacionalidade, dataNascimento);
        });

        // Listener para atualizar a tabela
        refreshButton.addActionListener(e -> controller.loadAutores());
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }
}
