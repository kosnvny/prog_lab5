package exceptions;

import java.io.IOException;

/**Класс для исключения при нахождении рекурсии в скриптах*/
public class RecursionInScriptException extends IOException {
    public RecursionInScriptException() {
        super("Переданные скрипты вызывают друг друга и образуют цикл");
    }
}
