package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String URL = "jdbc:postgresql://localhost:5432/biblioteca";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123456";

    private static Connection conexao = null;

    // Testa a conexão
    public static void testConnection() {
        if (conexao == null) {
            System.out.println("BD NÃO CONECTADO");
        } else {
            System.out.println("BD CONECTADO");
        }
    }

    // Método para obter a conexão (thread-safe)
    public static Connection getConexao() {
        try {
            // Verifica se a conexão está nula ou fechada
            if (conexao == null || conexao.isClosed()) {
                // Se estiver, cria uma nova conexão
                conexao = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Conexão estabelecida com sucesso.");
            }
            return conexao;
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    // Fecha a conexão
    public static void closeConnection() {
        if (conexao != null) {
            try {
                conexao.close();
                conexao = null;
                System.out.println("Conexão fechada com sucesso.");
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
