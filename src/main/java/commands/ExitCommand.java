package commands;

import exceptions.*;

public class ExitCommand extends Command{
    public ExitCommand() {
        super("exit", "завершить программу (без сохранения в файл)");
    }

    /** Метод для выполнения команды
     * @param args Аргументы команды
     * @throws IllegalArguments Аргумент этой команды должен быть пустым
     * @throws ForcedExit Выполнение команды*/
    @Override
    public void execute(String args) throws ForcedExit, IllegalArguments {
        if (!args.isBlank()) throw new IllegalArguments("Аргумент этой команды должен быть пустым");
        throw new ForcedExit("Вы вышли из приложения с помощью команды exit");
    }
}
