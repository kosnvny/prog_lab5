package commandLine;

/**Класс для "пустой" консоли, используется при вводе из файла*/
public class BlankConsole implements Printable{
    /** Пустой метод вывода с переводом на следующую строку
     * @param args
     */
    @Override
    public void println(String args) {

    }

    /** Пустой метод вывода
     * @param args
     */
    @Override
    public void print(String args) {

    }

    /** Пустой метод вывода ошибок
     * @param args
     */
    @Override
    public void printError(String args) {

    }
}
