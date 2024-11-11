package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAOImpl implements UsuarioDAO {

	@Override
	public Usuario getUsuarioById(int idsuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario getUsuarioByEmail(String usuarioemail) {
		
		String sql = "SELECT * FROM usuario WHERE usuarioemail='" + usuarioemail + "';";

        try (Connection connection = Conexao.getConexao();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario(rs.getInt("idusuario"), rs.getString("usuarionome"), rs.getString("usuarioemail"), rs.getString("usuariosenha"), rs.getInt("usuariostatus"));
                return usuario;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		return null;
	}

	@Override
	public boolean validarUsuario(String usuarioemail, String usuariosenha) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean inserirUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return false;
	}

}
