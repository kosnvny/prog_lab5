package managers;

import java.util.Scanner;

/**Класс, хранящий пользовательский ввод*/
public class ScannerManager {
    public static Scanner usersScanner = new Scanner(System.in);

    /***/
    public static void setUsersScanner(String usersScanner1) {
        usersScanner = new Scanner(usersScanner1);
    }

    /** Статический метод, передающий пользовательский ввод
     * @return Scanner*/
    public static Scanner getUsersScanner() {
        return usersScanner;
    }
}
