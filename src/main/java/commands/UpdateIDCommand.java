package commands;

import commandLine.Console;
import commandLine.Printable;
import exceptions.*;
import managers.CollectionManager;
import models.StudyGroup;
import models.formsForUser.StudyGroupForm;


public class UpdateIDCommand extends Command{
    /**{@link CollectionManager}, в котором хранится коллекция и с помощью которого выполняется команда*/
    private final CollectionManager collectionManager;
    /**Поле, отвечающее за вывод информации о работе команды*/
    private final Printable console;
    public UpdateIDCommand(CollectionManager collectionManager, Console console) {
        super("update", "id {element} : обновить значение элемента коллекции, id которого равен заданному");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    /** Метод для выполнения команды
     * @param args Аргументы команды
     * @throws IllegalArguments Аргумент этой команды не может быть пустым
     * @throws InvalideForm Форма для {@link StudyGroup} получила некорректные аргументы*/
    @Override
    public void execute(String args) throws IllegalArguments, InvalideForm {
        if (args.isBlank()) throw new IllegalArguments("В команде update должны быть непустые аргументы");
        try {
            int id = Integer.parseInt(args.trim());
            if (collectionManager.checkIfExists(id)) {
                collectionManager.updateID(id, new StudyGroupForm(console).build());
                console.println("Элемент коллекции с заданным id был обновлён");
            } else {
                throw new IllegalArguments("Данный id не существует");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArguments("id должен быть целочисленным");
        }
    }
}
