import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

public class DdosGuard {

    static class TokenBucket {

        private final int maxTokens;
        private int currentTokens;

        public TokenBucket(int maxTokens) {
            this.maxTokens = maxTokens;
            this.currentTokens = maxTokens;
        }

        public boolean tryConsume() {
            if (currentTokens > 0) {
                currentTokens--;
                return true;
            }
            return false;
        }

        public void refill() {
            currentTokens++;
            if (currentTokens > maxTokens) {
                currentTokens = maxTokens;
            }
        }
    }

    static class RateLimiter {

        private final HashMap<String, TokenBucket> buckets = new HashMap<>();
        private final int maxTokens;

        public RateLimiter(int maxTokens) {
            this.maxTokens = maxTokens;
        }

        public boolean isAllowed(String ip) {
            if (!buckets.containsKey(ip)) {
                buckets.put(ip, new TokenBucket(maxTokens));
            }
            TokenBucket bucket = buckets.get(ip);
            return bucket.tryConsume();
        }

        public void refillAll() {
            for (TokenBucket bucket : buckets.values()) {
                bucket.refill();
            }
        }
    }

    public static void main(String[] args) {
        RateLimiter limiter = new RateLimiter(5);
        Thread refillThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    limiter.refillAll();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        refillThread.setDaemon(true);
        refillThread.start();

        Scanner scanner = new Scanner(System.in);

        System.out.println("NoSQL Rate Limiter Security Guard started.");
        System.out.println("Commands: enter valid IPv4 to test, or type 'exit' to quit");
        System.out.print("Enter IP address -> ");

        while (true) {
            String ip = scanner.nextLine().trim();

            String ipPattern = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
            if (!Pattern.matches(ipPattern, ip)) {
                System.err.println("\n[ERROR] Invalid IP format! Please enter a valid IPv4 (e.g. 192.168.0.1)");
                continue;
            }

            if (ip.equalsIgnoreCase("exit")) { break; }
            if (limiter.isAllowed(ip) == true) {
                System.out.println("[ALLOWED] Request processed for IP: " + ip);
            } else {
                System.out.println("[REJECTED] Too Many Requests for IP: " + ip);
            }
            System.out.print("Enter IP address -> ");
        }
    }
}

