package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class FileUtils {

    public static boolean fileExists(String fileInputName) {

        if (!new File(fileInputName).exists()) {
            System.out.println("El archivo [" + fileInputName + "] no existe o su direccion no es valida");
            return false;
        }

        return true;
    }

    public static String getCleanedProgramName(String filename) {

        System.out.println("filename " + filename);
        String cleanName = "";

        String[] dirs = filename.split("/");
        cleanName = dirs[dirs.length - 1];

        String[] nameParts = cleanName.split("\\.");
        cleanName = nameParts[0];

        return cleanName;
    }

    public static void savePlainTextFile(String filename, String content) {

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), "ASCII"))) {
            writer.write(content);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        System.out.println("\n#File saved [" + filename + "]");
    }

    public static String readPlainTextFile(String filenamePath) {

        try {
            try (BufferedReader br = new BufferedReader(new FileReader(filenamePath))) {
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();

                while (line != null) {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    line = br.readLine();
                }
                return sb.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}
