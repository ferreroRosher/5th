package Collection;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

//public class Person {
    /*private Integer id;
    //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name;
    //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates;
    //Поле не может быть null
    private java.time.LocalDate creationDate;
    //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private long height;
    //Значение поля должно быть больше 0
    private java.time.ZonedDateTime birthday;
    //Поле не может быть null
    private String passportID;
    //Длина строки не должна быть больше 33, Длина строки должна быть не меньше 6, Поле не может быть null
    private Country nationality;
    //Поле не может быть null
    private Location location;
    //Поле может быть null
     */
//}
// Класс Person с проверкой значений

public class Person {
    private static final Set<Integer> generatedIds = new HashSet<>(); // Для хранения уникальных id
    private static int nextId = 1; // Начальное значение id

    private final Integer id; // Генерируется автоматически
    private final String name; // Не может быть null или пустым
    private final Coordinates coordinates; // Не может быть null
    private final LocalDate creationDate; // Генерируется автоматически
    private final long height; // Должно быть > 0
    private final ZonedDateTime birthday; // Не может быть null
    private final String passportID; // 6-33 символов, не null
    private final Country nationality; // Не может быть null
    private final Location location; // Может быть null

    public Person(String name, Coordinates coordinates, long height, ZonedDateTime birthday,
                  String passportID, Country nationality, Location location) {
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Имя не может быть пустым.");
        if (coordinates == null) throw new IllegalArgumentException("Координаты не могут быть null.");
        if (height <= 0) throw new IllegalArgumentException("Рост должен быть больше 0.");
        if (birthday == null) throw new IllegalArgumentException("Дата рождения не может быть null.");
        if (passportID == null || passportID.length() < 6 || passportID.length() > 33)
            throw new IllegalArgumentException("Длина passportID должна быть от 6 до 33 символов.");
        if (nationality == null) throw new IllegalArgumentException("Национальность не может быть null.");

        this.id = generateUniqueId();
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = LocalDate.now(); // Генерируется автоматически
        this.height = height;
        this.birthday = birthday;
        this.passportID = passportID;
        this.nationality = nationality;
        this.location = location;
    }

    private static Integer generateUniqueId() {
        while (generatedIds.contains(nextId)) {
            nextId++;
        }
        generatedIds.add(nextId);
        return nextId;
    }

    // Геттеры
    public Integer getId() { return id; }
    public String getName() { return name; }
    public Coordinates getCoordinates() { return coordinates; }
    public LocalDate getCreationDate() { return creationDate; }
    public long getHeight() { return height; }
    public ZonedDateTime getBirthday() { return birthday; }
    public String getPassportID() { return passportID; }
    public Country getNationality() { return nationality; }
    public Location getLocation() { return location; }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", height=" + height +
                ", birthday=" + birthday +
                ", passportID='" + passportID + '\'' +
                ", nationality=" + nationality +
                ", location=" + location +
                '}';
    }
}


