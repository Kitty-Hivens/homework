package oop.stream.util;

import oop.stream.entity.Cat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TaskOne {

    public static String getLongestName(List<Cat> cats) {

        return cats.stream()
                .map(Cat::getName)
                .max(Comparator.comparingInt(String::length))
                .orElse("");
    }

    public static List<Integer> getSortedOdd(List<Integer> list) {
        return list.stream()
                .filter(integer -> integer % 2 != 0)
                .sorted()
                .collect(Collectors.toList());

    }

    public static Path getLargestFile(Path directory) throws IOException {
        try (var stream = Files.list(directory)) {
            return stream
                    .filter(Files::isRegularFile) // только файлы
                    .max(Comparator.comparingLong(path -> {
                        try {
                            return Files.size(path);
                        } catch (IOException e) {
                            return 0L;
                        }
                    }))
                    .orElse(null); // если файлов нет
        }
    }


}
