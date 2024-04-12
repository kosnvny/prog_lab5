package commands;

import commandLine.Console;
import exceptions.*;
import managers.CollectionManager;
import models.Semester;

public class CountLessThanSemesterEnumCommand extends Command{
    /**{@link CollectionManager}, в котором хранится коллекция и с помощью которого выполняется команда*/
    private final CollectionManager collectionManager;
    /***/
    private final Console console;
    public CountLessThanSemesterEnumCommand(CollectionManager collectionManager, Console console) {
        super("count_less_than_semester_enum", " semesterEnum : вывести количество элементов, значение поля semesterEnum которых меньше заданного");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    /**Абстрактный метод для выполнения команды
     * @param args Аргументы команды
     * @throws IllegalArguments Выбрасывается, если получены пустые аргументы
     * */
    @Override
    public void execute(String args) throws IllegalArguments {
        if (args.isBlank()) throw new IllegalArguments("В команде count_less_than_semester_enum не может быть пустого аргумента");
        try {
            Semester semester = Semester.valueOf(args.trim().toUpperCase());
            console.println(collectionManager.countLessThanSemester(semester));
        } catch (IllegalArgumentException e) {
            throw new IllegalArguments("Вы ввели невалидное значение :(");
        }
    }
}
