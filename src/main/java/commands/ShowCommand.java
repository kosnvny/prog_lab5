package commands;

import commandLine.Console;
import commandLine.Printable;
import exceptions.IllegalArguments;
import managers.CollectionManager;

public class ShowCommand extends Command{
    /**{@link CollectionManager}, в котором хранится коллекция и с помощью которого выполняется команда*/
    private CollectionManager collectionManager;
    /**Поле, отвечающее за вывод информации о работе команды*/
    private Printable console;
    public ShowCommand(CollectionManager collectionManager, Console console) {
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        this.collectionManager = collectionManager;
        this.console = console;
    }
    /** Метод для выполнения команды
     * @param args Аргументы команды
     * @throws IllegalArguments Поступили невалидные аргументы
     */
    @Override
    public void execute(String args) throws IllegalArguments {
        if (!args.isBlank()) throw new IllegalArguments("В команде show нет аргументов");
        console.println("Выводим все элементы коллекции в строковом представлении");
        if (collectionManager.getCollection().isEmpty()) console.println("В коллекции нет элементов(");
        else console.println(collectionManager.show());
    }
}
