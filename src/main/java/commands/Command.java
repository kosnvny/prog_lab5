package commands;

import exceptions.*;

import java.util.Objects;

/**
 * Абстрактный класс для всех команд*/
public abstract class Command {
    /**Название команды*/
    private String name;
    /**Описание команды*/
    private String description;
    public Command(String name, String description) {
        this.name = name;
        this.description = description;
    }
    /**Абстрактный метод для выполнения команды
     * @param args Аргументы команды
     * @throws ForcedExit
     * @throws IllegalArguments
     * @throws RecursionInScriptException
     * @throws CommandDoesNotExist
     * @throws InvalideForm*/
    public abstract void execute(String args) throws ForcedExit, IllegalArguments, RecursionInScriptException, CommandDoesNotExist, InvalideForm;
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Command command = (Command) o;
        return Objects.equals(name, command.name) && Objects.equals(description, command.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }

    @Override
    public String toString() {
        return "Command{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
