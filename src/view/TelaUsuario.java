package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controller.ControllerUsuario;
import model.Usuario;

import java.awt.*;
import java.util.ArrayList;

public class TelaUsuario extends JFrame {
    
    // Construtor da classe
    public TelaUsuario() {
        // Título da janela
        setTitle("Tela de Dados de Usuários");
        
        // Define o tamanho da janela
        setSize(600, 400);
        
        // Configura o fechamento da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Definir o layout
        setLayout(new BorderLayout());
        
        // Dados de exemplo (nome, email, idade)
        /*Object[][] dados = {
            {"João Silva", "joao.silva@gmail.com", 28},
            {"Maria Oliveira", "maria.oliveira@hotmail.com", 34},
            {"Carlos Souza", "carlos.souza@yahoo.com", 25},
            {"Ana Pereira", "ana.pereira@outlook.com", 22},
            {"Luiz Almeida", "luiz.almeida@icloud.com", 30}
        };*/

        // Nomes das colunas
        String[] colunas = {"Nome", "E-mail", "Senha"};
        
        ControllerUsuario control = new ControllerUsuario();
        ArrayList<Usuario> usuarios = control.carregarUsuarios();
        

        // Criando o modelo da tabela com os dados
        //DefaultTableModel modelo = new DefaultTableModel(dados, colunas);

        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Nome");
        modelo.addColumn("Email");
        modelo.addColumn("Senha");
        
        for(Usuario usuario: usuarios)
        {
        	modelo.addRow(new Object[]{usuario.getUsuarionome(), usuario.getUsuarioemail(), usuario.getUsuariosenha()});
        }
        
        // Criando o JTable e passando o modelo
        JTable tabela = new JTable(modelo);

        // Adicionando a tabela em um JScrollPane (para permitir rolagem)
        JScrollPane scrollPane = new JScrollPane(tabela);

        // Adicionando o JScrollPane ao frame
        add(scrollPane, BorderLayout.CENTER);
        
        // Exibindo a janela
        setVisible(true);
    }

    // Método principal para executar o programa
    /*public static void main(String[] args) {
        // Criando e mostrando a tela de dados de usuário
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TelaUsuario();
            }
        });
    }*/
}

