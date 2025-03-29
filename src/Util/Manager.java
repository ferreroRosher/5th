package Util;

import Commands.*;
import java.util.HashMap;
import java.util.Map;

public class Manager {
    private static final Map<String, Command> commands = new HashMap<>();

    static {
        commands.put("HELP", new Help());
        commands.put("INFO", new Info());
        commands.put("EXIT", new Exit());
        commands.put("SHOW", new Show());
        commands.put("REMOVE_KEY", new remove_keynull());
        commands.put("REMOVELOWERKEY", new RemoveLower());
        commands.put("REMOVE_GREATER_KEY", new remove_greater_key());
        commands.put("FILTER_LESS_THAN_NATIONALITY", new filter_less_than_nationality());
        commands.put("PRINT_UNIQUE_NATIONALITY", new print_unique_nationality());
        commands.put("PRINT_FIELD_ASCENDING_PASSPORT_ID", new print_field_ascending_passport_id());
        commands.put("UPDATEID", new UpdateId());
        commands.put("", new WaitForEmptyInput());
        commands.put("CLEAR",new Clear());
        commands.put("EXECUTE SCRIPT",new ExecuteScriptFileName());
    }

    public static void enableWordleMode() {
        commands.put("WORDLE", new Wordle());
    }

    public static void unlockInsert() {
        if (!commands.containsKey("INSERT")) {
            commands.put("INSERT", new Insert());
        }
    }

    public static void removeCommand(String command) {
        commands.remove(command);
    }

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

    public static Map<String, Command> getCommands() {
        return commands;
    }
}

