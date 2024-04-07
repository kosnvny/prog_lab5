package commandLine;


/**Класс консоли*/
public class Console implements Printable{
    /** Метод вывода с переходом на следующую строку
     * @param args
     */
    @Override
    public void println(String args) {
        System.out.println(args);
    }

    /** Метод вывода
     * @param args
     */
    @Override
    public void print(String args) {
        System.out.print(args);
    }

    /** Метод вывода ошибок
     * @param args
     */
    @Override
    public void printError(String args) {
        System.out.println("Обнаружена ошибка: " + args);
    }
}