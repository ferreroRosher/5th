package Util;

import java.util.Scanner;

public class CommandScanner {
    private static boolean isInputMode = false; // Флаг режима ввода
    private static Scanner scanner = new Scanner(System.in);

    public static void startInteractiveMode() {
        while (true) {
            System.out.print("> ");
            String commandLine = scanner.nextLine().trim().toUpperCase();
            Manager.executeCommand(commandLine);
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
}



