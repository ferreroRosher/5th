package Util;

import Collection.*;

import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.StringTokenizer;
import java.util.function.DoublePredicate;
import java.util.function.LongPredicate;

import static Collection.Person.generateId;


public class CreatePerson {


    public static Person createFromInput() {
        StringTokenizer tokens = null;

        System.out.println("Введите данные для создания нового Person:");
        String line = CommandScanner.readLine("Введите ключ:");
        assert line != null;
        tokens = new StringTokenizer(line);

        String name = getNextValidString("Имя", tokens);
        Coordinates coordinates = new Coordinates(getNextValidInt("Координата X (целое число ≤ 369)", tokens, x -> x <= 369),
                getNextValidFloat(tokens, y -> y > -983));

        long height = getNextValidLong(tokens, h -> h > 0);
        ZonedDateTime birthday = getNextValidZonedDateTime(tokens);
        String passportID = getNextValidString("Паспорт ID (от 6 до 33 символов)", tokens, s -> s.length() >= 6 && s.length() <= 33);
        Country nationality = getNextValidCountry(tokens);
        Location location = new Location(
                getNextValidDouble("Локация X (вещественное число)", tokens),
                getNextValidDouble("Локация Y (вещественное число)", tokens),
                getNextValidInt("Локация Z (целое число)", tokens, z -> true),
                getNextValidString("Название локации", tokens)
        );

        return new Person(generateId(), name, coordinates, height, birthday, passportID, nationality, location);
    }

    private static String getNextValidString(String prompt, StringTokenizer tokens) {
        return getNextValidString(prompt, tokens, s -> !s.isEmpty());
    }

    private static String getNextValidString(String prompt, StringTokenizer tokens, java.util.function.Predicate<String> validator) {
        while (true) {
            String input = tokens != null && tokens.hasMoreTokens() ? tokens.nextToken() : CommandScanner.readLine("Введите ключ:");
            if (input != null && validator.test(input)) return input;
            System.out.println("Некорректный ввод. " + prompt + ":");
        }
    }

    private static int getNextValidInt(String prompt, StringTokenizer tokens, java.util.function.IntPredicate validator) {
        while (true) {
            try {
                String token = tokens != null && tokens.hasMoreTokens() ? tokens.nextToken() : CommandScanner.readLine("Введите ключ:");
                assert token != null;
                int value = Integer.parseInt(token);
                if (validator.test(value)) return value;
            } catch (NumberFormatException ignored) {
            }
            System.out.println("Некорректный ввод. " + prompt + ":");
        }
    }

    private static float getNextValidFloat(StringTokenizer tokens, DoublePredicate validator) {
        while (true) {
            try {
                String token = tokens != null && tokens.hasMoreTokens() ? tokens.nextToken() : CommandScanner.readLine("Введите ключ:");
                assert token != null;
                float value = Float.parseFloat(token);
                if (validator.test(value)) return value;
            } catch (NumberFormatException ignored) {
            }
            System.out.println("Некорректный ввод. " + "Координата Y (вещественное число > -983)" + ":");
        }
    }

    private static long getNextValidLong(StringTokenizer tokens, LongPredicate validator) {
        while (true) {
            try {
                String token = tokens != null && tokens.hasMoreTokens() ? tokens.nextToken() : CommandScanner.readLine("Введите ключ:");
                assert token != null;
                long value = Long.parseLong(token);
                if (validator.test(value)) return value;
            } catch (NumberFormatException ignored) {
            }
            System.out.println("Некорректный ввод. " + "Рост (целое число > 0)" + ":");
        }
    }

    private static double getNextValidDouble(String prompt, StringTokenizer tokens) {
        while (true) {
            try {
                String token = tokens != null && tokens.hasMoreTokens() ? tokens.nextToken() : CommandScanner.readLine("Введите ключ:");
                assert token != null;
                return Double.parseDouble(token);
            } catch (NumberFormatException ignored) {
                System.out.println("Некорректный ввод. " + prompt + ":");
            }
        }
    }

    private static ZonedDateTime getNextValidZonedDateTime(StringTokenizer tokens) {
        while (true) {
            try {
                String token = tokens != null && tokens.hasMoreTokens() ? tokens.nextToken() : CommandScanner.readLine("Введите ключ:");
                assert token != null;
                return ZonedDateTime.parse(token);
            } catch (DateTimeParseException e) {
                System.out.println("Некорректная дата. " + "Дата рождения (в формате 2000-01-01T12:00:00+03:00)" + ":");
            }
        }
    }

    private static Country getNextValidCountry(StringTokenizer tokens) {
        while (true) {
            try {
                String token = tokens != null && tokens.hasMoreTokens() ? tokens.nextToken() : CommandScanner.readLine("Введите ключ:");
                assert token != null;
                return Country.valueOf(token.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Неизвестная страна. " + "Национальность (GERMANY, FRANCE, JAPAN)" + ":");
            }
        }
    }
}


