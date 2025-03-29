package Commands;

import Collection.Person;
import Util.CollectionManager;
import Util.CommandScanner;
import Util.CreatePerson;

import java.util.LinkedHashMap;

public class UpdateId extends AbstractCommand {

    public UpdateId() {
        super("update_id", "Обновить значение элемента коллекции, id которого равен заданному");
    }

    @Override
    public void execute(String[] args) {
        if (args == null || args.length == 0) {
            System.out.println("Пожалуйста, укажите ID для обновления.");
            return;
        }

        try {
            int id = Integer.parseInt(args[0]);
            LinkedHashMap<Integer, Person> collection = (LinkedHashMap<Integer, Person>) CollectionManager.getCollection();

            Person toUpdate = collection.get(id);
            if (toUpdate == null) {
                System.out.println("Элемент с таким ID не найден.");
                return;
            }

            CommandScanner.enableInputMode();
            Person updatedPerson = CreatePerson.createFromInput();
            CommandScanner.disableInputMode();

            // заменяем по ключу
            collection.put(id, updatedPerson);
            System.out.println("Элемент успешно обновлён.");

        } catch (NumberFormatException e) {
            System.out.println("Неверный формат ID. Укажите целое число.");
        }
    }
}
