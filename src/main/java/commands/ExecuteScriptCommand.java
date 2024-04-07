package commands;

import commandLine.ExecuteScriptManager;
import commandLine.Printable;
import exceptions.*;
import managers.CommandManager;
import managers.FileManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Класс команды execute_script*/
public class ExecuteScriptCommand extends Command{
    /**Поле, отвечающее за вывод информации о работе команды*/
    private Printable console;
    /**{@link CommandManager}, запускающий выполнение команд*/
    private CommandManager commandManager;
    /**{@link FileManager}, работающий с поступившем файлом*/
    public ExecuteScriptCommand(CommandManager commandManager, Printable console) {
        super("execute_script", "file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
        this.commandManager = commandManager;
        this.console = console;
    }
    /** Метод для выполнения команды
     * @param args Аргументы команды
     * @throws IllegalArguments Аргумент этой команды не может быть пустым
     * @throws RecursionInScriptException Скрипт вызывает сам себя, или скрипты образовали цикл
     * @throws ForcedExit Во время выполнения команды случилось непоправимое
     * @throws CommandDoesNotExist Команда не существует
     * @throws InvalideForm {@link models.formsForUser.StudyGroupForm} или {@link models.formsForUser.PersonForm} получила неверные аргументы*/
    @Override
    public void execute(String args) throws IllegalArguments, RecursionInScriptException, ForcedExit, CommandDoesNotExist, InvalideForm {
        if (args.isBlank()) throw new IllegalArguments("В команде execute_script аргументом должен быть путь");
        try {
            ExecuteScriptManager.addFile(args.trim());
            for (String line = ExecuteScriptManager.readLine(); line != null; line = ExecuteScriptManager.readLine()) {
                String[] commandToExecute = (line + " ").split(" ", 2);
                if (commandToExecute[0].isBlank()) throw new CommandDoesNotExist();
                if (commandToExecute[0].equals("execute_script")) {
                    if (ExecuteScriptManager.haveWeBeenInFile(args.trim())) throw new RecursionInScriptException();
                }
                console.println("А теперь выполняем команду " + commandToExecute[0]);
                commandManager.execute(commandToExecute[0], commandToExecute[1]);
                if (commandToExecute[0].equals("execute_script")) ExecuteScriptManager.popFile();
            }
        } catch (FileNotFoundException e) {
            throw new ForcedExit("Файл не найден(");
        } catch (IOException e) {
            throw new ForcedExit("В файле нет данных для выполнения команд!");
        }
        FileManager.setIsItInFile(false);
    }
}
