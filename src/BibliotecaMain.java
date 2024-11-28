import java.sql.Connection;

import javax.swing.SwingUtilities;

import model.Conexao;
import view.TelaListarReservas;
import view.TelaLogin;
import view.TelaPrincipalReserva;
import view.TelaReserva;

public class BibliotecaMain {

	public static String version = "1.2";

	public static void main(String[] args) {
		System.out.println("Iniciar Sistema.");

		Conexao.testConnection();
		Conexao.getConexao();
		Conexao.testConnection();

		SwingUtilities.invokeLater(() -> {
			/*TelaLogin login = new TelaLogin();
			login.setVisible(true);
			TelaReserva tela = new TelaReserva();
	        tela.setVisible(true);*/
			// Exemplo para abrir a tela de listar reservas
			/*TelaListarReservas listarReservas = new TelaListarReservas();
			listarReservas.setVisible(true);*/
			TelaPrincipalReserva agrupar = new TelaPrincipalReserva();
			agrupar.setVisible(true);
		});
	}

}
