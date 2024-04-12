package commands;

import commandLine.Console;
import commandLine.Printable;
import exceptions.IllegalArguments;
import managers.CommandManager;

public class HelpCommand extends Command{
    /**Поле, отвечающее за вывод информации о работе команды*/
    private final Printable console;
    /**{@link CommandManager}, хранящий все команды*/
    private final CommandManager commandManager;
    public HelpCommand(CommandManager commandManager, Console console) {
        super("help", "вывести справку по доступным командам");
        this.commandManager = commandManager;
        this.console = console;
    }
    /** Метод для выполнения команды
     * @param args Аргументы команды
     * @throws IllegalArguments Были получены непустые аргументы
     */
    public void execute(String args) throws IllegalArguments {
        if (!args.isBlank()) throw new IllegalArguments("В команде help нет аргументов");
        console.println(commandManager.getAllCommands());
    }
}
