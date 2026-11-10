import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class AsyncRealMarket {
    public static void main(String[] args) {
        String currencyPair = "BTC/EUR";
        System.out.println("[START] Launching real-time lambda market engine...");

        new Thread(() -> {
            HttpClient client = HttpClient.newHttpClient();
            while (true) {
                try {
                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create("https://coinbase.com"))
                            .GET()
                            .build();

                    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                    String json = response.body();

                    String amountKey = "\"amount\":\"";
                    int startIdx = json.indexOf(amountKey) + amountKey.length();
                    int endIdx = json.indexOf("\"", startIdx);
                    String priceStr = json.substring(startIdx, endIdx);

                    double realPrice = Double.parseDouble(priceStr);

                    System.out.printf("[ASYNC POOL] Real %s rate: %.2f EUR\n", currencyPair, realPrice);
                    Thread.sleep(2000);

                } catch (Exception e) {
                    System.err.println("[NET STATUS] Packet blocked or server lag. Retrying...");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ie) {
                        break;
                    }
                }
            }
        }).start();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("[MAIN] Main thread is free! You can type commands here...");
            String mainMessage = scanner.nextLine();

            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                System.err.println("Main thread interrupted!");
                break;
            }
        }
    }
}


