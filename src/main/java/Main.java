import Util.CollectionManager;
import Util.CommandScanner;
import Util.FileManager;

import java.util.LinkedHashMap;

public class Main {
    public static void main(String[] args) {
        /* Scanner scanner = new Scanner(System.in);
        // Создаём объект Scanner
        String name = scanner.nextLine();
        // Читаем строку из ввода
        System.out.println("Hello world! " + name);
         */
        if (args.length > 0) {
            FileManager.setFilePath(args[0]);
            FileManager.loadCollectionFromXml();
        } else {
            System.out.println("Путь к XML-файлу не передан. Коллекция будет создана пустой.");
            CollectionManager.setCollection(new LinkedHashMap<>());
        }

        CommandScanner.startInteractiveMode();
    }
}