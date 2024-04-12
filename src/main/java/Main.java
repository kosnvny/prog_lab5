import commandLine.Console;
import commands.*;
import exceptions.ForcedExit;
import managers.CollectionManager;
import managers.CommandManager;
import managers.FileManager;
import managers.RuntimeManager;
import models.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Console console = new Console();
        CollectionManager collectionManager = new CollectionManager();
        CommandManager commandManager = new CommandManager();
        FileManager fileManager = new FileManager(collectionManager, console);
        try {
            fileManager.readFile();
            fileManager.createObjects();
        } catch (ForcedExit e) {
            console.printError(e.getMessage());
            console.println("пупупупу па па па");
            System.exit(0);
        }
        commandManager.addCommands(List.of(new AddCommand(collectionManager, console),
                new ClearCommand(collectionManager, console),
                new CountLessThanSemesterEnumCommand(collectionManager, console),
                new ExecuteScriptCommand(commandManager, console),
                new ExitCommand(),
                new HeadCommand(collectionManager, console),
                new HelpCommand(commandManager, console),
                new InfoCommand(collectionManager, console),
                new PrintDescendingCommand(collectionManager, console),
                new PrintUniqueSemesterEnumCommand(collectionManager, console),
                new RemoveByIDCommand(collectionManager, console),
                new RemoveFirstCommand(collectionManager, console),
                new RemoveGreaterCommand(collectionManager, console),
                new SaveCommand(fileManager, collectionManager, console),
                new ShowCommand(collectionManager, console),
                new UpdateIDCommand(collectionManager, console)));
        collectionManager.addElement(new StudyGroup("52", new Coordinates(52, 0f), 52L, 1, FormOfEducation.DISTANCE_EDUCATION,
                Semester.FOURTH, new Person("52", 52f, Colour.GREEN, Country.INDIA))); // чтобы в коллекции УЖЕ хоть что-то было (я заколебалась придумывать имена)
        new RuntimeManager(commandManager, console).letsGo();
    }
}
/*сделать апдейт айдишников
* */