package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EditoraDAO {


    public EditoraDAO() {
    }
    public void addEditora(Editora editora) {
        String sql = "INSERT INTO editora (nome) VALUES (?)";
        Connection connection = Conexao.getConexao();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, editora.getNome());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Editora> listarEditoras() {
        List<Editora> editoras = new ArrayList<>();
        String sql = "SELECT * FROM editora";
        Connection connection = Conexao.getConexao();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("ideditora");
                String nome = rs.getString("nome");
                editoras.add(new Editora(id, nome));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return editoras;
    }

    public void updateEditora(Editora editora) {
        String sql = "UPDATE editora SET nome = ? WHERE ideditora = ?";
        Connection connection = Conexao.getConexao();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, editora.getNome());
            stmt.setInt(2, editora.getIdeditora());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteEditora(int id) {
        String sql = "DELETE FROM editora WHERE ideditora = ?";
        Connection connection = Conexao.getConexao();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

/*

SQL 

-- Tabela usuario
CREATE TABLE usuario (
    idusuario SERIAL PRIMARY KEY,
    usuarionome VARCHAR(45) NOT NULL,
    usuarioemail VARCHAR(45) NOT NULL,
    usuariosenha VARCHAR(255) NOT NULL
);

-- Tabela editora
CREATE TABLE editora (
    ideditora SERIAL PRIMARY KEY,
    nome VARCHAR(45) NOT NULL
);

-- Tabela autor
CREATE TABLE autor (
    idautor SERIAL PRIMARY KEY,
    nome VARCHAR(45) NOT NULL,
    nacionalidade VARCHAR(45),
    data_nascimento DATE
);

-- Tabela categoria
CREATE TABLE categoria (
    idcategoria SERIAL PRIMARY KEY,
    nome VARCHAR(45) NOT NULL,
    descricao VARCHAR(45)
);

-- Tabela reserva
CREATE TABLE reserva (
    idreserva SERIAL PRIMARY KEY,
    data_reserva DATE NOT NULL,
    usuario_idusuario INT NOT NULL,
    CONSTRAINT fk_reserva_usuario FOREIGN KEY (usuario_idusuario) REFERENCES usuario (idusuario)
);

-- Tabela livro
CREATE TABLE livro (
    idlivro SERIAL PRIMARY KEY,
    nome VARCHAR(45) NOT NULL,
    autor_idautor INT NOT NULL,
    categoria_idcategoria INT NOT NULL,
    editora_ideditora INT NOT NULL,
    reserva_idreserva INT,
    CONSTRAINT fk_livro_autor FOREIGN KEY (autor_idautor) REFERENCES autor (idautor),
    CONSTRAINT fk_livro_categoria FOREIGN KEY (categoria_idcategoria) REFERENCES categoria (idcategoria),
    CONSTRAINT fk_livro_editora FOREIGN KEY (editora_ideditora) REFERENCES editora (ideditora),
    CONSTRAINT fk_livro_reserva FOREIGN KEY (reserva_idreserva) REFERENCES reserva (idreserva)
);

-- Tabela emprestimo
CREATE TABLE emprestimo (
    idemprestimo SERIAL PRIMARY KEY,
    data_emprestimo VARCHAR(45) NOT NULL,
    data_devolucao VARCHAR(45) NOT NULL,
    livro_idlivro INT NOT NULL,
    usuario_idusuario INT NOT NULL,
    CONSTRAINT fk_emprestimo_livro FOREIGN KEY (livro_idlivro) REFERENCES livro (idlivro),
    CONSTRAINT fk_emprestimo_usuario FOREIGN KEY (usuario_idusuario) REFERENCES usuario (idusuario)
);

-- Tabela multa
CREATE TABLE multa (
    idmulta SERIAL PRIMARY KEY,
    valor_base FLOAT NOT NULL,
    valor_atual FLOAT NOT NULL,
    taxa_diaria FLOAT NOT NULL,
    emprestimo_idemprestimo INT NOT NULL,
    CONSTRAINT fk_multa_emprestimo FOREIGN KEY (emprestimo_idemprestimo) REFERENCES emprestimo (idemprestimo)
);

INSERT INTO editora (nome) VALUES ('Editora Alegria');
INSERT INTO editora (nome) VALUES ('Editora Cultura');
INSERT INTO editora (nome) VALUES ('Editora Nova História');
INSERT INTO editora (nome) VALUES ('Editora Saber');
INSERT INTO editora (nome) VALUES ('Editora Mundo Literário');
*/
