package Commands;

import Collection.Person;
import Util.CollectionManager;
import Util.CommandScanner;

public class show extends AbstractCommand {
    public show() {
        super("show", "");
    }

    @Override
    public void execute(String[] args) {
        CommandScanner.disableInputMode();
        if (CollectionManager.isEmpty()) {
            System.out.println("Коллекция пуста.");
        } else {
            CollectionManager.getCollection().forEach((key, person) ->
                    System.out.println(key + " -> " + person)
            );
        }
    }
}
