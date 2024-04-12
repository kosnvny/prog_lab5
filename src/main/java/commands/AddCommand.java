package commands;

import commandLine.Console;
import commandLine.Printable;
import exceptions.IllegalArguments;
import exceptions.InvalideForm;
import managers.*;
import models.*;
import models.formsForUser.StudyGroupForm;

/**
 * Класс команды add
 * */
public class AddCommand extends Command{
    /**{@link CollectionManager}, в котором хранится коллекция и с помощью которого выполняется команда*/
    private final CollectionManager collectionManager;
    /**Поле, отвечающее за вывод информации о работе команды*/
    private final Printable console;
    public AddCommand(CollectionManager collectionManager, Console console) {
        super("add", "{element} : добавить новый элемент в коллекцию");
        this.collectionManager = collectionManager;
        this.console = console;
    }
    /**
     * Выполнение команды
     * @param args аргументы комнады
     * @throws IllegalArguments аргументы не валидны по различным причинам
     * @throws InvalideForm форма заполнения объектов {@link StudyGroup}, {@link Person} или их составляющих неправильна*/
    @Override
    public void execute(String args) throws IllegalArguments, InvalideForm {
        if (!args.isBlank()) throw new IllegalArguments("Аргументы в команде add должны вводиться со следующей строки");
        console.println("Создание StudyGroup");
        collectionManager.addElement(new StudyGroupForm(console).build());
        console.println("StudyGroup добавлена в коллекцию (всё у нас прекрасно)");
    }
}
