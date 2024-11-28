package model;

import java.util.ArrayList;

public interface UsuarioDAO {

	public Usuario getUsuarioById(int idsuario);
	public Usuario getUsuarioByEmail(String usuarioemail);
	public boolean validarUsuario(String usuarioemail, String usuariosenha);
	public boolean inserirUsuario(Usuario usuario);
	public ArrayList<Usuario> getAllUsuarios();
}
