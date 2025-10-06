package oop.net;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtractLinks {
    public static void main(String[] args) {
        String urlString = "https://example.com";
        String outputFile = "links.txt";

        try (HttpClient client = HttpClient.newHttpClient();) {

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlString))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String html = response.body();

            Pattern pattern = Pattern.compile("(?i)<a\\s+[^>]*href\\s*=\\s*['\"]([^'\"]+)['\"]");
            Matcher matcher = pattern.matcher(html);

            URI baseUri = URI.create(urlString);

            try (PrintWriter writer = new PrintWriter(outputFile)) {
                while (matcher.find()) {
                    String link = matcher.group(1);


                    URI resolved = baseUri.resolve(link);
                    writer.println(resolved);
                }
            }

            System.out.println("Ссылки успешно записаны в " + outputFile);

        } catch (IOException | InterruptedException e) {
            System.out.println("Обнаружена ошибка: " + e.getMessage());
        }
    }
}
