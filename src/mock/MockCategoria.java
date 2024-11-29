package mock;

import java.util.ArrayList;

public class MockCategoria {
    public static String getNomeCategoria(int id) {
        return switch (id) {
            case 1 -> "Romance";
            case 2 -> "Ficção Científica";
            case 3 -> "Terror";
            default -> "Categoria não encontrada";
        };
    }

    public static int getIdCategoria(String nome) {
        return switch (nome) {
            case "Romance" -> 1;
            case "Ficção Científica" -> 2;
            case "Terror" -> 3;
            default -> 0;
        };
    }

    public static ArrayList<String> getAll() {
        return new ArrayList<>() {{
            add("1 - Romance");
            add("2 - Ficção Científica");
            add("3 - Terror");
        }};
    }
}
