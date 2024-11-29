package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

	private static final String URL = "jdbc:postgresql://localhost:5432/biblioteca";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123456";
    
    //sigleton
    private static Connection conexao = null;

    // Método para testar a conexão
    public static void testConnection() {
        if (Conexao.conexao == null)
            System.out.println("BD NÃO CONECTADO");
        else
            System.out.println("BD CONECTADO");
    }

    // Método para obter a conexão
    public static Connection getConexao() {
        try {
            if (Conexao.conexao == null || Conexao.conexao.isClosed()) {  // Verifica se a conexão está fechada
                Conexao.conexao = DriverManager.getConnection(URL, USER, PASSWORD);
            }
            return Conexao.conexao;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Método para fechar a conexão
    public static void fecharConexao() {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
