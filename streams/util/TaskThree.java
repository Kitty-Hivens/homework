package oop.stream.util;

import oop.stream.entity.Artist;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TaskThree {

    /** * Утилитарный метод для проверки доступности URL.
     * Считается доступным, если код ответа 2xx или 3xx.
     */
    private static boolean isUrlAvailable(String urlString) {
        try {
            URL url = new URI(urlString).toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");
            connection.setConnectTimeout(3000);
            connection.setReadTimeout(3000);

            int responseCode = connection.getResponseCode();
            return responseCode >= 200 && responseCode < 400;

        } catch (IOException e) {
            return false;
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    /** 1) Выделение доступных URL-адресов из файла. */
    public static List<String> getAvailableUrls(String filePath) throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            return lines
                    .filter(TaskThree::isUrlAvailable)
                    .collect(Collectors.toList());
        }
    }

    /** 2) Создание списка из первых трех песен (по алфавиту) на основе массива исполнителей. */
    public static List<String> getTopThreeSongs(Artist[] artists) {
        return Arrays.stream(artists)
                // Разворачиваем поток объектов Artist в поток строк (песен)
                .flatMap(artist -> artist.getSongs().stream())
                .sorted()
                .limit(3)
                .collect(Collectors.toList());
    }

    /** 3) Выделение из каталога списка файлов с расширением «txt». */
    public static List<String> getTxtFiles(String directoryPath) throws IOException {
        Path dir = Paths.get(directoryPath);

        // Используем Files.list() для получения Stream<Path>
        try (Stream<Path> paths = Files.list(dir)) {
            return paths
                    .filter(Files::isRegularFile)
                    // Проверяем, что имя файла оканчивается на ".txt" (без учета регистра)
                    .filter(p -> p.getFileName().toString().toLowerCase().endsWith(".txt"))
                    .map(p -> p.getFileName().toString())
                    .collect(Collectors.toList());
        }
    }

}
