import Util.CommandScanner;
import Util.FileManager;
public class Main {
    public static void main(String[] args) {
        /* Scanner scanner = new Scanner(System.in);
        // Создаём объект Scanner
        String name = scanner.nextLine();
        // Читаем строку из ввода
        System.out.println("Hello world! " + name);
         */
        System.out.println("Введите 'help' для списка команд");
        CommandScanner.startInteractiveMode();
        if (args.length > 0) {
            FileManager.setFilePath(args[0]);
            FileManager.loadCollectionFromXml();
        } else {
            System.out.println("Укажите путь к XML-файлу как аргумент командной строки.");
        }
    }
}