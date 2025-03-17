package Util;

import Collection.Person;
import Collection.Coordinates;
import Collection.Country;
import Collection.Location;
import java.util.Iterator;
import java.util.List;
import java.util.Arrays;
import java.time.ZonedDateTime;
import java.util.Scanner;
import java.util.Set;

/*public class CreatePerson {

    private static final Set<String> COMMANDS = Set.of("HELP", "INFO", "EXIT", "SHOW", "CLEAR");

    public static Person createPersonFromInput(Scanner scanner) {
        if (scanner == null) throw new IllegalArgumentException("Ошибка: Scanner не может быть null.");

        System.out.print("Введите имя: ");
        String name = checkForCommand(scanner);
        if (name == null || !CommandScanner.isInputMode()) return null;

        System.out.print("Введите координату X (≤ 369): ");
        Integer x = readInteger(scanner, 1, 369);
        if (x == null || !CommandScanner.isInputMode()) return null;

        System.out.print("Введите координату Y (> -983): ");
        float y = readFloat(scanner, -982);
        if (y == Float.MIN_VALUE || !CommandScanner.isInputMode()) return null;

        Coordinates coordinates = new Coordinates(x, y);

        System.out.print("Введите рост (> 0): ");
        long height = readLong(scanner, 1);
        if (height == Long.MIN_VALUE || !CommandScanner.isInputMode()) return null;

        ZonedDateTime birthday = ZonedDateTime.now();

        System.out.print("Введите passport ID (6-33 символов): ");
        String passportID = checkForCommand(scanner);
        if (passportID == null || passportID.length() < 6 || passportID.length() > 33 || !CommandScanner.isInputMode()) return null;

        System.out.print("Введите национальность (GERMANY, FRANCE, JAPAN): ");
        Country nationality = readCountry(scanner);
        if (nationality == null || !CommandScanner.isInputMode()) return null;

        System.out.print("Введите координату X локации: ");
        double locX = readDouble(scanner);
        if (locX == Double.MIN_VALUE || !CommandScanner.isInputMode()) return null;

        System.out.print("Введите координату Y локации (не может быть null): ");
        Double locY = readDouble(scanner);
        if (locY == Double.MIN_VALUE || !CommandScanner.isInputMode()) return null;

        System.out.print("Введите координату Z локации: ");
        Integer locZ = readInteger(scanner, Integer.MIN_VALUE, Integer.MAX_VALUE);
        if (locZ == null || !CommandScanner.isInputMode()) return null;

        System.out.print("Введите название локации: ");
        String locName = checkForCommand(scanner);
        if (locName == null || !CommandScanner.isInputMode()) return null;

        Location location = new Location(locX, locY, locZ, locName);

        CommandScanner.disableInputMode(); // ✅ `insert` завершает ввод
        return new Person(name, coordinates, height, birthday, passportID, nationality, location);
    }

    private static String checkForCommand(Scanner scanner) {
        String input = scanner.nextLine().trim();
        if (COMMANDS.contains(input.toUpperCase())) {
            System.out.println("⛔ Ввод отменен. Выполняется команда: " + input);
            CommandScanner.disableInputMode();
            Manager.executeCommand(input.toUpperCase()); // ✅ Выполняем команду
            return null; // ✅ Прерываем `insert`
        }
        return input;
    }

    private static Integer readInteger(Scanner scanner, int min, int max) {
        while (CommandScanner.isInputMode()) {
            String input = checkForCommand(scanner);
            if (input == null || !CommandScanner.isInputMode()) return null;
            try {
                int value = Integer.parseInt(input);
                if (value >= min && value <= max) return value;
                System.out.print("Ошибка: Число должно быть в диапазоне " + min + " - " + max + ". Повторите ввод: ");
            } catch (NumberFormatException e) {
                System.out.print("Ошибка: Введите целое число. Повторите ввод: ");
            }
        }
        return null;
    }

    private static float readFloat(Scanner scanner, float min) {
        while (CommandScanner.isInputMode()) {
            String input = checkForCommand(scanner);
            if (input == null || !CommandScanner.isInputMode()) return Float.MIN_VALUE;
            try {
                float value = Float.parseFloat(input);
                if (value > min) return value;
                System.out.print("Ошибка: Число должно быть больше " + min + ". Повторите ввод: ");
            } catch (NumberFormatException e) {
                System.out.print("Ошибка: Введите число. Повторите ввод: ");
            }
        }
        return Float.MIN_VALUE;
    }

    private static long readLong(Scanner scanner, long min) {
        while (CommandScanner.isInputMode()) {
            String input = checkForCommand(scanner);
            if (input == null || !CommandScanner.isInputMode()) return Long.MIN_VALUE;
            try {
                long value = Long.parseLong(input);
                if (value > min) return value;
                System.out.print("Ошибка: Число должно быть больше " + min + ". Повторите ввод: ");
            } catch (NumberFormatException e) {
                System.out.print("Ошибка: Введите целое число. Повторите ввод: ");
            }
        }
        return Long.MIN_VALUE;
    }

    private static double readDouble(Scanner scanner) {
        while (CommandScanner.isInputMode()) {
            String input = checkForCommand(scanner);
            if (input == null || !CommandScanner.isInputMode()) return Double.MIN_VALUE;
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.print("Ошибка: Введите число. Повторите ввод: ");
            }
        }
        return Double.MIN_VALUE;
    }

    private static Country readCountry(Scanner scanner) {
        while (CommandScanner.isInputMode()) {
            String input = checkForCommand(scanner);
            if (input == null || !CommandScanner.isInputMode()) return null;
            try {
                return Country.valueOf(input.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.print("Ошибка: Введите GERMANY, FRANCE или JAPAN. Повторите ввод: ");
            }
        }
        return null;
    }
} */

