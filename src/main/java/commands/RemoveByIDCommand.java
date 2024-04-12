package commands;

import commandLine.Console;
import commandLine.Printable;
import exceptions.IllegalArguments;
import managers.CollectionManager;

public class RemoveByIDCommand extends Command {
    /**{@link CollectionManager}, в котором хранится коллекция и с помощью которого выполняется команда*/
    private final CollectionManager collectionManager;
    /**Поле, отвечающее за вывод информации о работе команды*/
    private final Printable console;
    public RemoveByIDCommand(CollectionManager collectionManager, Console console) {
        super("remove_by_id", "id : удалить элемент из коллекции по его id");
        this.collectionManager = collectionManager;
        this.console = console;
    }
    /** Метод для выполнения команды
     * @param args Аргументы команды
     * @throws IllegalArguments Команде поступили невалидные аргументы
     */
    @Override
    public void execute(String args) throws IllegalArguments {
        if (args.isBlank()) throw new IllegalArguments("В команде remove_by_id должны быть аргументы");
        try {
            int id = Integer.parseInt(args.trim());
            collectionManager.removeByID(id);
            console.println("Элемент по заданному id был удалён");
        } catch (NumberFormatException e) {
            throw new IllegalArguments("Вы ввели не целое число(");
        }
    }
}
