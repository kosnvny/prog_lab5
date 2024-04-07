package commands;

import commandLine.Console;
import commandLine.Printable;
import exceptions.IllegalArguments;
import managers.CollectionManager;

public class PrintDescendingCommand extends Command{
    /**Поле, отвечающее за вывод информации о работе команды*/
    private Printable console;
    /**{@link CollectionManager}, в котором хранится коллекция и с помощью которого выполняется команда*/
    private CollectionManager collectionManager;
    public PrintDescendingCommand(CollectionManager collectionManager, Console console) {
        super("print_descending", "вывести элементы коллекции в порядке убывания");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    /** Метод для выполнения команды
     * @param args Аргументы команды
     * @throws IllegalArguments Поступили невалидные аргументы
     */
    @Override
    public void execute(String args) throws IllegalArguments {
        if (!args.isBlank()) throw new IllegalArguments("В команде print_descending не должно быть аргументов");
        console.println(collectionManager.descendingOrder());
    }
}
