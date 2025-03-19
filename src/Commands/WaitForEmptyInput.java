package Commands;

import Util.CommandScanner;
import Util.Manager;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class WaitForEmptyInput extends AbstractCommand {
    private int emptyInputCount = 0;
    private static final int MAX_EMPTY_INPUTS = 2;

    public WaitForEmptyInput() {
        super("???", "Отслеживает три подряд пустых ввода и выполняет действие");
    }

    @Override
    public void execute(String[] args) {
        System.out.println("");
        CommandScanner.enableInputMode();
        emptyInputCount = 0;

        while (CommandScanner.isInputMode()) {
            String input = CommandScanner.nextLine().trim();

            if (!input.isEmpty()) {
                System.out.println("");
                CommandScanner.disableInputMode();
                Manager.executeCommand(input); // Выполняем найденную команду
                return;
            }

            emptyInputCount++;
            if (emptyInputCount >= MAX_EMPTY_INPUTS) {
                performEmptyInputAction();
                return;
            }
        }
    }

    private void performEmptyInputAction() {
        System.out.println(
                "SUS" +
                "SUS" +
                "SUS" +
                "SUS" +
                "SUS" +
                "SUS" +
                "SUS" +
                "SUS" +
                "SUS" +
                "SUS" +
                "SUS" +
                "SUS" +
                "SUS");


        File gifFile = new File("C:\\Users\\User\\IdeaProjects\\5th\\src\\among-us-twerk-among-us.gif"); // Укажите путь к вашему файлу

        if (gifFile.exists()) {
            try {
                Desktop.getDesktop().open(gifFile);
            } catch (IOException e) {
                System.out.println("Ошибка при открытии GIF: " + e.getMessage());
            }
        } else {
            System.out.println("Файл GIF не найден: " + gifFile.getAbsolutePath());
        }
    }
}
