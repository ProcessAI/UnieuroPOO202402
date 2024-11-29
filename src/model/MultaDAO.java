package model;

import java.util.List;

public interface MultaDAO {

    public boolean cadastrarMulta(Multa multa);  // Método para cadastrar uma multa
    public boolean excluirMulta(int id);         // Método para excluir uma multa
    public List<Multa> listarMultas();          // Método para listar todas as multas
    public void calcularMultas();               // Método para calcular as multas
}

/*– Alteracoes no BD

CREATE OR REPLACE FUNCTION atualizar_multas()
RETURNS void AS $$
BEGIN
    UPDATE multa
    SET valor_atual = valor_atual + (taxa_diaria * (CURRENT_DATE - data_ultima_atualizacao)),
        data_ultima_atualizacao = CURRENT_DATE
    WHERE CURRENT_DATE > data_ultima_atualizacao;
END;
$$ LANGUAGE plpgsql;

SELECT atualizar_multas();
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
	data_ultima_atualizacao DATE DEFAULT CURRENT_DATE,
    CONSTRAINT fk_multa_emprestimo FOREIGN KEY (emprestimo_idemprestimo) REFERENCES emprestimo (idemprestimo)
);


ALTER TABLE usuario
ADD COLUMN usuariostatus INT;
UPDATE usuario SET usuariostatus = 1 WHERE idusuario = 1;

INSERT INTO usuario (usuarionome, usuarioemail, usuariosenha)
VALUES
('João Silva', 'joao.silva@email.com', 'senha123'),
('Maria Oliveira', 'maria.oliveira@email.com', 'senha456');

INSERT INTO editora (nome)
VALUES
('Editora b');

INSERT INTO autor (nome, nacionalidade, data_nascimento)
VALUES
('Carlos Drummond', 'Brasileiro', '1902-10-31'),
('Clarice Lispector', 'Brasileira', '1920-12-10');

INSERT INTO reserva (data_reserva, usuario_idusuario)
VALUES
('2024-11-28', 3),
('2024-11-29', 4);


-- Inserir dados na tabela categoria
INSERT INTO categoria (nome, descricao)
VALUES
('Ficção', 'Livros de ficção literária'),
('Não-Ficção', 'Livros de não-ficção e documentários');

-- Inserir dados na tabela reserva
INSERT INTO reserva (data_reserva, usuario_idusuario)
VALUES
('2024-11-28', 3),
('2024-11-29', 4);

select* from emprestimo
select* from usuario

INSERT INTO livro (nome, autor_idautor, categoria_idcategoria, editora_ideditora, reserva_idreserva)
VALUES
('O Último Poema', 3, 3, 2, 9),
('A Hora da Estrela', 4, 4, 2, 10);


INSERT INTO emprestimo (idemprestimo, data_emprestimo, data_devolucao, livro_idlivro, usuario_idusuario)
VALUES
(1,'2024-11-28', '2024-12-28', 7, 3);
('2024-11-28', '2024-12-28', 7, 3),
('2024-11-29', '2024-12-29', 8, 4);
('2024-11-29', '2024-12-29', 8, 4);

-- Inserção de multa para empréstimo com ID 1, especificando a data de atualização
INSERT INTO multa (valor_base, valor_atual, taxa_diaria, emprestimo_idemprestimo, data_ultima_atualizacao)
VALUES
(10.00, 15.00, 1.00, 3, '2024-11-28');

-- Inserção de multa para empréstimo com ID 2, especificando a data de atualização
INSERT INTO multa (valor_base, valor_atual, taxa_diaria, emprestimo_idemprestimo, data_ultima_atualizacao)
VALUES
(8.00, 18.00, 1.00, 4, '2024-11-27');;*/

