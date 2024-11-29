package view;

import controller.ControllerLivro;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TelaLivro extends JFrame {

    private final JTable tabelaLivros;
    private final JTextField nomeField;
    private final JComboBox<String> autorComboBox;
    private final JComboBox<String> categoriaComboBox;
    private final JComboBox<String> editoraComboBox;
    private final JComboBox<String> reservaComboBox;
    private final JButton salvarButton;
    private final JButton limparButton;
    private final ControllerLivro controller;
    private final JPanel contentPanel;

    public TelaLivro() {
        controller = new ControllerLivro();
        setTitle("Gerenciamento de Livros");
        setSize(800, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Navbar com o novo botão "Deletar"
        JPanel navbar = new JPanel();
        navbar.setLayout(new FlowLayout(FlowLayout.LEFT));
        navbar.setBackground(Color.DARK_GRAY);
        navbar.setPreferredSize(new Dimension(800, 40));
        JButton visuButton = new JButton("Visualizar");
        JButton addButton = new JButton("Adicionar");
        navbar.add(visuButton);
        navbar.add(addButton);
        add(navbar, BorderLayout.NORTH);

        contentPanel = new JPanel();
        contentPanel.setLayout(new CardLayout());
        add(contentPanel, BorderLayout.CENTER);

        // Painel do formulário
        JPanel painelFormulario = new JPanel(new GridLayout(6, 2, 10, 10));
        painelFormulario.setBorder(BorderFactory.createTitledBorder("Gerenciar Livro"));
        nomeField = new JTextField();
        painelFormulario.add(new JLabel("Nome:"));
        painelFormulario.add(nomeField);
        autorComboBox = new JComboBox<>();
        carregarCombo(autorComboBox, controller.listarAutores());
        painelFormulario.add(new JLabel("Autor:"));
        painelFormulario.add(autorComboBox);
        categoriaComboBox = new JComboBox<>();
        carregarCombo(categoriaComboBox, controller.listarCategorias());
        painelFormulario.add(new JLabel("Categoria:"));
        painelFormulario.add(categoriaComboBox);
        editoraComboBox = new JComboBox<>();
        carregarCombo(editoraComboBox, controller.listarEditoras());
        painelFormulario.add(new JLabel("Editora:"));
        painelFormulario.add(editoraComboBox);
        reservaComboBox = new JComboBox<>();
        carregarCombo(reservaComboBox, controller.listarReservas());
        painelFormulario.add(new JLabel("Reserva:"));
        painelFormulario.add(reservaComboBox);
        salvarButton = new JButton("Salvar");
        limparButton = new JButton("Limpar");
        painelFormulario.add(salvarButton);
        painelFormulario.add(limparButton);

        // Painel da tabela
        JPanel painelTabela = new JPanel(new BorderLayout());
        tabelaLivros = new JTable();
        carregarTabela();
        JScrollPane scrollPane = new JScrollPane(tabelaLivros);
        painelTabela.add(scrollPane, BorderLayout.CENTER);

        contentPanel.add(painelFormulario, "Formulario");
        contentPanel.add(painelTabela, "Tabela");

        salvarButton.addActionListener(e -> salvarLivro());
        limparButton.addActionListener(e -> limparFormulario());
        visuButton.addActionListener(e -> mostrarTabela());
        addButton.addActionListener(e -> mostrarFormulario());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaLivro telaLivro = new TelaLivro();
            telaLivro.setVisible(true);
        });
    }

    private void carregarTabela() {
        List<String[]> livros = controller.listarLivros();
        String[] colunas = {"ID", "Nome", "Autor", "Categoria", "Editora", "Reserva", "Ações"};
        String[][] dados = new String[livros.size()][colunas.length];

        for (int i = 0; i < livros.size(); i++) {
            String[] livro = livros.get(i);
            for (int j = 0; j < livro.length; j++) {
                dados[i][j] = livro[j];
            }
            // Adiciona o botão "Deletar" na coluna de ações
            dados[i][colunas.length - 1] = "Deletar";
        }

        tabelaLivros.setModel(new DefaultTableModel(dados, colunas));

        // Define o renderizador para a coluna de ações (botão "Deletar")
        tabelaLivros.getColumnModel().getColumn(6).setCellRenderer(new ButtonRenderer());

        // Define o editor para os botões na tabela
        tabelaLivros.getColumnModel().getColumn(6).setCellEditor(new ButtonEditor(new JCheckBox()));
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
        return 0;
    }

    private void mostrarTabela() {
        CardLayout cl = (CardLayout) contentPanel.getLayout();
        cl.show(contentPanel, "Tabela");
    }

    private void mostrarFormulario() {
        CardLayout cl = (CardLayout) contentPanel.getLayout();
        cl.show(contentPanel, "Formulario");
    }

    private class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setText("Deletar");
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return this;
        }
    }

    private class ButtonEditor extends DefaultCellEditor {
        protected JButton button;
        private String label;
        private int row;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);

            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    row = tabelaLivros.getSelectedRow();
                    int livroId = Integer.parseInt(tabelaLivros.getValueAt(row, 0).toString());
                    controller.excluirLivro(livroId);
                    carregarTabela();
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            this.row = row;
            label = (value == null) ? "" : value.toString();
            button.setText(label);
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            return label;
        }
    }
}
