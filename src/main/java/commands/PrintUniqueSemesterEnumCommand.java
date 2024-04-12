package commands;

import commandLine.Console;
import commandLine.Printable;
import exceptions.IllegalArguments;
import managers.CollectionManager;

public class PrintUniqueSemesterEnumCommand extends Command{
    /**{@link CollectionManager}, в котором хранится коллекция и с помощью которого выполняется команда*/
    private final CollectionManager collectionManager;
    /**Поле, отвечающее за вывод информации о работе команды*/
    private final Printable console;
    public PrintUniqueSemesterEnumCommand(CollectionManager collectionManager, Console console) {
        super("print_unique_semester_enum", "вывести уникальные значения поля semesterEnum всех элементов в коллекции");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    /** Метод для выполнения команды
     * @param args Аргументы команды
     * @throws IllegalArguments Поступили невалидные аргументы
     */
    @Override
    public void execute(String args) throws IllegalArguments {
        if (!args.isBlank()) throw new IllegalArguments("В команде print_unique_semester_enum не должно быть аргументов");
        console.println("Выводим уникальные значения поля Semester");
        console.println(collectionManager.printUniqueSemester());
    }
}
