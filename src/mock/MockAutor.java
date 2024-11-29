package mock;

import java.util.ArrayList;

public class MockAutor {
    public static String getNomeAutor(int id) {
        return switch (id) {
            case 1 -> "Machado de Assis";
            case 2 -> "J. K. Rowling";
            case 3 -> "Gabriel García Márquez";
            default -> "Autor não encontrado";
        };
    }

    public static int getIdAutor(String nome) {
        return switch (nome) {
            case "Machado de Assis" -> 1;
            case "J. K. Rowling" -> 2;
            case "Gabriel García Márquez" -> 3;
            default -> 0;
        };

    }

    public static ArrayList<String> getAll() {
        return new ArrayList<>() {{
            add("1 - Machado de Assis");
            add("2 - J. K. Rowling");
            add("3 - Gabriel García Márquez");
        }};
    }
}

