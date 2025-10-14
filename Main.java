import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

public class Main {
    public static void main(String[] args) throws IOException {
        // Sunucu 1989 portunda oluşturuluyor
        HttpServer server = HttpServer.create(new InetSocketAddress(1989), 0);

        // "/" yolu ve cevap
        server.createContext("/", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                String cevap = "Merhaba! Sunucu başarıyla çalışıyor 😎";

                // Ayarlama: Türkçe karakterler için UTF-8
                exchange.getResponseHeaders().add("Content-Type", "text/plain; charset=UTF-8");

                exchange.sendResponseHeaders(200, cevap.getBytes("UTF-8").length);
                OutputStream os = exchange.getResponseBody();
                os.write(cevap.getBytes("UTF-8"));
                os.close();
            }
        });

        server.setExecutor(null); // Varsayılan executor kullanılıyor
        server.start();
        System.out.println("Web Sunucu çalışıyor: http://localhost:1989");
    }
}
