package oop.io;

import java.io.*;
import java.nio.file.*;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class InputOutput {

    public static void copySingleFile(File original, File newFile) {
        byte[] buffer = new byte[8192];
        int bytesRead;

        try (FileInputStream fis = new FileInputStream(original);
             FileOutputStream fos = new FileOutputStream(newFile)) {

            while ((bytesRead = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
            System.out.println("Copied: " + original.getName() + " to " + newFile.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("There was an error when copying the file: " + e);
        }
    }

    public static void copyFilesByExtension(File sourceDirectory, File targetDirectory, String extension) {
        if (!sourceDirectory.isDirectory()) {
            System.err.println("Source is not a directory: " + sourceDirectory.getAbsolutePath());
            return;
        }
        if (!targetDirectory.exists()) {
            System.out.println(targetDirectory.mkdirs() ? "Directory " + targetDirectory.getAbsolutePath() + " created." : "Directory " + targetDirectory.getAbsolutePath() + " does not exist.");
        }

        if (!targetDirectory.isDirectory()) {
            System.err.println("Target is not a directory: " + targetDirectory.getAbsolutePath());
            return;
        }

        try (Stream<Path> paths = Files.walk(sourceDirectory.toPath())) {
            paths.filter(Files::isRegularFile)
                    .forEach(path -> {
                        String fileName = path.getFileName().toString();
                        if (fileName.endsWith("." + extension)) {
                            File originalFile = path.toFile();
                            File newFile = new File(targetDirectory, fileName);
                            copySingleFile(originalFile, newFile);
                        }
                    });
        } catch (IOException e) {
            System.err.println("Error walking through directory: " + e);
        }
    }

    public static void saveCommonWordsToFile(File firstFile, File secondFile, File outputFile) {
        Set<String> wordsInFirstFile = new HashSet<>();
        Set<String> wordsInSecondFile = new HashSet<>();

        Pattern wordPattern = Pattern.compile("\\b\\w+\\b");

        try (BufferedReader firstBR = new BufferedReader(new FileReader(firstFile))) {
            String line;
            while ((line = firstBR.readLine()) != null) {
                Matcher matcher = wordPattern.matcher(line.toLowerCase());
                while (matcher.find()) {
                    wordsInFirstFile.add(matcher.group());
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading first file: " + e);
            return;
        }

        try (BufferedReader secondBR = new BufferedReader(new FileReader(secondFile))) {
            String line;
            while ((line = secondBR.readLine()) != null) {
                Matcher matcher = wordPattern.matcher(line.toLowerCase());
                while (matcher.find()) {
                    wordsInSecondFile.add(matcher.group());
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading second file: " + e);
            return;
        }

        Set<String> tempWords = new HashSet<>(wordsInFirstFile);
        tempWords.retainAll(wordsInSecondFile);
        Set<String> commonWords = new HashSet<>(tempWords);


        // Записываем общие слова в выходной файл
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
            for (String word : commonWords) {
                bw.write(word);
                bw.newLine();
            }
            System.out.println("Common words saved to: " + outputFile.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error writing common words to output file: " + e);
        }
    }
}