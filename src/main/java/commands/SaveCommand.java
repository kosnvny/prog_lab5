package commands;

import commandLine.Console;
import commandLine.Printable;
import exceptions.ForcedExit;
import exceptions.IllegalArguments;
import managers.CollectionManager;
import managers.FileManager;

public class SaveCommand extends Command{
    /**{@link FileManager}, работающий с файлом*/
    private final FileManager fileManager;
    /**Поле, отвечающее за вывод информации о работе команды*/
    private final Printable console;
    private final CollectionManager collectionManager;
    public SaveCommand(FileManager fileManager, CollectionManager collectionManager, Console console) {
        super("save", "сохранить коллекцию в файл");
        this.fileManager = fileManager;
        this.collectionManager = collectionManager;
        this.console = console;
    }

    /** Метод для выполнения команды
     * @param args Аргументы команды
     * @throws IllegalArguments Невалидные аргументы
     * @throws ForcedExit Невозможно записать коллекцию в файл*/
    @Override
    public void execute(String args) throws IllegalArguments, ForcedExit {
        if (!args.isBlank()) throw new IllegalArguments("В команде save нет аргументов");
        fileManager.writeCollection(collectionManager.getCollection());
        console.println("Коллекция в файле!");
    }
}
