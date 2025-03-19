package Util;

import Collection.Country;
import Collection.Person;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


public class CollectionManager {
    // Коллекция для хранения объектов Person, ключ - уникальный ID
    private static final Map<Integer, Person> personCollection = new LinkedHashMap<>();
    private static int nextId = 1;
    // Уникальный идентификатор для новых элементов
    public static List<Person> filterLessThanNationality(Country nationality) {
        return personCollection.values().stream()
                .filter(person -> person.getNationality().isLessThan(nationality)) //Используем метод из Country
                .collect(Collectors.toList());
    }
    public static List<String> getSortedPassportID() {
        return personCollection.values().stream()
                .map(Person::getPassportID) // Извлекаем passportID
                .sorted() // Сортируем по алфавиту
                .collect(Collectors.toList());
    }

    // Дата инициализации коллекции
    private static final LocalDateTime initializationDate = LocalDateTime.now();

    /**
     * Добавляет нового человека в коллекцию с автоматическим ID.
     */
    public static void addPerson(Person person) {
        personCollection.put(nextId++, person);
    }
    /*
     * Удаляет элемент по ключу.
     */
    public static boolean removeByKey(int key) {
        if (personCollection.containsKey(key)) {
            personCollection.remove(key);
            return true;
        }
        return false;
    }
    /**
     * Удаляет все элементы, id которых меньше указанного.
     */
    public static int removeLower(Person person) {
        List<Integer> toRemove = personCollection.values().stream()
                .filter(p -> p.compareTo(person) < 0)
                .map(Person::getId)
                .toList();

        toRemove.forEach(personCollection::remove);
        return toRemove.size();
    }
    public static int removeGreaterKey(int key) {
        List<Integer> toRemove = personCollection.keySet().stream()
                .filter(id -> id > key)
                .toList();

        toRemove.forEach(personCollection::remove);
        return toRemove.size();
    }


    /* public static List<Person> getSortedCollection() {
        return personCollection.values().stream()
                .sorted()
                .collect(Collectors.toList());
    } Cортировать человеков, проверить, что исполняется без этого*/

    /**
     * Возвращает всю коллекцию объектов Person.
     * @return Map с элементами коллекции
     */
    public static Map<Integer, Person> getCollection() {
        return personCollection;
    }

    /**
     * Проверяет, пуста ли коллекция.
     * @return true, если коллекция пуста, иначе false
     */
    public static boolean isEmpty() {
        return personCollection.isEmpty();
    }

    /**
     * Возвращает количество элементов в коллекции.
     * @return число элементов в коллекции
     */
    public static int getSize() {
        return personCollection.size();
    }

    /**
     * Возвращает тип коллекции (LinkedHashMap).
     * @return название класса коллекции
     */
    public static String getCollectionType() {
        return personCollection.getClass().getSimpleName();
    }

    /**
     * Возвращает дату инициализации коллекции.
     * @return дата инициализации коллекции
     */
    public static LocalDateTime getInitializationDate() {
        return initializationDate;
    }
    public static Set<Country> getUniqueNationalities() {
        return personCollection.values().stream()
                .map(Person::getNationality) //Извлекаем nationality из Person
                .collect(Collectors.toSet()); //Собираем в Set (уникальные значения)
    }
}

