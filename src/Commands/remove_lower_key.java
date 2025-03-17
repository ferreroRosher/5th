package Commands;

import Util.CollectionManager;
import Util.CommandScanner;
import java.util.Scanner;

/**
 * Команда "remove_lower_key" - удаляет из коллекции все элементы, id которых меньше заданного.
 */
public class remove_lower_key extends AbstractCommand {
    public remove_lower_key() {
        super("remove_lower_key", "Удалить из коллекции все элементы, id которых меньше указанного");
    }

    /**
     * Выполняет команду "remove_lower_key".
     * Запрашивает id у пользователя, проверяет, и удаляет все элементы с меньшим id.
     */
    @Override
    public void execute(String[] args) {
        CommandScanner.disableInputMode();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите id, меньше которого все элементы будут удалены: ");

        int id;
        try {
            id = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: id должен быть целым числом.");
            return;
        }

        int removedCount = CollectionManager.removeLowerKey(id);
        if (removedCount > 0) {
            System.out.println("✅ Удалено элементов: " + removedCount);
        } else {
            System.out.println("⚠️ Нет элементов с id меньше " + id);
        }
    }
}
