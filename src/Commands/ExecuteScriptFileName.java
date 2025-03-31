package Commands;

import Util.CommandScanner;

import java.io.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ExecuteScriptFileName extends AbstractCommand {

    private static final Set<String> executedScripts = new HashSet<>();

    public ExecuteScriptFileName() {
        super("ExecuteScript", "Считать и исполнить скрипт из указанного файла");
    }

    @Override
    public void execute(String[] args) {
        if (args == null || args.length == 0) {
            System.out.println("Укажите путь к файлу скрипта.");
            return;
        }

        String filePath = args[0];
        File scriptFile = new File(filePath);

        if (!scriptFile.exists() || !scriptFile.canRead()) {
            System.out.println("Файл не найден или недоступен для чтения: " + filePath);
            return;
        }

        try {
            String canonicalPath = scriptFile.getCanonicalPath();
            if (executedScripts.contains(canonicalPath)) {
                System.out.println("Зацикливание, скрипт остановлен");
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
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }
}

