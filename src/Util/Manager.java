package Util;

import Commands.Command;
import Commands.exit;
import Commands.help;
import Commands.info;
import Commands.insertnull;
import Commands.show;

import java.util.HashMap;
import java.util.Map;

public class Manager {
    private static final Map<String, Command> commands = new HashMap<>();

    static {
        commands.put("HELP", new help());
        commands.put("INFO", new info());
        commands.put("EXIT", new exit());
        commands.put("INSERT", new insertnull());
        commands.put("SHOW", new show());
    }

    public static void executeCommand(String commandLine) {
        if (!commandLine.equalsIgnoreCase("INSERT")) {
            CommandScanner.disableInputMode(); // ✅ Любая команда выключает `isInputMode`
        }

        Command command = commands.get(commandLine.toUpperCase());
        if (command != null) {
            command.execute(new String[]{});
        } else if (!CommandScanner.isInputMode()) {
            System.out.println("Неизвестная команда. Введите 'help' для списка команд.");
        }
    }
}

