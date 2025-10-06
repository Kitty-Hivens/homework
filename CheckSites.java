package oop.net;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class CheckSites {
    public static void main(String[] args) {
        Path file = Path.of("sites.txt");

        try (Stream<String> lines = Files.lines(file)) {
            lines.map(String::trim)
                    .filter(line -> !line.isEmpty())
                    .map(CheckSites::toURI)
                    .forEach(CheckSites::checkSite);
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
        }
    }

    private static URI toURI(String str) {
        try {
            return new URI(str);
        } catch (URISyntaxException e) {
            System.err.println("Неверный URI: " + str);
            return null;
        }
    }

    private static void checkSite(URI uri) {
        if (uri == null) return;
        try {
            HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection();
            connection.setRequestMethod("HEAD");
            connection.setConnectTimeout(3000);
            connection.setReadTimeout(3000);
            int code = connection.getResponseCode();
            System.out.printf("%-40s -> %d %s%n", uri, code, code == 200 ? "OK" : "FAIL");
        } catch (IOException e) {
            System.out.printf("%-40s -> ERROR: %s%n", uri, e.getMessage());
        }
    }
}
