package managers;

import commandLine.Console;
import commandLine.Printable;
import exceptions.*;
import models.Person;
import models.StudyGroup;

import java.util.Scanner;

/**Класс для начала работы приложения*/
public class RuntimeManager {
    /**{@link CommandManager}, в котором хранятся команды*/
    private CommandManager commandManager;
    /**Поле, отвечающее за вывод информации о работе команды*/
    private Printable console;

    public RuntimeManager(CommandManager commandManager, Console console) {
        this.commandManager = commandManager;
        this.console = console;
    }

    /**Метод, запускающий приложение*/
    public void letsGo() {
        try {
            if (FileManager.isIsItInFile()) {
                String file = FileManager.getText();
                String[] text = file.split("\r\n");
                int i = 0;
                while (i < text.length) {
                    if (text[i].equals("add")) {
                        ScannerManager.setUsersScanner(makeString(text, i+1, i+12));
                        this.launch(text[i].split(" ", 2));
                        i = i + 12;
                    } else if (text[i].equals("update")) {
                        ScannerManager.setUsersScanner(makeString(text, i+1, i+12));
                        this.launch(text[i].split(" ", 2));
                        i = i + 12;
                    } else if (text[i].equals("remove_greater")) {
                        ScannerManager.setUsersScanner(makeString(text, i+1, i+12));
                        this.launch(text[i].split(" ", 2));
                        i = i + 12;
                    } else {
                        this.launch(text[i].split(" ", 2));
                        i = i + 1;
                    }
                }
            } else {
            Scanner userScanner = ScannerManager.getUsersScanner();
                while (true) {
                        if (!userScanner.hasNext()) throw new ForcedExit("Ввод отсутствует(");
                        String userCommand = userScanner.nextLine().trim() + " "; // прибавляем пробел, чтобы split выдал два элемента в массиве
                        this.launch(userCommand.split(" ", 2));
                    }
                }
        } catch (InvalideForm | IllegalArguments | ForcedExit e) {
            if (e.getMessage().equals("Вы вышли из приложения с помощью команды exit")) {
                console.println(e.getMessage());
                System.exit(0);
            } else if (e.getMessage().equals("Ввод отсутствует(")) {
                console.printError(e.getMessage());
                System.exit(0);
            }
            else console.printError(e.getMessage());
        } catch (CommandDoesNotExist e) {
            console.printError("В списке нет введённой команды: " + e.getMessage());
        } catch (RecursionInScriptException e) {
            console.printError("Найдена рекурсия в файлах. Невозможно выполнение команд(");
            System.exit(0);
        }
    }

    /** Триггер выполнения команды из {@link CommandManager}
     * @param userCommand массив из 2 элементов, первый - название команды, второй - аргументы
     * @throws CommandDoesNotExist несуществующая команда
     * @throws ForcedExit команда привела к окончанию работы программы
     * @throws IllegalArguments команда содержит неверные аргументы
     * @throws RecursionInScriptException в скрипте обнаружена рекурсия
     * @throws InvalideForm форма заполнения объектов {@link StudyGroup}, {@link Person} или их составляющих получила невалидные данные
     */
    public void launch(String[] userCommand) throws CommandDoesNotExist, ForcedExit, IllegalArguments, RecursionInScriptException, InvalideForm {
        if (userCommand[0].isEmpty()) return;
        commandManager.execute(userCommand[0], userCommand[1]);
    }

    public String makeString(String[] text, int start, int end) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = start; i < end; i++) {
            if (i != end - 1) {
                stringBuilder.append(text[i]).append("\r\n");
            } else {
                stringBuilder.append(text[i]);
            }
        }
        return stringBuilder.toString();
    }
}

