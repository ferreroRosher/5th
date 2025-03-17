package Util;

import Commands.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс Manager управляет выполнением команд.
 */
public class Manager {
    private static final Map<String, Command> commands = new HashMap<>();

    static {
        commands.put("HELP", new help());
        commands.put("INFO", new info());
        commands.put("EXIT", new exit());
        commands.put("INSERT", new insertnull());
        commands.put("SHOW", new show());
        commands.put("REMOVE_KEY", new remove_keynull());
        commands.put("REMOVE_LOWER_KEY", new remove_lower_key());
        commands.put("FILTER_LESS_THAN_NATIONALITY", new filter_less_than_nationality());
        commands.put("PRINT_UNIQUE_NATIONALITY", new print_unique_nationality());
    }

    /**
     * Выполняет команду по ее названию.
     * @param commandLine строка с названием команды
     */
    public static void executeCommand(String commandLine) {
        if (!commandLine.equalsIgnoreCase("INSERT")) {
            CommandScanner.disableInputMode(); // Отключаем ввод данных
        }

        Command command = commands.get(commandLine.toUpperCase());
        if (command != null) {
            command.execute(new String[]{});
        } else if (!CommandScanner.isInputMode()) {
            System.out.println("Неизвестная команда. Введите 'help' для списка команд.");
        }
    }
    /**
     * Возвращает список всех зарегистрированных команд.
     * @return Map с командами
     */
    public static Map<String, Command> getCommands() {
        return commands;
    }
}


