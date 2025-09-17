package oop.io;

import java.io.*;

public class InputOutput {
    public static void copyFileTo(File original, File newFile) {
        try (FileInputStream fis = new FileInputStream(original);
             FileOutputStream fos = new FileOutputStream(newFile)) {

            fos.write(fis.readAllBytes());
        } catch (IOException e) {
            System.err.println("There was an error when copying the file: " + e);
        }
    }

    public static void saveFileBy(File firstFile,  File secondFile, File outputFile) {
        try (BufferedReader firstBR = new BufferedReader(new FileReader(firstFile));
             BufferedReader secondBR = new BufferedReader(new FileReader(secondFile));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile)))) {
            String line;

            while((line = firstBR.readLine()) != null) {
                bw.write(line);
                bw.newLine();
            }

            bw.newLine();
            bw.write("--- Разделитель между файлами ---");
            bw.newLine();
            bw.newLine();

            while((line = secondBR.readLine()) != null) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


