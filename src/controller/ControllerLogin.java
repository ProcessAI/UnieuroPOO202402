package controller;

import model.Conexao;
import model.Usuario;
import model.UsuarioDAO;
import model.UsuarioDAOImpl;

public class ControllerLogin {
	
	public boolean validarLogin(String login, String senha)
	{
		Conexao.testConnection();
		/*if(login.equals("admin") && senha.equals("123456"))
			return true;*/
		UsuarioDAO udao = new UsuarioDAOImpl();
		Usuario usuario = udao.getUsuarioByEmail(login);
		//if(usuario == null) return false;
		if(usuario != null && senha.equals(usuario.getUsuariosenha())) return true;
		//if(senha.equals(usuario.getUsuariosenha())) return true;
		
		return false;
	}

}
