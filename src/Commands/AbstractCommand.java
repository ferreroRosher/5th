package Commands;

/**
 * Абстрактный класс для всех команд.
 * Каждая команда имеет имя и описание.
 */
public abstract class AbstractCommand implements Command {
    private final String name;
    private final String description;

    public AbstractCommand(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Возвращает название команды.
     * @return строка с названием команды
     */
    public String getName() {
        return name;
    }

    /**
     * Возвращает описание команды.
     * @return строка с описанием команды
     */
    public String getDescription() {
        return description;
    }

    /**
     * Метод, который должны реализовать все команды.
     */
    public abstract void execute(String[] args);
}
