package oop.net;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class GroupWebServer {

    private static final Group group = new Group("CS101");

    public static void main(String[] args) throws IOException {
        group.addStudent(new Student("John", "Doe", Gender.MAN, 1, "CS101"));
        group.addStudent(new Student("Alice", "Smith", Gender.WOMAN, 2, "CS101"));
        group.addStudent(new Student("Bob", "Johnson", Gender.MAN, 3, "CS101"));


        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/", new MainHandler());
        server.createContext("/action", new ActionHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("Server running at http://localhost:8080");
    }

    // Главная страница: список доступных методов
    static class MainHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {

            String html = "<html><body>" + "<h1>Group: " + group.getGroupName() + "</h1>" +
                    "<h2>Available methods:</h2>" +
                    "<ul>" +
                    "<li><a href='/action?method=listStudents'>List Students</a></li>" +
                    "<li><a href='/action?method=hasDuplicates'>Check for duplicates</a></li>" +
                    "<li><a href='/action?method=groupName'>Get Group Name</a></li>" +
                    "</ul>" +
                    "</body></html>";

            sendResponse(exchange, html);
        }
    }

    // Обработчик выполнения методов
    static class ActionHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            URI requestURI = exchange.getRequestURI();
            Map<String, String> queryParams = parseQuery(requestURI.getQuery());
            String method = queryParams.get("method");

            StringBuilder html = new StringBuilder("<html><body>");
            html.append("<a href='/'>Back to main</a><br><br>");

            try {
                switch (method) {
                    case "listStudents" -> {
                        html.append("<h2>Students:</h2><ul>");
                        for (Student s : group.getStudents()) {
                            html.append("<li>").append(s).append("</li>");
                        }
                        html.append("</ul>");
                    }
                    case "hasDuplicates" -> {
                        boolean duplicates = group.hasEquivalentStudents();
                        html.append("<h2>Has duplicates: ").append(duplicates).append("</h2>");
                    }
                    case "groupName" -> html.append("<h2>Group Name: ").append(group.getGroupName()).append("</h2>");
                    case null, default -> html.append("<h2>Unknown method</h2>");
                }
            } catch (Exception e) {
                html.append("<h2>Error: ").append(e.getMessage()).append("</h2>");
            }

            html.append("</body></html>");
            sendResponse(exchange, html.toString());
        }
    }

    // Вспомогательная функция для отправки ответа
    private static void sendResponse(HttpExchange exchange, String response) throws IOException {
        exchange.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
        exchange.sendResponseHeaders(200, response.getBytes(StandardCharsets.UTF_8).length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes(StandardCharsets.UTF_8));
        }
    }

    // Парсинг query string в Map
    private static Map<String, String> parseQuery(String query) {
        Map<String, String> result = new HashMap<>();
        if (query == null) return result;
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            String[] keyVal = pair.split("=", 2);
            String key = URLDecoder.decode(keyVal[0], StandardCharsets.UTF_8);
            String value = keyVal.length > 1 ? URLDecoder.decode(keyVal[1], StandardCharsets.UTF_8) : "";
            result.put(key, value);
        }
        return result;
    }
}
