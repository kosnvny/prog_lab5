package models.formsForUser;

import commandLine.*;
import exceptions.InvalideForm;
import managers.FileManager;
import models.Person;

public class PersonForm extends Form<Person>{
    private Printable console;
    private UserInput userInput;
    public PersonForm(Printable console) {
        this.console = (FileManager.isIsItInFile() ? new BlankConsole() : console);
        this.userInput = (FileManager.isIsItInFile() ? new ExecuteScriptManager() : new ConsoleInput());
    }
    /**
     * Абстрактный метод, "строящий" новый объект заданного типа
     *
     * @return T
     * @throws InvalideForm невалидные значения
     */
    @Override
    public Person build() {
        return new Person(askName(), askWeight(), new ColourForm(console).build(), new CountryForm(console).build());
    }

    private String askName() {
        console.println("Пожалуйста, введите имя человека");
        while (true) {
            String input = userInput.nextLine();
            if (!input.isBlank()) return input;
            console.printError("Имя не может быть пустым или null");
        }
    }

    private float askWeight() {
        console.println("Пожалуйста, введите вес");
        while (true) {
            String input = userInput.nextLine();
            try {
                float weight = Float.parseFloat(input);
                if (weight > 0) return weight;
                console.printError("Вес не может быть неположительным");
            } catch (NumberFormatException e) {
                console.printError("Вес должен быть типа float");
            }
        }
    }
}
