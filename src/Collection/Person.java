package Collection;
import java.time.ZonedDateTime;

/**
 * Класс Person, который хранится в коллекции.
 * Реализует Comparable для сортировки по ID.
 */
public class Person implements Comparable<Person> {
    private final Integer id;
    private final Country nationality;
    private final String passportID;

    public Person(Integer id, String name, Coordinates coordinates, long height, ZonedDateTime birthday,
                  String passportID, Country nationality, Location location) {
        this.id = id;
        this.nationality = nationality;
        this.passportID = passportID;
    }

    /**
     * Реализация compareTo() для сортировки Person по ID.
     */
    @Override
    public int compareTo(Person other) {
        return Integer.compare(this.id, other.id);
    }

    public Integer getId() {
        return id;
    }

    /**
     * Возвращает национальность Person.
     * @return nationality
     */
    public Country getNationality() {
        return nationality;
    }
    public String getPassportID() {
        return passportID;
    } //вернуть значение паспорта для сортировки (сраная сортировка)
}



