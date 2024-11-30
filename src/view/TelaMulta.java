package view;

import controller.ControllerMulta;
import model.Multa;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TelaMulta extends JFrame {

    private ControllerMulta controller;
    private JTable tabela;
    private DefaultTableModel modelo;

    private static final long serialVersionUID = 1L;

    public TelaMulta() {
        setTitle("Gestão de Multas");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        controller = new ControllerMulta();
        
        // Layout e tabela
        setLayout(new BorderLayout());
        
        modelo = new DefaultTableModel(new String[]{"ID", "Descrição", "Valor", "Usuário"}, 0);
        tabela = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tabela);
        add(scrollPane, BorderLayout.CENTER);
        
        // Botões
        JPanel panel = new JPanel();
        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnExcluir = new JButton("Excluir");
        panel.add(btnAdicionar);
        panel.add(btnExcluir);
        add(panel, BorderLayout.SOUTH);

        // Carregar e atualizar a tabela
        carregarTabela();

        btnAdicionar.addActionListener(e -> {
            String descricao = JOptionPane.showInputDialog(this, "Descrição da multa:");
            if (descricao == null || descricao.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Descrição não pode ser vazia!");
                return;
            }

            String valorStr = JOptionPane.showInputDialog(this, "Valor da multa:");
            try {
                double valor = Double.parseDouble(valorStr);
                // Aqui criamos a multa com os dados preenchidos
                Multa multa = new Multa(descricao, valor, valor, 0, 1); // Passando o valor de usuário como 1
                if (controller.cadastrarMulta(multa)) {
                    JOptionPane.showMessageDialog(this, "Multa cadastrada com sucesso!");
                    carregarTabela();
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao cadastrar multa.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Valor inválido!");
            }
        });

        btnExcluir.addActionListener(e -> {
            int selectedRow = tabela.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Selecione uma multa para excluir.");
                return;
            }

            int id = (int) modelo.getValueAt(selectedRow, 0);
            if (controller.excluirMulta(id)) {
                JOptionPane.showMessageDialog(this, "Multa excluída com sucesso!");
                carregarTabela();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao excluir multa.");
            }
        });

        setVisible(true);
    }

    private void carregarTabela() {
        modelo.setRowCount(0); // Limpar a tabela
        List<Multa> multas = controller.listarMultas();
        for (Multa multa : multas) {
            modelo.addRow(new Object[]{
                multa.getId(),
                multa.getDescricao(),
                multa.getValorAtual(), // Usando o valor atual da multa
                multa.getNomeUsuario() // Mostrar o nome do usuário
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TelaMulta::new);
    }
}
