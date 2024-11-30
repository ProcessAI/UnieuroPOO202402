package mock;

import java.util.ArrayList;

public class MockEditora {
    public static String getNomeEditora(int id) {
        return switch (id) {
            case 1 -> "Editora Moderna";
            case 2 -> "Editora Atlas";
            case 3 -> "Editora Saraiva";
            default -> "Editora não encontrada";
        };
    }

    public static int getIdEditora(String nome) {
        return switch (nome) {
            case "Editora Moderna" -> 1;
            case "Editora Atlas" -> 2;
            case "Editora Saraiva" -> 3;
            default -> 0;
        };
    }

    public static ArrayList<String> getAll() {
        return new ArrayList<>() {{
            add("1 - Editora Moderna");
            add("2 - Editora Atlas");
            add("3 - Editora Saraiva");
        }};
    }
}