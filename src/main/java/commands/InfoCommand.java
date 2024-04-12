package commands;

import commandLine.Console;
import commandLine.Printable;
import exceptions.IllegalArguments;
import managers.CollectionManager;

public class InfoCommand extends Command {
    /**Поле, отвечающее за вывод информации о работе команды*/
    private final Printable console;
    /**{@link CollectionManager}, в котором хранится коллекция и с помощью которого выполняется команда*/
    private final CollectionManager collectionManager;

    public InfoCommand(CollectionManager collectionManager, Console console) {
        super("info", "вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    /** Метод для выполнения команды
     * @param args Аргументы команды
     * @throws IllegalArguments Поступили невалидные аргументы
     */
    @Override
    public void execute(String args) throws IllegalArguments {
        if (!args.isBlank()) throw new IllegalArguments("В команде info не может быть аргументов");
        console.println(collectionManager.info());
    }
}
