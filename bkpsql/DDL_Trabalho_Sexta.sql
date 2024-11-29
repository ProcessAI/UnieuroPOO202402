-- Tabela usuario
CREATE TABLE usuario (
    idusuario SERIAL PRIMARY KEY,
    usuarionome VARCHAR(45) NOT NULL,
    usuarioemail VARCHAR(45) NOT NULL,
    usuariosenha VARCHAR(255) NOT NULL,
	usuariostatus INT
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

ALTER TABLE usuario
ADD COLUMN usuariostatus INT; 

-- DML USUÁRIOS

SELECT * FROM usuario;

INSERT INTO usuario(usuarionome, usuarioemail, usuariosenha)
VALUES(
	'Guilherme Henrique',
	'Admin@2024gmail.com',
	'123456'
);

UPDATE usuario SET usuariostatus = 1 WHERE idusuario = 1;