package model;

public class Usuario {
	
	private int idusuario;
	private String usuarionome;
	private String usuarioemail;
	private String usuariosenha;
	private int usuariostatus;
	
	public Usuario(String usuarionome, String usuarioemail, String usuariosenha)
	{
		this.usuarionome = usuarionome;
		this.usuarioemail = usuarioemail;
		this.usuariosenha = usuariosenha;
	}
	
	public Usuario(int idusuario, String usuarionome, String usuarioemail, String usuariosenha, int usuariostatus)
	{
		this.usuariostatus = usuariostatus;
		this.idusuario = idusuario;
		this.usuarionome = usuarionome;
		this.usuarioemail = usuarioemail;
		this.usuariosenha = usuariosenha;
	}
	
	public int getIdusuario() {
		return idusuario;
	}
	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}
	public String getUsuarionome() {
		return usuarionome;
	}
	public void setUsuarionome(String usuarionome) {
		this.usuarionome = usuarionome;
	}
	public String getUsuarioemail() {
		return usuarioemail;
	}
	public void setUsuarioemail(String usuarioemail) {
		this.usuarioemail = usuarioemail;
	}
	public String getUsuariosenha() {
		return usuariosenha;
	}
	public void setUsuariosenha(String usuariosenha) {
		this.usuariosenha = usuariosenha;
	}
	public int getUsuariostatus() {
		return usuariostatus;
	}
	public void setUsuariostatus(int usuariostatus) {
		this.usuariostatus = usuariostatus;
	}
	
	
	

}
