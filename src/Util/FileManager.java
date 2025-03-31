package Util;

import Collection.Person;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class FileManager {
    private static String filePath;

    public static void setFilePath(String path) {
        filePath = path;
    }

    public static void saveCollectionToXml() throws IOException {
        if (filePath == null || filePath.isEmpty()) {
            throw new IOException("Путь к файлу не задан.");
        }

        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(filePath), StandardCharsets.UTF_8)) {
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            writer.write("<collection>\n");

            for (Map.Entry<Integer, Person> entry : CollectionManager.getCollection().entrySet()) {
                writer.write(convertPersonToXml(entry.getValue(), entry.getKey()) + "\n");
            }

            writer.write("</collection>\n");
            System.out.println(" Коллекция сохранена в файл: " + filePath);
        }
    }

    public static String convertPersonToXml(Person person, Integer key) {
        StringBuilder xml = new StringBuilder();
        xml.append("  <person key=\"").append(key).append("\">\n");
        xml.append("    <id>").append(Person.generateId()).append("</id>\n");
        xml.append("    <name>").append(person.getName()).append("</name>\n");
        xml.append("    <coordinates>\n");
        xml.append("      <x>").append(person.getCoordinates().getX()).append("</x>\n");
        xml.append("      <y>").append(person.getCoordinates().getY()).append("</y>\n");
        xml.append("    </coordinates>\n");
        xml.append("    <creationDate>").append(person.getCreationDate()).append("</creationDate>\n");
        xml.append("    <height>").append(person.getHeight()).append("</height>\n");
        xml.append("    <birthday>").append(person.getBirthday()).append("</birthday>\n");
        xml.append("    <passportID>").append(person.getPassportID()).append("</passportID>\n");
        xml.append("    <nationality>").append(person.getNationality()).append("</nationality>\n");

        if (person.getLocation() != null) {
            xml.append("    <location>\n");
            xml.append("      <x>").append(person.getLocation().getX()).append("</x>\n");
            xml.append("      <y>").append(person.getLocation().getY()).append("</y>\n");
            xml.append("      <z>").append(person.getLocation().getZ()).append("</z>\n");
            xml.append("      <name>").append(person.getLocation().getName()).append("</name>\n");
            xml.append("    </location>\n");
        }

        xml.append("  </person>");
        return xml.toString();
    }
}