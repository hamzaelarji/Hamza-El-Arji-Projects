package uiView;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The FileUtil class provides utility methods for file operations.
 */
public class FileUtil {
	/**
     * Saves the given content to the specified file.
     * @param fileName the name of the file to save the content to
     * @param content the content to save to the file
     * @throws IOException if an I/O error occurs while writing to the file
     */
   public static void saveToFile(String fileName, String content) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        fileWriter.write(content);
        fileWriter.close();
    }
}
