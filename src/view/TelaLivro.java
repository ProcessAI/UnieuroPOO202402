package view;

import controller.LivroController;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaLivro extends JFrame {

    private JTable tabelaLivros;
    private JTextField nomeField;
    private JComboBox<String> autorComboBox, categoriaComboBox, editoraComboBox, reservaComboBox;
    private JButton salvarButton, limparButton;
    private LivroController controller;

    public TelaLivro() {
        // Inicializar controlador
        controller = new LivroController();

        // Configurações da janela principal
        setTitle("Gerenciamento de Livros");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout principal
        setLayout(new BorderLayout());

        // Painel superior: Formulário de entrada
        JPanel painelFormulario = new JPanel(new GridLayout(6, 2, 10, 10));
        painelFormulario.setBorder(BorderFactory.createTitledBorder("Gerenciar Livro"));

        // Campo Nome
        nomeField = new JTextField();
        painelFormulario.add(new JLabel("Nome:"));
        painelFormulario.add(nomeField);

        // ComboBox Autor
        autorComboBox = new JComboBox<>();
        carregarCombo(autorComboBox, controller.listarAutores());
        painelFormulario.add(new JLabel("Autor:"));
        painelFormulario.add(autorComboBox);

        // ComboBox Categoria
        categoriaComboBox = new JComboBox<>();
        carregarCombo(categoriaComboBox, controller.listarCategorias());
        painelFormulario.add(new JLabel("Categoria:"));
        painelFormulario.add(categoriaComboBox);

        // ComboBox Editora
        editoraComboBox = new JComboBox<>();
        carregarCombo(editoraComboBox, controller.listarEditoras());
        painelFormulario.add(new JLabel("Editora:"));
        painelFormulario.add(editoraComboBox);

        // ComboBox Reserva
        reservaComboBox = new JComboBox<>();
        carregarCombo(reservaComboBox, controller.listarReservas());
        painelFormulario.add(new JLabel("Reserva:"));
        painelFormulario.add(reservaComboBox);

        // Botões
        salvarButton = new JButton("Salvar");
        limparButton = new JButton("Limpar");
        painelFormulario.add(salvarButton);
        painelFormulario.add(limparButton);

        add(painelFormulario, BorderLayout.NORTH);

        // Tabela para exibir os livros
        tabelaLivros = new JTable();
        carregarTabela();

        JScrollPane scrollPane = new JScrollPane(tabelaLivros);
        add(scrollPane, BorderLayout.CENTER);

        // Ações dos botões
        salvarButton.addActionListener(e -> salvarLivro());
        limparButton.addActionListener(e -> limparFormulario());
    }

    private void carregarTabela() {
        List<String[]> livros = controller.listarLivros();
        String[] colunas = {"ID", "Nome", "Autor", "Categoria", "Editora", "Reserva"};

        String[][] dados = livros.toArray(new String[0][]);
        tabelaLivros.setModel(new javax.swing.table.DefaultTableModel(dados, colunas));
    }

    private void salvarLivro() {
        try {
            String nome = nomeField.getText();
            int autorId = extrairIdSelecionado(autorComboBox);
            int categoriaId = extrairIdSelecionado(categoriaComboBox);
            int editoraId = extrairIdSelecionado(editoraComboBox);
            Integer reservaId = extrairIdSelecionado(reservaComboBox);

            controller.salvarLivro(nome, autorId, categoriaId, editoraId, reservaId);
            JOptionPane.showMessageDialog(this, "Livro salvo com sucesso!");
            limparFormulario();
            carregarTabela();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar o livro: " + e.getMessage());
        }
    }

    private void limparFormulario() {
        nomeField.setText("");
        autorComboBox.setSelectedIndex(0);
        categoriaComboBox.setSelectedIndex(0);
        editoraComboBox.setSelectedIndex(0);
        reservaComboBox.setSelectedIndex(0);
    }

    private void carregarCombo(JComboBox<String> comboBox, List<String> dados) {
        comboBox.removeAllItems();
        comboBox.addItem("Selecione...");
        for (String item : dados) {
            comboBox.addItem(item);
        }
    }

    private int extrairIdSelecionado(JComboBox<String> comboBox) {
        String selecionado = (String) comboBox.getSelectedItem();
        if (selecionado != null && !selecionado.equals("Selecione...")) {
            return Integer.parseInt(selecionado.split(" - ")[0]);
        }
        return 0; // Retorna 0 ou lança uma exceção dependendo da lógica de validação
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaLivro telaLivro = new TelaLivro();
            telaLivro.setVisible(true);
        });
    }
}

