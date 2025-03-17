package Collection;

import java.time.LocalDate;
import java.time.ZonedDateTime;

/**
 * Класс Person, который хранится в коллекции.
 * Реализует Comparable для сортировки по ID.
 */
public class Person implements Comparable<Person> {
    private final Integer id;
    private final Country nationality;

    public Person(Integer id, String name, Coordinates coordinates, long height, ZonedDateTime birthday,
                  String passportID, Country nationality, Location location) {
        this.id = id;
        this.nationality = nationality;
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
}



