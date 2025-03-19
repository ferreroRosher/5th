package Commands;

import Util.CollectionManager;
import Util.CommandScanner;
import java.util.Scanner;

/**
 * Команда "remove_greater_key" - удаляет из коллекции все элементы, id которых больше указанного.
 */
public class remove_greater_key extends AbstractCommand {
    public remove_greater_key() {
        super("remove_greater_key", "Удалить из коллекции все элементы, id которых больше указанного");
    }

    /**
     * Выполняет команду "remove_greater_key".
     * Запрашивает id у пользователя, проверяет, и удаляет все элементы с большим id.
     */
    @Override
    public void execute(String[] args) {
        CommandScanner.disableInputMode();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите id, больше которого все элементы будут удалены: ");

        int id;
        try {
            id = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: id должен быть целым числом.");
            return;
        }

        int removedCount = CollectionManager.removeGreaterKey(id);
        if (removedCount > 0) {
            System.out.println("Удалено элементов: " + removedCount);
        } else {
            System.out.println("Нет элементов с id больше " + id);
        }
    }
}
