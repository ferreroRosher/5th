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

    public static void addPerson(Person person) {
        personCollection.put(nextId++, person);
    }

    public static boolean removeByKey(int key) {
        if (personCollection.containsKey(key)) {
            personCollection.remove(key);
            return true;
        }
        return false;
    }
    public static int removeLower(Person person) {
        List<Person> toRemove = personCollection.values().stream()
                .filter(p -> p.compareTo(person) < 0)
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

    public static Map<Integer, Person> getCollection() {
        return personCollection;
    }

    public static boolean isEmpty() {
        return personCollection.isEmpty();
    }


    public static int getSize() {
        return personCollection.size();
    }


    public static String getCollectionType() {
        return personCollection.getClass().getSimpleName();
    }

    public static void clear() {
        personCollection.clear();
        //Должен очищать коллекцию
    }


    public static LocalDateTime getInitializationDate() {
        return initializationDate;
    }

    public static Set<Country> getUniqueNationalities() {
        return personCollection.values().stream()
                .map(Person::getNationality) //Извлекаем nationality из Person
                .collect(Collectors.toSet()); //Собираем в Set (уникальные значения)
    }
}

