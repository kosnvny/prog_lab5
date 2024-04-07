package commands;

import commandLine.Console;
import commandLine.Printable;
import exceptions.IllegalArguments;
import managers.CollectionManager;

public class HeadCommand extends Command{
    /**Поле, отвечающее за вывод информации о работе команды*/
    private Printable console;
    /**{@link CollectionManager}, в котором хранится коллекция и с помощью которого выполняется команда*/
    private final CollectionManager collectionManager;
    public HeadCommand(CollectionManager collectionManager, Console console) {
        super("head", "вывести первый элемент коллекции");
        this.collectionManager = collectionManager;
        this.console = console;
    }
    /** Метод для выполнения команды
     * @param args Аргументы команды
     * @throws IllegalArguments Были получены аргументы
     */
    @Override
    public void execute(String args) throws IllegalArguments {
        if (!args.isBlank()) throw new IllegalArguments("В команде head не должно быть аргументов");
        console.println(collectionManager.head().toString());
    }
}
