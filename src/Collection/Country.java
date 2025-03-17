package Collection;

public enum Country {
    GERMANY,
    FRANCE,
    JAPAN;

/*
 * Проверяет, меньше ли текущая национальность, чем переданная.
 * Другая национальность для сравнения
 * @return true, если текущая меньше
 */
public boolean isLessThan(Country other) {
    return this.compareTo(other) < 0;

    }
}