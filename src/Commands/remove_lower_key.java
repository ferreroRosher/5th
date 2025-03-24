package Commands;

import Util.CollectionManager;
import Util.CreatePerson;
import Util.CommandScanner;
import Collection.Person;
import java.util.Scanner;

/**
 * Команда "remove_lower" - удаляет из коллекции все элементы, меньшие, чем заданный.
 */
public class remove_lower_key extends AbstractCommand {
    public remove_lower_key() {
        super("remove_lower", "Удалить из коллекции все элементы, меньшие, чем заданный");
    }
    //Запрашивает объект Person у пользователя, проверяет, и удаляет все меньшие элементы.
    @Override
    public void execute(String[] args) {
        CommandScanner.disableInputMode();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите объект Person, относительно которого будут удалены элементы:");
        Person person = CreatePerson.createPersonFromInput(scanner);

        int removedCount = CollectionManager.removeLower(person);
        if (removedCount > 0) {
            System.out.println("Удалено элементов: " + removedCount);
        } else {
            System.out.println("Нет элементов, меньших чем заданный.");
        }
    }
}
