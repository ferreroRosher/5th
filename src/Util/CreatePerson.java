package Util;

import Collection.*;
import java.time.ZonedDateTime;
import java.util.*;

public class CreatePerson {
    private static final Set<String> COMMANDS = Set.of("HELP", "INFO", "EXIT", "SHOW", "CLEAR", "REMOVE_KEY");
    private static int nextId = 1;

    public static Person createPersonFromInput(Scanner scanner) {
        if (scanner == null) throw new IllegalArgumentException("Ошибка: Scanner не может быть null.");

        System.out.println("Введите данные одной строкой или поочередно:");
        String inputLine = scanner.nextLine().trim().replace("\\n", " ");
        List<String> inputs = Arrays.asList(inputLine.split("\\s+"));
        Iterator<String> iterator = inputs.iterator();

        String name = CommandScanner.nextValidString("Введите имя: ", iterator, s -> !s.isEmpty(), "Имя не может быть пустым.");

        Integer x = CommandScanner.nextValidInt("Введите координату X (целое число ≤ 369): ", iterator, Integer.MIN_VALUE, 369);
        Float y = CommandScanner.nextValidFloat("Введите координату Y (вещественное число > -983): ", iterator, -983);
        Coordinates coordinates = new Coordinates(x, y);

        Long height = CommandScanner.nextValidLong("Введите рост (> 0): ", iterator, 1);

        ZonedDateTime birthday = ZonedDateTime.now();

        String passportID = CommandScanner.nextValidString("Введите passport ID (6-33 символов): ", iterator,
                s -> s.length() >= 6 && s.length() <= 33, "Длина passportID должна быть от 6 до 33 символов.");

        Country nationality = CommandScanner.nextValidEnum("Введите национальность (GERMANY, FRANCE, JAPAN): ", iterator, Country.class);

        Double locX = CommandScanner.nextValidDouble("Введите координату X локации: ", iterator);
        Double locY = CommandScanner.nextValidDouble("Введите координату Y локации: ", iterator);
        Integer locZ = CommandScanner.nextValidInt("Введите координату Z локации (целое): ", iterator, Integer.MIN_VALUE, Integer.MAX_VALUE);
        String locName = CommandScanner.nextValidString("Введите название локации: ", iterator,
                s -> !s.isEmpty(), "Название локации не может быть пустым.");

        Location location = new Location(locX, locY, locZ, locName);

        CommandScanner.disableInputMode();
        int id = generateUniqueID();
        return new Person(id, name, coordinates, height, birthday, passportID, nationality, location);
    }

    public static int generateUniqueID() {
        return nextId++;
    }
}

