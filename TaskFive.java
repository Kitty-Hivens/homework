package oop.stream.util;

import oop.stream.entity.ProgrammingLanguage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class TaskFive {
    // Утилитарный метод: проверяет, содержит ли каталог более N txt-файлов
    private static boolean hasMoreThanNTxtFiles(Path directory, int n) {
        if (!Files.isDirectory(directory)) {
            return false;
        }
        try (Stream<Path> files = Files.list(directory)) {
            // Считаем количество txt-файлов и сравниваем с n
            long txtCount = files
                    .filter(Files::isRegularFile)
                    .filter(p -> p.getFileName().toString().toLowerCase().endsWith(".txt"))
                    .count();
            return txtCount > n;
        } catch (IOException e) {
            // Игнорируем недоступные или несуществующие каталоги
            return false;
        }
    }

    public static Optional<Path> findFirstDirectoryWithManyTxtFiles(String filePathWithDirs, int minTxtFiles) throws IOException {
        // 1. Создание потока путей из файла
        try (Stream<String> lines = Files.lines(Paths.get(filePathWithDirs))) {
            return lines
                    .map(Paths::get) // 2. Преобразование строк в Path
                    // 3. Фильтрация и поиск: ищем первый каталог с нужным количеством файлов
                    .filter(path -> hasMoreThanNTxtFiles(path, minTxtFiles))
                    .findFirst(); // 4. Возвращаем первый найденный
        }
    }

    public static Optional<ProgrammingLanguage> findLanguageByDifficulty(
            List<ProgrammingLanguage> languages,
            ProgrammingLanguage.Difficulty targetDifficulty) {

        return languages.stream()
                // 1. Фильтрация: оставляем языки с заданной сложностью
                .filter(lang -> lang.difficulty() == targetDifficulty)
                // 2. Поиск: возвращаем любой (первый) подходящий элемент
                .findAny();
    }
}
