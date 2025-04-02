package Util;

import Collection.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

public class FileManager {
    private static String filePath;

    public static void setFilePath(String path) {
        filePath = path;
    }

    public static String getFilePath() {
        return filePath;
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
            System.out.println("Коллекция успешно сохранена в файл: " + filePath);
        }
    }

    public static String convertPersonToXml(Person person, Integer key) {
        StringBuilder xml = new StringBuilder();
        xml.append("  <person key=\"").append(key).append("\">\n");
        xml.append("    <id>").append(person.generateId()).append("</id>\n");
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

    public static void loadCollectionFromXml() {
        if (filePath == null) {
            System.out.println("Файл не указан.");
            return;
        }

        File file = new File(filePath);
        if (!file.exists() || file.length() == 0) {
            System.out.println("Файл не существует или пуст. Коллекция будет инициализирована как пустая.");
            CollectionManager.setCollection(new LinkedHashMap<>());
            return;
        }

        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(bis);

            NodeList persons = doc.getElementsByTagName("person");
            LinkedHashMap<Integer, Person> collection = new LinkedHashMap<>();

            for (int i = 0; i < persons.getLength(); i++) {
                Element personElement = (Element) persons.item(i);
                Integer key = Integer.parseInt(personElement.getAttribute("key"));
                Integer id = Integer.parseInt(getText(personElement, "id"));
                String name = getText(personElement, "name");

                Element coords = (Element) personElement.getElementsByTagName("coordinates").item(0);
                Coordinates coordinates = new Coordinates(
                        Integer.parseInt(getText(coords, "x")),
                        Float.parseFloat(getText(coords, "y"))
                );

                LocalDate creationDate = LocalDate.parse(getText(personElement, "creationDate"));
                long height = Long.parseLong(getText(personElement, "height"));
                ZonedDateTime birthday = ZonedDateTime.parse(getText(personElement, "birthday"));
                String passportID = getText(personElement, "passportID");
                Country nationality = Country.valueOf(getText(personElement, "nationality").toUpperCase());

                Location location = null;
                NodeList locList = personElement.getElementsByTagName("location");
                if (locList.getLength() > 0) {
                    Element loc = (Element) locList.item(0);
                    location = new Location(
                            Double.parseDouble(getText(loc, "x")),
                            Double.parseDouble(getText(loc, "y")),
                            Integer.parseInt(getText(loc, "z")),
                            getText(loc, "name")
                    );
                }

                Person p = new Person(id, name, coordinates, height, birthday, passportID, nationality, location);
                collection.put(key, p);
            }

            CollectionManager.setCollection(collection);
            System.out.println("Коллекция загружена из файла.");

        } catch (Exception e) {
            System.out.println("Ошибка при загрузке: " + e.getMessage());
            CollectionManager.setCollection(new LinkedHashMap<>());
        }
    }

    private static String getText(Element parent, String tag) {
        return parent.getElementsByTagName(tag).item(0).getTextContent();
    }
}
