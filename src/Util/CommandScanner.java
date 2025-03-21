package Util;

import java.util.Scanner;
import java.util.NoSuchElementException;

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
                if (commandLine == null) {
                    continue;
                }
                Manager.executeCommand(commandLine);

                // Если INSERT разблокирован, удаляем WORDLE
                if (Manager.getCommands().containsKey("INSERT")) {
                    Manager.removeCommand("WORDLE");
                }
            } catch (NoSuchElementException e) {
                System.out.println("");
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
                System.out.println("");
                disableInputMode();
            }
            return scanner.nextLine();
        } catch (NoSuchElementException e) {
            System.out.println("");
            disableInputMode();
            scanner.nextLine();
            return null;
        }
    }
}


