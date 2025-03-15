package Commands;

import Collection.Person;
import Util.CommandScanner;
import Util.CollectionManager;
import Util.CreatePerson;

import java.util.Scanner;

public class insertnull extends AbstractCommand {
    public insertnull() {
        super("insert", "Добавить элемент в коллекцию");
    }

    @Override
    public void execute(String[] args) {
        System.out.println("");
        CommandScanner.enableInputMode(); // Включаем режим ввода

        Scanner scanner = new Scanner(System.in);
        Person newPerson = CreatePerson.createPersonFromInput(scanner);

        if (newPerson != null) {
            CollectionManager.addPerson(newPerson);
            System.out.println("Человек успешно добавлен: " + newPerson);
        } else {
            System.out.println("Ввод данных отменен.");
        }

        CommandScanner.disableInputMode(); //  `insert` сам выключает `isInputMode`
    }
}