public class CreatePerson {

    private static final Set<String> COMMANDS = Set.of("HELP", "INFO", "EXIT", "SHOW", "CLEAR","REMOVE_KEY");
    private static int nextId = 1;

    public static Person createPersonFromInput(Scanner scanner) {
        if (scanner == null) throw new IllegalArgumentException("Ошибка: Scanner не может быть null.");

        System.out.println("Введите данные одной строкой или вводите по очереди:");
        String inputLine = scanner.nextLine().trim().replace("\n", " "); //  Убираем `\n`
        List<String> inputs = Arrays.asList(inputLine.split("\\s+"));
        Iterator<String> iterator = inputs.iterator();

        String name = getNextValidString("Введите имя: ", iterator, scanner, s -> !s.isEmpty(), "Имя не может быть пустым.");
        if (name == null) return null;

        Integer x = getNextValidInteger("Введите координату X (≤ 369): ", iterator, scanner, 1, 369);
        if (x == null) return null;

        float y = getNextValidFloat("Введите координату Y (> -983): ", iterator, scanner, -982);
        if (y == Float.MIN_VALUE) return null;

        Coordinates coordinates = new Coordinates(x, y);

        long height = getNextValidLong("Введите рост (> 0): ", iterator, scanner, 1);
        if (height == Long.MIN_VALUE) return null;

        ZonedDateTime birthday = ZonedDateTime.now(); //  Временная заглушка

        String passportID = getNextValidString("Введите passport ID (6-33 символов): ", iterator, scanner, s -> s.length() >= 6 && s.length() <= 33, "Длина passportID должна быть 6-33 символов.");
        if (passportID == null) return null;

        Country nationality = getNextValidCountry("Введите национальность (GERMANY, FRANCE, JAPAN): ", iterator, scanner);
        if (nationality == null) return null;

        double locX = getNextValidDouble("Введите координату X локации: ", iterator, scanner);
        if (locX == Double.MIN_VALUE) return null;

        double locY = getNextValidDouble("Введите координату Y локации (не может быть null): ", iterator, scanner);
        if (locY == Double.MIN_VALUE) return null;

        Integer locZ = getNextValidInteger("Введите координату Z локации: ", iterator, scanner, Integer.MIN_VALUE, Integer.MAX_VALUE);
        if (locZ == null) return null;

        String locName = getNextValidString("Введите название локации: ", iterator, scanner, s -> !s.isEmpty(), "Название не может быть пустым.");
        if (locName == null) return null;

        Location location = new Location(locX, locY, locZ, locName);

        CommandScanner.disableInputMode();
        Integer id = generateUniqueID();
        return new Person(id, name, coordinates, height, birthday, passportID, nationality, location);
    }

