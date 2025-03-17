package Commands;

import Util.CollectionManager;
import Collection.Country;
import Collection.Person;
import Util.CommandScanner;
import java.util.List;
import java.util.Scanner;

/**
 * Команда "filter_less_than_nationality" - выводит всех Person, у которых nationality меньше указанного.
 */
public class filter_less_than_nationality extends AbstractCommand {
    public filter_less_than_nationality() {
        super("filter_less_than_nationality", "Вывести элементы, значение nationality которых меньше заданного");
    }

    /**
     * Выполняет команду "filter_less_than_nationality".
     * Запрашивает nationality у пользователя и выводит отфильтрованные элементы.
     */
    @Override
    public void execute(String[] args) {
        CommandScanner.disableInputMode();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите национальность (GERMANY, FRANCE, JAPAN): ");

        Country nationality;
        try {
            nationality = Country.valueOf(scanner.nextLine().trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: некорректная национальность.");
            return;
        }

        List<Person> filteredPersons = CollectionManager.filterLessThanNationality(nationality);
        if (filteredPersons.isEmpty()) {
            System.out.println("Нет элементов с nationality меньше " + nationality);
        } else {
            System.out.println("Найденные Person:");
            filteredPersons.forEach(System.out::println);
        }
    }
}
