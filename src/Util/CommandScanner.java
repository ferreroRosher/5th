package Util;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class CommandScanner {
    private static Scanner scanner = new Scanner(System.in);
    private static boolean insertUnlocked = false;
    private static boolean wordleMode = false;

    public static void startInteractiveMode() {
        System.out.println("Хотите включить режим Wordle для разблокировки скрытой команды? (y/n): ");
        String response = scanner.nextLine().trim().toLowerCase();

        if (response.equals("y")) {
            Manager.enableWordleMode();
            wordleMode = true;
            System.out.println("Режим Wordle активирован! Введите WORDLE, чтобы начать игру.");
        } else {
            unlockInsert();
        }

        while (true) {
            System.out.print("> ");
            try {
                String commandLine = readLine();
                if (commandLine == null) continue;

                String[] inputs = commandLine.trim().split("\\s+");
                for (String input : inputs) {
                    String upper = input.toUpperCase();

                    if (Manager.getCommands().containsKey(upper)) {
                        if (upper.equals("INSERT") && !insertUnlocked) {
                            return;
                        }

                        if (upper.equals("WORDLE") && wordleMode) {
                            Manager.executeCommand("WORDLE");
                            Manager.removeCommand("WORDLE");
                            wordleMode = false;
                            unlockInsert();
                            break;
                        }

                        Manager.executeCommand(upper);
                        break;
                    }
                }

            } catch (NoSuchElementException e) {
                System.out.println();
                break;
            }
        }
    }

    public static String readLine() {
        if (!scanner.hasNextLine()) return null;
        return scanner.nextLine()
                .replace("\\n", " ")
                .replace("\\t", " ")
                .replace("\\r", " ")
                .replaceAll("[\";]", " ")
                .trim();
    }

    public static Scanner getScanner() {
        return scanner;
    }

    public static void setScanner(Scanner newScanner) {
        scanner = newScanner;
    }

    public static void unlockInsert() {
        insertUnlocked = true;
    }

    private static boolean inputMode = false;

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



