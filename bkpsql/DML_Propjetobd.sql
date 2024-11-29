-- DML USUÁRIOS

SELECT * FROM usuario;

INSERT INTO usuario(usuarionome, usuarioemail, usuariosenha)
VALUES(
	'Guilherme Henrique',
	'Admin@2024gmail.com',
	'123456'
);

UPDATE usuario SET usuariostatus = 1 WHERE idusuario = 1;