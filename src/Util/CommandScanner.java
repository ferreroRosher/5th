package Util;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class CommandScanner {
    private static boolean isInputMode = false; // Флаг режима ввода
    private static final Scanner scanner = new Scanner(System.in);

    public static void startInteractiveMode() {
        while (true) {
            System.out.print("> ");
            try {
                if (!scanner.hasNextLine()) {
                    System.out.println("Обнаружено завершение ввода (Ctrl+D)");
                    return; // Завершаем метод, чтобы выйти из режима ожидания, но не завершать программу
                }
                String commandLine = scanner.nextLine().trim().toUpperCase();
                Manager.executeCommand(commandLine);
            } catch (NoSuchElementException e) {
                System.out.println("Обнаружено завершение ввода (Ctrl+D)");
                return;
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
        try {
            return scanner.nextLine();
        } catch (NoSuchElementException e) {
            System.out.println();
            disableInputMode();
            return ""; // Возвращаем пустую строку, чтобы вызывающий код мог обработать это
        }
    }
}




