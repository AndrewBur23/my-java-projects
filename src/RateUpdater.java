import java.util.Scanner;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class RateUpdater implements Runnable {

    private String currencyPair;

    public RateUpdater(String currencyPair){
        this.currencyPair = currencyPair;
    }

    public void run() {
        while (true) {
            try {
                HttpClient client = HttpClient.newHttpClient();
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

                double price = Double.parseDouble(priceStr);

                System.out.printf("[ASYNC] %s price updated: %.2f\n", currencyPair, price);
                Thread.sleep(2000);
            }

            catch (Exception e) {
                System.err.println("[NET ERROR] Connection failed...n j");
            }
        }
    }

    public static void main(String[] args) {
        RateUpdater btcUpdater = new RateUpdater("BTC/EUR");
        new Thread(btcUpdater).start();

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("[MAIN] Main thread is free! You can type commands here...");
            String mainMessage = scanner.nextLine();
            try {
                Thread.sleep(4000);
            }
            catch (Exception e) {
                System.out.println("Main thread interrupted!");
                break;
            }
        }
    }
}
