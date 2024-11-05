import javax.swing.SwingUtilities;

import view.TelaLogin;

public class BibliotecaMain {

	public static void main(String[] args) {
		System.out.println("Iniciar Sistema.");
		SwingUtilities.invokeLater(() -> {
            TelaLogin login = new TelaLogin();
            login.setVisible(true);
        });
	}

}
