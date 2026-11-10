import java.io.FileWriter;

public class AuditLogger {
    private static final String FILE_NAME = "audit.log";
    public static void logAction(String level, String message) {
        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            long timestamp = System.currentTimeMillis();
            String fullLog = "[" + timestamp + "] [" + level + "] " + message + "\n";
            writer.write(fullLog);
        } catch (Exception e) {
            System.out.println("[LOGGER ERROR] Failed to write to audit file: " + e.getMessage());
        }
    }
}
