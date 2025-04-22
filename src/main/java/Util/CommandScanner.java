package Util;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class CommandScanner {
    private static final Scanner defaultScanner = new Scanner(System.in);
    private static Scanner scanner = defaultScanner;
    private static boolean wordleMode = false;
    private static boolean inputMode = false;
    public static void startInteractiveMode() {
        if (scanner == null) scanner = defaultScanner;
        if (scanner == defaultScanner) {
            System.out.println("Хотите включить режим Wordle для разблокировки скрытой команды? (y/n): ");
            String response = scanner.nextLine().trim().toLowerCase();

            if (response.equals("y")) {
                Manager.enableWordleMode();
                wordleMode = true;
                System.out.println("Режим Wordle активирован! Введите WORDLE, чтобы начать игру.");
            } else {
                Manager.unlockInsert();
            }
        }

        while (true) {
            System.out.print("> ");
            try {
                if (!scanner.hasNextLine()) {
                    System.out.println("\n Завершение: получен EOF (Ctrl+D)");
                    break;
                }

                String commandLine = scanner.nextLine().trim();
                if (commandLine.isEmpty()) continue;

                // Если сейчас режим ввода — команды не обрабатываем
                if (isInputMode()) {
                    continue;
                }

                String[] inputs = commandLine.split("\\s+");
                boolean recognized = false;

                for (String input : inputs) {
                    String upper = cleanCommand(input);

                    if (Manager.getCommands().containsKey(upper)) {

                        if (upper.equals("WORDLE") && wordleMode) {
                            Manager.executeCommand("WORDLE");
                            Manager.removeCommand("WORDLE");
                            wordleMode = false;
                            Manager.unlockInsert();
                            recognized = true;
                            break;
                        }

                        Manager.executeCommand(upper);
                        recognized = true;
                        break;
                    }
                }

                if (!recognized) {
                    System.out.println("Неизвестная команда. Введите 'help' для списка команд.");
                }

            } catch (NoSuchElementException e) {
                System.out.println("\n Завершение по EOF.");
                break;
            }
        }
    }

    private static String cleanCommand(String input) {
        return input.toUpperCase();
    }

    public static String readLine(String prompt) {
        if (!scanner.hasNextLine()) return null;
        System.out.print(prompt + " ");
        return scanner.nextLine()
                .replace("\\n", " ")
                .replace("\\t", " ")
                .replace("\\r", " ")
                .replaceAll("[\";]", " ")
                .replace("KEY", "")
                .replace("_", "")
                .trim();
    }

    public static Scanner getScanner() {
        return scanner;
    }

    public static void setScanner(Scanner newScanner) {
        scanner = newScanner;
    }

    public static boolean isInputMode() {
        return inputMode;
    }

    public static void enableInputMode() {
        inputMode = true;
    }

    public static void disableInputMode() {
        inputMode = false;
    }
}


