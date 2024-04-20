package managers;

import commands.Command;
import exceptions.*;
import models.Person;
import models.StudyGroup;

import java.util.Collection;
import java.util.HashMap;

/**Класс для работы с командами и их запуском*/
public class CommandManager {
    /**Отображение, хранящее как ключ строковое предствление команды, а как значение - саму команду*/
    private final HashMap<String, Command> commands = new HashMap<>();

    /** Добавление команды в отображение и коллекции
     * @param command добавляемая команда*/
    public void addCommand(Command command) {
        commands.put(command.getName(), command);
    }

    /** Добавление коллекции команд
     * @param commands добавляемые команды*/
    public void addCommands(Collection<Command> commands) {
        for (Command c: commands) {
            this.commands.put(c.getName(), c);
        }
    }

    public HashMap<String, Command> getCommands() {
        return commands;
    }

    public String getAllCommands() {
        StringBuilder text = new StringBuilder();
        for (String s : commands.keySet()) {
            text.append(s).append(" ");
            text.append(commands.get(s).getDescription()).append("\r\n");
        }
        return text.toString();
    }

    /** Выполнение полученной команды
     * @param name название команды
     * @param args аргументы
     * @throws CommandDoesNotExist команда не существует
     * @throws IllegalArguments невалидные значения
     * @throws ForcedExit выход из приложения
     * @throws RecursionInScriptException рекурсия в скрипте
     * @throws InvalideForm форма заполнения объектов {@link StudyGroup}, {@link Person} или их составляющих получила неверные аргументы
     * */
    public void execute(String name, String args) throws CommandDoesNotExist, IllegalArguments, ForcedExit, RecursionInScriptException, InvalideForm {
        Command command = commands.get(name);
        if (command == null) throw new CommandDoesNotExist(name);
        command.execute(args);
    }
}
