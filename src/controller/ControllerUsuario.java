package controller;

import java.util.ArrayList;
import model.UsuarioDAO;
import model.UsuarioDAOImpl;
import model.Usuario;


public class ControllerUsuario {
	
	public ArrayList<Usuario> carregarUsuarios()
	{
		UsuarioDAO udao = new UsuarioDAOImpl();
		ArrayList<Usuario> usuarios = udao.getAllUsuarios();
		return usuarios;
	}

}
