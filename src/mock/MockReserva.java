package mock;

import java.util.ArrayList;

public class MockReserva {
    public static String getDataReserva(int id) {
        return switch (id) {
            case 1 -> "2024-11-01";
            case 2 -> "02/01/2021";
            case 3 -> "03/01/2021";
            default -> "Reserva não encontrada";
        };
    }


    public static int getIdReserva(String data) {
        return switch (data) {
            case "2024-11-01" -> 1;
            case "02/01/2021" -> 2;
            case "03/01/2021" -> 3;
            default -> 0;
        };
    }

    public static boolean getStatusById(int id) {
        return switch (id) {
            case 1, 4 -> true;
            default -> false;
        };
    }

    public static ArrayList<String> getAll() {
        ArrayList<String> idnome = new ArrayList<>() {{
            add("1 - 2024-11-01");
            add("2 - 02/01/2021");
            add("3 - 03/01/2021");
        }};
        return idnome;
    }

    public static ArrayList<String> getAllStatus() {
        ArrayList<String> idnome = new ArrayList<>() {{
            add("1 - Reservado");
            add("2 - Disponível");
        }};
        return idnome;
    }
}