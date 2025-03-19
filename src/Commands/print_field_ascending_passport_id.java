package Commands;

import Util.CollectionManager;
import Util.CommandScanner;
import java.util.List;

/**
 * Команда "print_field_ascending_passport_id" - выводит все passportID в порядке возрастания.
 */
public class print_field_ascending_passport_id extends AbstractCommand {
    public print_field_ascending_passport_id() {
        super("print_field_ascending_passport_id", "Вывести значения поля passportID всех элементов в порядке возрастания");
    }

    /**
     * Выполняет команду "print_field_ascending_passport_id".
     * Выводит все passportID в порядке возрастания.
     */
    @Override
    public void execute(String[] args) {
        CommandScanner.disableInputMode();

        List<String> sortedPassportID = CollectionManager.getSortedPassportID();
        if (sortedPassportID.isEmpty()) {
            System.out.println("Коллекция пуста, passportID отсутствуют.");
        } else {
            System.out.println("PassportID в порядке возрастания:");
            sortedPassportID.forEach(System.out::println);
        }
    }
}
