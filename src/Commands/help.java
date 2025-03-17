package Commands;
import Util.Manager;

import java.util.Map;

public class help extends AbstractCommand {
    public help() {
        super("help", "");
    }

    @Override
    /*public void execute(String[] args) {
        CommandScanner.disableInputMode();
        System.out.println("Доступные команды:");
        System.out.println("help - вывести справку");
        System.out.println("info - информация о коллекции");
        System.out.println("show - показать все элементы");
        System.out.println("insert <key> {element} - добавить элемент");
        System.out.println("update <id> {element} - обновить элемент");
        System.out.println("remove_key <key> - удалить элемент по ключу");
        System.out.println("clear - очистить коллекцию");
        System.out.println("save - сохранить коллекцию в файл");
        System.out.println("execute_script <file_name> - выполнить скрипт из файла");
        System.out.println("exit - выйти без сохранения");
    }
} */


        /*
         * Выполняет команду "help".
         * Перебирает все команды, зарегистрированные в Manager, и выводит их название и описание.
         */
        public void execute(String[] args) {
            System.out.println("Список доступных команд:");
            for (Map.Entry<String, Command> entry : Manager.getCommands().entrySet()) {
                Command command = entry.getValue();
                System.out.println("   - " + command.getName() + " --- " + command.getDescription());
            }
        }
    }


