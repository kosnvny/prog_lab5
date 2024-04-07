package commands;

import commandLine.Console;
import commandLine.Printable;
import exceptions.IllegalArguments;
import managers.CollectionManager;

public class RemoveFirstCommand extends Command{
    /**{@link CollectionManager}, в котором хранится коллекция и с помощью которого выполняется команда*/
    private CollectionManager collectionManager;
    /**Поле, отвечающее за вывод информации о работе команды*/
    private Printable console;
    public RemoveFirstCommand(CollectionManager collectionManager, Console console) {
        super("remove_first", "удалить первый элемент из коллекции");
        this.collectionManager = collectionManager;
        this.console = console;
    }
    /** Метод для выполнения команды
     * @param args Аргументы команды
     * @throws IllegalArguments Были получены аргументы
     */
    @Override
    public void execute(String args) throws IllegalArguments {
        if (!args.isBlank()) throw new IllegalArguments("В команде remove_first нет аргументов");
        collectionManager.removeFirstElement();
        console.println("Первый элемент коллекции удалён");
    }
}
