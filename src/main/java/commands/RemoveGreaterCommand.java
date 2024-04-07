package commands;

import commandLine.Console;
import commandLine.Printable;
import exceptions.IllegalArguments;
import exceptions.InvalideForm;
import managers.CollectionManager;
import managers.ScannerManager;
import models.StudyGroup;
import models.formsForUser.*;

import java.util.Scanner;

public class RemoveGreaterCommand extends Command {
    /**{@link CollectionManager}, в котором хранится коллекция и с помощью которого выполняется команда*/
    private CollectionManager collectionManager;
    /**Поле, отвечающее за вывод информации о работе команды*/
    private Printable console;
    public RemoveGreaterCommand(CollectionManager collectionManager, Console console) {
        super("remove_greater", "{element} : удалить из коллекции все элементы, превышающие заданный");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    /** Метод для выполнения команды
     * @param args Аргументы команды
     * @throws IllegalArguments Поступили невалидные аргументы
     * @throws InvalideForm {@link StudyGroupForm} или {@link PersonForm} получила невалидные аргументы*/
    @Override
    public void execute(String args) throws IllegalArguments, InvalideForm {
        if (!args.isBlank()) throw new IllegalArguments("В команде " + getName() + " аргументы вводятся с новой строки");
        collectionManager.removeGreater(new StudyGroupForm(console).build());
    }
}
