package models.formsForUser;

import commandLine.*;
import exceptions.InvalideForm;
import managers.FileManager;
import models.Country;
import models.FormOfEducation;

public class FormOfEducationForm extends Form<FormOfEducation>{
    private Printable console;
    private UserInput userInput;
    public FormOfEducationForm(Printable console) {
        this.console = (FileManager.isIsItInFile() ? new BlankConsole() : console);
        this.userInput = (FileManager.isIsItInFile() ? new ExecuteScriptManager() : new ConsoleInput());
    }
    /**
     * Абстрактный метод, "строящий" новый объект заданного типа
     *
     * @return FormOfEducation
     */
    @Override
    public FormOfEducation build() {
        console.println("Введите форму обучения");
        console.println("Возможные формы: " + FormOfEducation.nameAll());
        while (true) {
            String input = userInput.nextLine().trim();
            try {
                return FormOfEducation.valueOf(input.toUpperCase());
            } catch (IllegalArgumentException exception) {
                console.printError("Такого цвета нет в списке");
                if (FileManager.isIsItInFile()) console.printError("Невалидные значения для цвета в файле");
            }
        }
    }
}
