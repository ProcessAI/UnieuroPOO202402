package model;

public interface UsuarioDAO {

	public Usuario getUsuarioById(int idsuario);
	public Usuario getUsuarioByEmail(String usuarioemail);
	public boolean validarUsuario(String usuarioemail, String usuariosenha);
	public boolean inserirUsuario(Usuario usuario);
}
