package Util;

import Collection.Person;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Класс для управления коллекцией объектов Person.
 * Хранит элементы в LinkedHashMap, сохраняя порядок добавления.
 */
public class CollectionManager {
    // Коллекция для хранения объектов Person, ключ - уникальный ID
    private static final Map<Integer, Person> personCollection = new LinkedHashMap<>();
    private static int nextId = 1; // Уникальный идентификатор для новых элементов

    // Дата инициализации коллекции
    private static final LocalDateTime initializationDate = LocalDateTime.now();

    /**
     * Добавляет нового человека в коллекцию с автоматическим ID.
     * @param person объект Person для добавления
     */
    public static void addPerson(Person person) {
        personCollection.put(nextId++, person);
    }
    /**
     * Удаляет элемент по ключу.
     * @param key ID элемента
     */
    public static boolean removeByKey(int key) {
        if (personCollection.containsKey(key)) {
            personCollection.remove(key);
            return true; // Успешное удаление
        }
        return false; // Такого ключа нет
    }

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
}

