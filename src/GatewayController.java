import java.util.Scanner;

public class GatewayController {

    private static final DdosGuard.RateLimiter ddosGuard = new DdosGuard.RateLimiter(5);
    private static final NoSQLDatabase<String> noSQLDatabase = new NoSQLDatabase();

    public static boolean processRequest(String ip, String command) {
        String status = noSQLDatabase.get(ip);
        if (status != null && status.equals("BANNED")) {
            System.out.println("[GATEWAY] ACCESS DENIED. IP IS STILL BANNED IN CACHE: " + ip);
            AuditLogger.logAction("SECURITY_BLOCK", "Blocked repetitive request from banned IP: " + ip);
            return false;
        }
        if (ddosGuard.isAllowed(ip) == false) {
            System.out.println("[GATEWAY REJECTED] IP is spamming: " + ip);
            noSQLDatabase.put(ip, "BANNED", 20);
            AuditLogger.logAction("DDOS_ALERT", "IP exceeded request limits, sent to ban-list: " + ip);
            return false;
        }
        switch (command) {
            case "trade":
                System.out.println("[GATEWAY] Forwarding to Trade...");
                TradingEngine.startOrderGenerator();
                break;
            case "cache_clear":
                noSQLDatabase.cleanUp();
                break;
            default:
                System.out.println("[GATEWAY ERROR] Unknown command!");
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner =  new Scanner(System.in);
        System.out.println("=== API GATEWAY MONOLITH IS RUNNING ===");
        while (true) {
            System.out.print("Enter Request (IP COMMAND) -> ");
            String ipCommand = scanner.nextLine().trim();
            if (ipCommand.equalsIgnoreCase("exit")) {
                break;
            }
            String[] tokens = ipCommand.split(" ");
            if (tokens.length < 2) {
                System.out.println("[GATEWAY ERROR] Syntax error! Use format: IP COMMAND");
                continue;
            }
            processRequest(tokens[0], tokens[1]);
        }
    }
}
