package Commands;

import Util.CollectionManager;
import Util.CommandScanner;
import java.util.Scanner;

/**
 * Команда "remove_key" - удаляет элемент из коллекции по его id.
 */
public class remove_keynull extends AbstractCommand {
    public remove_keynull() {
        super("remove_key", "Удалить элемент из коллекции по ключу (id)");
    }

    /**
     * Выполняет команду "remove_key".
     * Запрашивает id у пользователя, проверяет, есть ли элемент, и удаляет его.
     */
    @Override
    public void execute(String[] args) {
        CommandScanner.disableInputMode();

        Scanner scanner = new Scanner(System.in); //Используем локальный Scanner
        System.out.print("Введите id элемента, который хотите удалить: ");

        int id;
        try {
            id = Integer.parseInt(scanner.nextLine().trim()); //Читаем ввод через scanner
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: id должен быть целым числом.");
            return;
        }

        if (CollectionManager.removeByKey(id)) {
            System.out.println("Person с ID " + id + " успешно удален.");
        } else {
            System.out.println("Person с ID " + id + " не найден.");
        }
    }
}
