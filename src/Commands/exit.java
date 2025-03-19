package Commands;

import Util.CommandScanner;

public class exit extends AbstractCommand {
    public exit() {
        super("exit", "Выйти из команды");
    }

    @Override
    public void execute(String[] args) {
        CommandScanner.disableInputMode();
        System.out.println("Выход из программы...");
        System.exit(0);
    }
}
