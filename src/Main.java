import Util.CommandScanner;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        /*Scanner scanner = new Scanner(System.in); // Создаём объект Scanner
        String name = scanner.nextLine(); // Читаем строку из ввода
        System.out.println("Hello world! " + name);
         */
        // Главный класс - точка входа в программу
        System.out.println("Введите 'help' для списка команд");
        CommandScanner.startInteractiveMode();// Запуск интерактивного режима
    }
}