import javax.swing.SwingUtilities;

import view.TelaLogin;

public class BibliotecaMain {

	public static String version = "1.0";

	public static void main(String[] args) {
		System.out.println("Iniciar Sistema.");
		SwingUtilities.invokeLater(() -> {
			TelaLogin login = new TelaLogin();
			login.setVisible(true);
		});
	}

}
