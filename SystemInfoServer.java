package oop.net;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.net.InetSocketAddress;
import java.util.concurrent.atomic.AtomicInteger;

public class SystemInfoServer {

    private static final AtomicInteger requestCount = new AtomicInteger(0);

    public static void main(String[] args) throws IOException {
        int port = 8000;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/", new SystemInfoHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("Server running on http://localhost:" + port);
    }

    static class SystemInfoHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            int requestNumber = requestCount.incrementAndGet();

            OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();

            long totalPhysicalMemory = -1;


            if (osBean instanceof com.sun.management.OperatingSystemMXBean sunOsBean) {
                totalPhysicalMemory = sunOsBean.getTotalMemorySize() / 1024 / 1024;
            }

            String systemInfo = String.format(
                    "Request number: %d%n" +
                            "OS: %s %s%n" +
                            "Java: %s%n" +
                            "Available processors: %d%n" +
                            "Total physical memory: %d MB%n",
                    requestNumber,
                    System.getProperty("os.name"),
                    System.getProperty("os.version"),
                    System.getProperty("java.version"),
                    Runtime.getRuntime().availableProcessors(),
                    totalPhysicalMemory
            );

            exchange.sendResponseHeaders(200, systemInfo.getBytes().length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(systemInfo.getBytes());
            }
        }
    }
}
