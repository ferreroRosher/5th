package Util;

import Collection.Person;

import java.util.LinkedHashMap;
import java.util.Map;

public class CollectionManager {
    private static final Map<Integer, Person> personCollection = new LinkedHashMap<>();
    private static int nextId = 1;

    public static void addPerson(Person person) {
        personCollection.put(nextId++, person);
    }

    public static Map<Integer, Person> getCollection() {
        return personCollection;
    }

    public static boolean isEmpty() {
        return personCollection.isEmpty();
    }
}
