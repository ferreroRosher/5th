package Util;

import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.Iterator;
import java.util.Arrays;

public class CommandScanner {
    private static boolean isInputMode = false;
    private static final Scanner scanner = new Scanner(System.in);

    public static void startInteractiveMode() {
        System.out.println("Хотите включить режим Wordle для разблокировки скрытой команды? (y/n): ");
        String response = scanner.nextLine().trim().toLowerCase();
        if (response.equals("y")) {
            Manager.enableWordleMode();
            System.out.println("Режим Wordle активирован! Введите WORDLE, чтобы начать игру.");
        } else {
            Manager.unlockInsert(); // Разблокируем insert сразу, если Wordle не активирован
        }

        while (true) {
            System.out.print("> ");
            try {
                String commandLine = readLine();
                if (commandLine == null) continue;
                Manager.executeCommand(commandLine);

                if (Manager.getCommands().containsKey("INSERT")) {
                    Manager.removeCommand("WORDLE");
                }
            } catch (NoSuchElementException e) {
                System.out.println();
                break;
            }
        }
    }

    public static void enableInputMode() {
        isInputMode = true;
    }

    public static void disableInputMode() {
        isInputMode = false;
    }

    public static boolean isInputMode() {
        return isInputMode;
    }

    public static String nextLine() {
        return readLine();
    }

    private static String readLine() {
        try {
            if (!scanner.hasNextLine()) {
                System.out.println();
                disableInputMode();
            }
            return scanner.nextLine();
        } catch (NoSuchElementException e) {
            System.out.println();
            disableInputMode();
            return null;
        }
    }

    // ======= ВАЛИДИРОВАННЫЙ ВВОД ДАННЫХ =======

    public static String nextValidString(String prompt, Iterator<String> iterator, java.util.function.Predicate<String> validator, String errorMessage) {
        while (true) {
            System.out.print(prompt);
            String input = iterator.hasNext() ? iterator.next() : scanner.nextLine().trim();
            if (input == null || input.isEmpty()) continue;
            if (validator.test(input)) return input;
            System.out.println("Ошибка: " + errorMessage);
        }
    }

    public static Integer nextValidInt(String prompt, Iterator<String> iterator, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String input = iterator.hasNext() ? iterator.next() : scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(input);
                if (value >= min && value <= max) return value;
                System.out.println("Ошибка: значение должно быть от " + min + " до " + max);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите целое число.");
            }
        }
    }

    public static Long nextValidLong(String prompt, Iterator<String> iterator, long min) {
        while (true) {
            System.out.print(prompt);
            String input = iterator.hasNext() ? iterator.next() : scanner.nextLine().trim();
            try {
                long value = Long.parseLong(input);
                if (value >= min) return value;
                System.out.println("Ошибка: число должно быть ≥ " + min);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число.");
            }
        }
    }

    public static Float nextValidFloat(String prompt, Iterator<String> iterator, float min) {
        while (true) {
            System.out.print(prompt);
            String input = iterator.hasNext() ? iterator.next() : scanner.nextLine().trim();
            try {
                float value = Float.parseFloat(input);
                if (value > min) return value;
                System.out.println("Ошибка: значение должно быть больше " + min);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число.");
            }
        }
    }

    public static Double nextValidDouble(String prompt, Iterator<String> iterator) {
        while (true) {
            System.out.print(prompt);
            String input = iterator.hasNext() ? iterator.next() : scanner.nextLine().trim();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите корректное число.");
            }
        }
    }

    public static <E extends Enum<E>> E nextValidEnum(String prompt, Iterator<String> iterator, Class<E> enumClass) {
        while (true) {
            System.out.print(prompt);
            String input = iterator.hasNext() ? iterator.next() : scanner.nextLine().trim();
            try {
                return Enum.valueOf(enumClass, input.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: допустимые значения: " + Arrays.toString(enumClass.getEnumConstants()));
            }
        }
    }
}



