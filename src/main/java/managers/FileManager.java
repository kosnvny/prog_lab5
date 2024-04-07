package managers;

import com.google.gson.*;
import commandLine.ExecuteScriptManager;
import commandLine.Printable;
import exceptions.ForcedExit;
import models.StudyGroup;

import java.io.*;
import java.time.LocalDate;
import java.util.LinkedList;

/**Класс, работающий с файлом*/
public class FileManager {
    /**{@link CollectionManager}, в котором находится коллекция*/
    private CollectionManager collectionManager;
    /***/
    private Printable console;
    /**{@link Gson}-объект для чтения/записи JSON-файлов*/
    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .serializeNulls()
            .registerTypeAdapter(LocalDate.class, new LocalDateTimeChecker())
            .create();
    private static boolean isItInFile = false;
    private static String text;
    public FileManager(CollectionManager collectionManager, Printable console) {
        this.collectionManager = collectionManager;
        this.console = console;
    }

    public static boolean isIsItInFile() {
        return isItInFile;
    }

    public static String getText() {
        return text;
    }

    public static void setIsItInFile(boolean isItInFile) {
        FileManager.isItInFile = isItInFile;
    }

    /**
     * Считывает содержимое файла.
     * @throws ForcedExit Файл некорректен
     */
    public void readFile() throws ForcedExit {
        String filePath = System.getenv("filePathToRead");
        if (filePath == null) return;
        if (filePath.isBlank()) throw new ForcedExit("Путь до файла должен содержаться в переменной окружения filePathToRead");
        console.println("Путь до файла получен");
        File file = new File(filePath);
        FileInputStream fis;
        BufferedInputStream bis;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            isItInFile = true;
            while (bis.available() > 0) {
                stringBuilder.append((char) bis.read());
            }
            fis.close();
            bis.close();
            if (stringBuilder.isEmpty()) text = "";
            else text = stringBuilder.toString();
            ExecuteScriptManager.addFile(filePath);

        } catch (IOException e) {
            throw new ForcedExit("В файле закралась ошибка");
        }
    }

    /**
     * Записывает коллекцию в файл.
     * @throws ForcedExit Проблема с файлом (невозможно открыть или невалидный путь)
     */
    public void writeCollection(LinkedList<StudyGroup> collection) throws ForcedExit {
        String filePath = System.getenv("filePathToWrite");
        if (filePath.isBlank()) throw new ForcedExit("Путь до файла должен содержаться в переменной окружения filePathToWrite");
        console.println("Путь до файла получен");
        try {
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(new File(filePath)));
            osw.write(gson.toJson(collection));
            console.println("Коллекция записана в файл!");
        } catch (FileNotFoundException e) {
            throw new ForcedExit("Файл не был найден");
        } catch (IOException e) {
            throw new ForcedExit("Невозможно сохранить коллекцию в json-файл(");
        } finally {
            isItInFile = false;
        }
    }

}