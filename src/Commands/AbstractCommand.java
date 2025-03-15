package Commands;

// 2. Абстрактный суперкласс - реализует базовую функциональность
public abstract class AbstractCommand implements Command {
    protected String name; // Название команды
    protected String description; // Описание команды

    public AbstractCommand(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public abstract void execute(String[] args); // Оставляем реализацию для наследников
}
