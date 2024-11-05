package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.ControllerLogin;

public class TelaLogin extends JFrame{
	
	private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
	
	public TelaLogin()
	{
	
		setTitle("Tela de Login");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Criação dos componentes
        JLabel usernameLabel = new JLabel("Usuário:");
        usernameField = new JTextField(20);
        
        JLabel passwordLabel = new JLabel("Senha:");
        passwordField = new JPasswordField(20);
        
        loginButton = new JButton("Login");
        
        // Ação do botão de login
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                
                ControllerLogin control = new ControllerLogin();
                if(control.validarLogin(username, password))
                	JOptionPane.showMessageDialog(null, "Login bem-sucedido!");
                else
                	JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos.");
                
                // Aqui você pode adicionar a lógica de validação
                /*if (username.equals("admin") && password.equals("admin")) {
                    JOptionPane.showMessageDialog(null, "Login bem-sucedido!");
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos.");
                }*/
            }
        });
        
        // Layout
        JPanel panel = new JPanel();
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        
        add(panel);
	}

}
