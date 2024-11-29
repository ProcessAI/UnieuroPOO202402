import javax.swing.SwingUtilities;
import model.Conexao;
import view.CategoriaView;

public class BibliotecaMain {

	public static String version = "1.2";

	public static void main(String[] args) {
		System.out.println("Iniciar Sistema.");

		Conexao.testConnection();
		Conexao.getConexao();
		Conexao.testConnection();
/* 		CategoriaView telaCategoria = new CategoriaView();
		telaCategoria.setVisible(true); */
/* 		TelaLivro telaLivro = new TelaLivro();
		telaLivro.setVisible(true); */
		SwingUtilities.invokeLater(() -> {
/* 			TelaLogin login = new TelaLogin();
			login.setVisible(true); */
			CategoriaView telaCategoria = new CategoriaView();
			telaCategoria.setVisible(true);
		});
	}

}
