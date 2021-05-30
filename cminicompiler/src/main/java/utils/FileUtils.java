package utils;

import java.io.File;

public class FileUtils {

    public static boolean fileExists(String fileInputName) {

        if (!new File(fileInputName).exists()) {
            System.out.println("El archivo [" + fileInputName + "] no existe o su direccion no es valida");
            return false;
        }

        return true;
    }
}
