package view;

import controller.EditoraController;
import model.Editora;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EditoraView extends JFrame {
    private static final long serialVersionUID = 1L;

    private EditoraController controller;
    private JTextField txtNome;
    private JButton btnAdicionar, btnListar, btnAtualizar, btnDeletar;
    private JTable tabelaEditoras;

    public EditoraView() {
        controller = new EditoraController(this); 

        setTitle("Cadastro de Editoras");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(245, 245, 245)); 

        
        JPanel panelTop = new JPanel();
        panelTop.setLayout(new GridBagLayout());
        panelTop.setBackground(new Color(30, 144, 255));  
        GridBagConstraints gbc = new GridBagConstraints();
        
        JLabel lblNome = new JLabel("Nome da Editora:");
        lblNome.setFont(new Font("Arial", Font.BOLD, 14));
        lblNome.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 5, 10);  
        panelTop.add(lblNome, gbc);

        txtNome = new JTextField(20);
        txtNome.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 5, 10);
        panelTop.add(txtNome, gbc);

        
        btnAdicionar = new JButton("Adicionar");
        btnAdicionar.setFont(new Font("Arial", Font.BOLD, 12));
        btnAdicionar.setBackground(new Color(50, 205, 50)); 
        btnAdicionar.setForeground(Color.WHITE);
        btnAdicionar.setFocusPainted(false);

        btnListar = new JButton("Listar");
        btnListar.setFont(new Font("Arial", Font.BOLD, 12));
        btnListar.setBackground(new Color(70, 130, 180));
        btnListar.setFocusPainted(false);

        btnAtualizar = new JButton("Atualizar");
        btnAtualizar.setFont(new Font("Arial", Font.BOLD, 12));
        btnAtualizar.setBackground(new Color(255, 165, 0));
        btnAtualizar.setForeground(Color.WHITE);
        btnAtualizar.setFocusPainted(false);

        btnDeletar = new JButton("Deletar");
        btnDeletar.setFont(new Font("Arial", Font.BOLD, 12));
        btnDeletar.setBackground(new Color(255, 69, 0)); 
        btnDeletar.setForeground(Color.WHITE);
        btnDeletar.setFocusPainted(false);

        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new FlowLayout());
        panelButtons.setBackground(new Color(30, 144, 255));
        panelButtons.add(btnAdicionar);
        panelButtons.add(btnListar);
        panelButtons.add(btnAtualizar);
        panelButtons.add(btnDeletar);

        add(panelTop, BorderLayout.NORTH);
        add(panelButtons, BorderLayout.CENTER);

        
        String[] colunas = {"ID", "Nome"};
        Object[][] dados = {};
        tabelaEditoras = new JTable(dados, colunas);
        tabelaEditoras.setFont(new Font("Arial", Font.PLAIN, 12));
        tabelaEditoras.setSelectionBackground(new Color(173, 216, 230));
        JScrollPane scrollPane = new JScrollPane(tabelaEditoras);
        add(scrollPane, BorderLayout.SOUTH);

       
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = txtNome.getText();
                controller.adicionarEditora(nome); 
            }
        });

        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.atualizarTabelaNaView();              }
        });

        btnAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idSelecionado = getIdSelecionado();  
                if (idSelecionado != -1) {
                    String novoNome = txtNome.getText();  
                    controller.atualizarEditora(idSelecionado, novoNome);  
                }
            }
        });

        btnDeletar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idSelecionado = getIdSelecionado();  
                if (idSelecionado != -1) {
                    controller.deletarEditora(idSelecionado);  
                }
            }
        });
    }

    private int getIdSelecionado() {
        int linhaSelecionada = tabelaEditoras.getSelectedRow();  
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma editora primeiro.");
            return -1;  
        }

        
        return Integer.parseInt(tabelaEditoras.getValueAt(linhaSelecionada, 0).toString());
    }

    
    public void atualizarTabela(List<Editora> editoras) {
        String[][] dados = new String[editoras.size()][2];
        for (int i = 0; i < editoras.size(); i++) {
            dados[i][0] = String.valueOf(editoras.get(i).getIdeditora());
            dados[i][1] = editoras.get(i).getNome();
        }
        tabelaEditoras.setModel(new javax.swing.table.DefaultTableModel(dados, new String[]{"ID", "Nome"}));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EditoraView view = new EditoraView();
            view.setVisible(true);
        });
    }
}
