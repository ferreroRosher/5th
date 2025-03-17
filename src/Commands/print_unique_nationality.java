package Commands;

import Util.CollectionManager;
import Collection.Country;
import Util.CommandScanner;
import java.util.Set;

/**
 * Команда "print_unique_nationality" - выводит уникальные значения nationality в коллекции.
 */
public class print_unique_nationality extends AbstractCommand {
    public print_unique_nationality() {
        super("print_unique_nationality", "Вывести уникальные значения nationality всех элементов в коллекции");
    }

    /**
     * Выполняет команду "print_unique_nationality".
     * Выводит уникальные значения nationality в коллекции.
     */
    @Override
    public void execute(String[] args) {
        CommandScanner.disableInputMode();

        Set<Country> uniqueNationalities = CollectionManager.getUniqueNationalities();
        if (uniqueNationalities.isEmpty()) {
            System.out.println("⚠️ Коллекция пуста, уникальные nationality отсутствуют.");
        } else {
            System.out.println("✅ Уникальные nationality:");
            uniqueNationalities.forEach(System.out::println);
        }
    }
}

