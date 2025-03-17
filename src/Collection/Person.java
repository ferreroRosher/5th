package Collection;

import java.time.LocalDate;
import java.time.ZonedDateTime;

/**
 * Класс Person, который хранится в коллекции.
 * Реализует Comparable для сортировки по ID.
 */
public class Person implements Comparable<Person> {
    private final Integer id;
    private final String name;
    private final Coordinates coordinates;
    private final LocalDate creationDate;
    private final long height;
    private final ZonedDateTime birthday;
    private final String passportID;
    private final Country nationality;
    private final Location location;

    public Person(Integer id, String name, Coordinates coordinates, long height, ZonedDateTime birthday,
                  String passportID, Country nationality, Location location) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = LocalDate.now();
        this.height = height;
        this.birthday = birthday;
        this.passportID = passportID;
        this.nationality = nationality;
        this.location = location;
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
}


