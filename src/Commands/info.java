package Commands;

import Util.CommandScanner;

// Команда Info - выводит информацию о программе
public class info extends AbstractCommand {
    public info() {
        super("info","");
    }
    @Override
    public void execute(String[] args) {
        CommandScanner.disableInputMode();
        System.out.println("Пока не знаю что сюда вписать");
    }
}
