package Commands;

import Util.CommandScanner;
import Util.Manager;

import java.io.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Команда execute_script.
 * Считывает и исполняет команды из указанного файла.
 */
public class ExecuteScriptFileName extends AbstractCommand {

    private static final Set<String> executedScripts = new HashSet<>();

    public ExecuteScriptFileName() {
        super("execute_script", "Считывает и исполняет команды из указанного файла.");
    }

    @Override
    public void execute(String[] args) {
        if (args == null || args.length == 0) {
            System.out.println("Укажите путь к файлу скрипта.");
            return;
        }

        String filePath = args[0];
        File scriptFile = new File(filePath);

        // Выводим реальную рабочую директорию
        System.out.println("Рабочая директория: " + System.getProperty("user.dir"));

        if (!scriptFile.isAbsolute()) {
            scriptFile = new File(System.getProperty("user.dir"), filePath);
        }

        // Выводим путь, куда реально смотрим
        System.out.println("Ищу файл по пути: " + scriptFile.getAbsolutePath());

        if (!scriptFile.exists() || !scriptFile.isFile()) {
            System.out.println("Файл не найден или недоступен для чтения: " + scriptFile.getAbsolutePath());
            return;
        }

        try {
            String canonicalPath = scriptFile.getCanonicalPath();
            if (executedScripts.contains(canonicalPath)) {
                System.out.println("Зацикливание скриптов обнаружено. Скрипт уже выполняется: " + canonicalPath);
                return;
            }

            executedScripts.add(canonicalPath);

            try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(scriptFile));
                 Scanner fileScanner = new Scanner(new InputStreamReader(bis))) {

                Scanner originalScanner = CommandScanner.getScanner();
                CommandScanner.setScanner(fileScanner);

                CommandScanner.startInteractiveMode();

                CommandScanner.setScanner(originalScanner);
            }

            executedScripts.remove(canonicalPath);

        } catch (IOException e) {
            System.out.println("Ошибка при выполнении скрипта: " + e.getMessage());
        }
    }
}

