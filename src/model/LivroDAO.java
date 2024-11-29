package model;

import java.sql.SQLException;
import java.util.ArrayList;

public interface LivroDAO {



    Livro getLivroById(int idlivro) throws SQLException;

    ArrayList<Livro> getLivroByNome(String nome);

    ArrayList<Livro> getAllLivros();

    boolean inserirLivro(Livro livro);

    boolean atualizarLivro(Livro livro);

    boolean deletarLivro(int idlivro);
}