    private static String getNextValidString(String prompt, Iterator<String> iterator, Scanner scanner,
                                             java.util.function.Predicate<String> validator, String errorMessage) {
        while (true) {
            String input = iterator.hasNext() ? iterator.next() : scanner.nextLine().trim();
            if (COMMANDS.contains(input.toUpperCase())) {
                System.out.println("Ввод отменен. Выполняется команда: " + input);
                CommandScanner.disableInputMode();
                Manager.executeCommand(input.toUpperCase());
                return null;
            }
            if (validator.test(input)) return input;
            System.out.print("Ошибка: " + errorMessage + " Повторите ввод: ");
        }
    }
    private static Country getNextValidCountry(String prompt, Iterator<String> iterator, Scanner scanner) {
        while (CommandScanner.isInputMode()) {
            String input = iterator.hasNext() ? iterator.next() : scanner.nextLine().trim();
            if (COMMANDS.contains(input.toUpperCase())) {
                System.out.println(" Ввод отменен. Выполняется команда: " + input);
                CommandScanner.disableInputMode();
                Manager.executeCommand(input.toUpperCase());
                return null;
            }
            try {
                return Country.valueOf(input.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.print("Ошибка: Введите GERMANY, FRANCE или JAPAN. Повторите ввод: ");
            }
        }
        return null;
    }


    private static Integer getNextValidInteger(String prompt, Iterator<String> iterator, Scanner scanner, int min, int max) {
        while (CommandScanner.isInputMode()) {
            String input = iterator.hasNext() ? iterator.next() : scanner.nextLine().trim();
            if (COMMANDS.contains(input.toUpperCase())) {
                System.out.println("Ввод отменен. Выполняется команда: " + input);
                CommandScanner.disableInputMode();
                Manager.executeCommand(input.toUpperCase());
                return null;
            }
            if (!input.matches("-?\\d+")) {
                System.out.print("Ошибка: Введите корректное число. Повторите ввод: ");
                continue;
            }
            int value = Integer.parseInt(input);
            if (value >= min && value <= max) return value;
            System.out.print("Ошибка: Число должно быть в диапазоне " + min + " - " + max + ". Повторите ввод: ");
        }
        return null;
    }

    private static float getNextValidFloat(String prompt, Iterator<String> iterator, Scanner scanner, float min) {
        while (CommandScanner.isInputMode()) {
            String input = iterator.hasNext() ? iterator.next() : scanner.nextLine().trim();
            if (COMMANDS.contains(input.toUpperCase())) {
                System.out.println("Ввод отменен. Выполняется команда: " + input);
                CommandScanner.disableInputMode();
                Manager.executeCommand(input.toUpperCase());
                return Float.MIN_VALUE;
            }
            try {
                float value = Float.parseFloat(input);
                if (value > min) return value;
                System.out.print("Ошибка: Число должно быть больше " + min + ". Повторите ввод: ");
            } catch (NumberFormatException e) {
                System.out.print("Ошибка: Введите число. Повторите ввод: ");
            }
        }
        return Float.MIN_VALUE;
    }

    private static long getNextValidLong(String prompt, Iterator<String> iterator, Scanner scanner, long min) {
        while (CommandScanner.isInputMode()) {
            String input = iterator.hasNext() ? iterator.next() : scanner.nextLine().trim();
            if (COMMANDS.contains(input.toUpperCase())) {
                System.out.println("Ввод отменен. Выполняется команда: " + input);
                CommandScanner.disableInputMode();
                Manager.executeCommand(input.toUpperCase());
                return Long.MIN_VALUE;
            }
            try {
                long value = Long.parseLong(input);
                if (value > min) return value;
                System.out.print("Ошибка: Число должно быть больше " + min + ". Повторите ввод: ");
            } catch (NumberFormatException e) {
                System.out.print("Ошибка: Введите целое число. Повторите ввод: ");
            }
        }
        return Long.MIN_VALUE;
    }

    private static double getNextValidDouble(String prompt, Iterator<String> iterator, Scanner scanner) {
        while (CommandScanner.isInputMode()) {
            String input = iterator.hasNext() ? iterator.next() : scanner.nextLine().trim();
            if (COMMANDS.contains(input.toUpperCase())) {
                System.out.println("Ввод отменен. Выполняется команда: " + input);
                CommandScanner.disableInputMode();
                Manager.executeCommand(input.toUpperCase());
                return Double.MIN_VALUE;
            }
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.print("Ошибка: Введите число. Повторите ввод: ");
            }
        }
        return Double.MIN_VALUE;
    }
    public static int generateUniqueID() {
        return nextId++;
    }
}




