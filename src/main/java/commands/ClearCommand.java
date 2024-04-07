package commands;

import commandLine.Console;
import commandLine.Printable;
import exceptions.*;
import managers.CollectionManager;

public class ClearCommand extends Command{
    /**Поле, отвечающее за вывод информации о работе команды*/
    private Printable console;
    /**{@link CollectionManager}, в котором хранится коллекция и с помощью которого выполняется команда*/
    private CollectionManager collectionManager;
    public ClearCommand(CollectionManager collectionManager, Console console) {
        super("clear", "очистить коллекцию");
        this.collectionManager = collectionManager;
        this.console = console;
    }
    /** Метод для выполнения команды
     * @param args Аргументы команды
     * @throws IllegalArguments Выбрасывается, если получены не пустые аргументы
     * */
    @Override
    public void execute(String args) throws IllegalArguments {
        if (!args.isBlank()) throw new IllegalArguments("В команде clear не должно быть аргументов");
        collectionManager.clearCollection();
        console.println("Коллекция очищена");
    }
}
