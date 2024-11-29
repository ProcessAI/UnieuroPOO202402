package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

	private static final String URL = "jdbc:postgresql://localhost:5433/biblioteca";
    private static final String USER = "postgres";
    private static final String PASSWORD = "02122004d";
    
    //sigleton
    private static Connection conexao = null;
    

    public static void testConnection() {
    	if(Conexao.conexao == null)
    		System.out.println("BD NÃO CONECTADO");
    	else
    		System.out.println("BD CONECTADO");
    }	

	public static Connection getConexao() {
		try {
			
			if(Conexao.conexao == null)
			{
				Conexao.conexao = DriverManager.getConnection(URL, USER, PASSWORD);
			}
			return Conexao.conexao;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
    
}
