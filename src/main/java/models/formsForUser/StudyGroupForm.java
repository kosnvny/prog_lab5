package models.formsForUser;

import commandLine.*;
import managers.FileManager;
import models.StudyGroup;

public class StudyGroupForm extends Form<StudyGroup> {
    private final Printable console;
    private final UserInput userInput;
    public StudyGroupForm(Printable console) {
        this.console = (FileManager.isIsItInFile() ? new BlankConsole() : console);
        this.userInput = (FileManager.isIsItInFile() ? new ExecuteScriptManager() : new ConsoleInput());
    }
    /**
     * Абстрактный метод, "строящий" новый объект заданного типа
     *
     * @return StudyGroup
     */
    @Override
    public StudyGroup build() {
        return new StudyGroup(askName(), new CoordinatesForm(console).build(), askCount(), askExpelled(),
                new FormOfEducationForm(console).build(), new SemesterForm(console).build(),
                new PersonForm(console).build());
    }

    private String askName() {
        console.println("Введите название группы, требование: не должно быть пустым или пробелом");
        while (true) {
            String input = userInput.nextLine();
            if (!input.isBlank()) return input;
            console.printError("Название не должно быть пустым или пробелом");
        }
    }

    private long askCount() {
        console.println("Введите количество учащихся, требование: должно быть больше 0");
        while (true) {
            String input = userInput.nextLine();
            try {
                long count = Long.parseLong(input);
                if (count > 0) return count;
                console.printError("Количество студентов должно быть положительным");
            } catch (NumberFormatException e) {
                console.printError("Краказябру ввёл");
            }
        }
    }

    private int askExpelled() {
        console.println("Введите количество студентов на отчисление, требования: должно быть больше 0");
        while (true) {
            String input = userInput.nextLine();
            try {
                int ex = Integer.parseInt(input);
                if (ex > 0) return ex;
                console.printError("Количество студентов на отчисление должно быть положительным");
            } catch (NumberFormatException e) {
                console.printError("Краказябру ввёл вместо числа");
            }
        }
    }
}
