package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.ResultSet;

public class EmprestimoDAO {

    private Connection con;

    public EmprestimoDAO(Connection con) {
        this.con = con;
    }

    public boolean salvarEmprestimo(int usuarioId, int livroId, Date dataDevolucao) {
        // Alterar o nome da tabela e das colunas conforme o esquema do banco
        String sql = "INSERT INTO emprestimo (usuario_idusuario, livro_idlivro, data_emprestimo, data_devolucao) VALUES (?, ?, CURRENT_DATE, ?)";
        
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId); // ID do Usuário
            stmt.setInt(2, livroId); // ID do Livro
            stmt.setDate(3, new java.sql.Date(dataDevolucao.getTime())); // Data de Devolução

            // Executa a inserção no banco de dados
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Retorna true se a inserção foi bem-sucedida
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Retorna false em caso de erro
        }
    }
    
    public boolean excluirEmprestimo(int idEmprestimo) {
        String sql = "DELETE FROM emprestimo WHERE idemprestimo = ?";
        
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            // Definir o ID do empréstimo a ser excluído
            stmt.setInt(1, idEmprestimo);
            
            int rowsAffected = stmt.executeUpdate();
            
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao excluir empréstimo: " + e.getMessage());
            return false;
        }
    }
    
    public Object[][] buscarEmprestimo(int idEmprestimo) {
        String sql = "SELECT idemprestimo, usuario_idusuario, livro_idlivro, data_devolucao FROM emprestimo WHERE idemprestimo = ?";
        
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idEmprestimo); // Define o ID do empréstimo a ser buscado
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                // Caso encontre um empréstimo, retornamos os dados em um array bidimensional
                Object[][] dados = new Object[1][4]; // Só terá uma linha com os dados do empréstimo
                dados[0][0] = rs.getInt("idemprestimo");
                dados[0][1] = rs.getInt("usuario_idusuario");
                dados[0][2] = rs.getInt("livro_idlivro");
                dados[0][3] = rs.getDate("data_devolucao");
                return dados;
            } else {
                // Se não encontrar, retorna null ou um array vazio
                return new Object[0][0];
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new Object[0][0];
        }
    }
    
    public boolean atualizarEmprestimo(int idEmprestimo, int usuarioId, int livroId, Date dataDevolucao) {
        String sql = "UPDATE emprestimo SET usuario_idusuario = ?, livro_idlivro = ?, data_devolucao = ? WHERE idemprestimo = ?";
        
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId); // ID do Usuário
            stmt.setInt(2, livroId); // ID do Livro
            stmt.setDate(3, dataDevolucao); // Data de Devolução
            stmt.setInt(4, idEmprestimo); // ID do Empréstimo para identificar qual atualizar

            int rowsAffected = stmt.executeUpdate(); // Executa a atualização
            
            return rowsAffected > 0; // Retorna true se a atualização foi bem-sucedida
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Retorna false em caso de erro
        }
    }
    
    public Object[][] listarEmprestimos() {
        String sql = "SELECT idemprestimo, usuario_idusuario, livro_idlivro, data_devolucao FROM emprestimo";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            
            rs.last();
            int totalRegistros = rs.getRow();
            rs.beforeFirst();
            
            Object[][] dados = new Object[totalRegistros][4];
            int i = 0;
            
            while (rs.next()) {
                dados[i][0] = rs.getInt("idemprestimo");
                dados[i][1] = rs.getInt("usuario_idusuario");
                dados[i][2] = rs.getInt("livro_idlivro");
                dados[i][3] = rs.getDate("data_devolucao");
                i++;
            }
            return dados;
        } catch (SQLException e) {
            e.printStackTrace();
            return new Object[0][0];
        }
    }


}

/*-- Tabela emprestimo
	CREATE TABLE emprestimo (
	    idemprestimo SERIAL PRIMARY KEY,
	    data_emprestimo VARCHAR(45) NOT NULL,
	    data_devolucao VARCHAR(45) NOT NULL,
	    livro_idlivro INT NOT NULL,
	    usuario_idusuario INT NOT NULL,
	    CONSTRAINT fk_emprestimo_livro FOREIGN KEY (livro_idlivro) REFERENCES livro (idlivro),
	    CONSTRAINT fk_emprestimo_usuario FOREIGN KEY (usuario_idusuario) REFERENCES usuario (idusuario)
	);
*/