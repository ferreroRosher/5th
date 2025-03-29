package Commands;

import Collection.Person;
import Util.CollectionManager;
import Util.CommandScanner;
import Util.CreatePerson;

public class Insert extends AbstractCommand {

    public Insert() {
        super("insert", "Добавить новый элемент в коллекцию");
    }

    @Override
    public void execute(String[] args) {
        CommandScanner.disableInputMode(); // на всякий случай

        Person person = CreatePerson.createFromInput();
        CollectionManager.addPerson(person);
        System.out.println("Person успешно добавлен в коллекцию. ");
    }
}
