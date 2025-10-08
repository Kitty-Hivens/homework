package oop.stream.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class PartitionCollectorTask {

    /**
     * Собирает четные и нечетные числа в две строки, разделенные ";".
     * @param numbers Список целых чисел.
     * @return Map<Boolean, String>, где true - четные, false - нечетные.
     */
    public static Map<Boolean, String> partitionNumbersToStrings(List<Integer> numbers) {
        return numbers.stream()
                .collect(Collectors.partitioningBy(
                        n -> n % 2 == 0, // Предикат: true, если число четное

                        // Downstream Collector: собирает числа в строку,
                        // предварительно преобразуя их в String
                        Collectors.mapping(
                                Object::toString,
                                Collectors.joining(";")
                        )
                ));
    }
}

class FileClassifier {

    public enum FileSize {
        SMALL, MEDIUM, LARGE, UNKNOWN
    }

    // Определяет категорию размера файла на основе его длины в байтах
    private static FileSize classifyBySize(Path path) {
        try {
            if (Files.isDirectory(path)) {
                return FileSize.UNKNOWN; // Исключаем каталоги
            }
            long size = Files.size(path);

            if (size < 1024) { // Меньше 1 KB
                return FileSize.SMALL;
            } else if (size < 1024 * 1024) { // Меньше 1 MB
                return FileSize.MEDIUM;
            } else { // 1 MB и более
                return FileSize.LARGE;
            }
        } catch (IOException e) {
            return FileSize.UNKNOWN;
        }
    }

    /**
     * Классифицирует файлы в каталоге по размеру.
     * @param directoryPath Путь к каталогу.
     * @return Map<FileSize, List<Path>>, сгруппированные файлы.
     */
    public static Map<FileSize, List<Path>> classifyFilesBySzie(String directoryPath) throws IOException {
        Path dir = Paths.get(directoryPath);

        try (Stream<Path> paths = Files.list(dir)) {
            return paths
                    // 1. Фильтрация: Оставляем только файлы (исключаем подкаталоги)
                    .filter(Files::isRegularFile)

                    // 2. Группировка: Используем Collectors.groupingBy
                    .collect(Collectors.groupingBy(
                            FileClassifier::classifyBySize // Классификатор
                    ));
        }
    }
}