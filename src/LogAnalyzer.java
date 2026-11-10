import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class LogAnalyzer {

    public static void analyzelogs(String serverLog, String reportFile, String keyword) {

        try (BufferedReader reader = new BufferedReader(new FileReader(serverLog));
             BufferedWriter writer = new BufferedWriter(new FileWriter(reportFile))) {

            String line;
            int errorCount = 0;

            while ((line = reader.readLine()) != null) {
                if (line.contains(keyword)) {
                    writer.write(line);
                    writer.newLine();
                    errorCount++;
                }
            }
            System.out.println("Analysis complete! Found " + errorCount + " critical events with keyword: " + keyword);

        } catch (IOException e) {
            System.err.println("Error reading or writing log files: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String logFile = "server.log";
        String reportFile = "security_report.txt";

        java.io.File fullPath = new java.io.File(logFile);
        System.out.println("-> PATH: Files are located here: " + fullPath.getAbsolutePath());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile))) {
            writer.write("19:00 [INFO] Server started successfully."); writer.newLine();
            writer.write("19:01 [WARNING] High memory usage detected."); writer.newLine();
            writer.write("19:02 [ATTACK] Brute-force password attempt from IP 192.168.1.50"); writer.newLine();
            writer.write("19:03 [INFO] User logged in."); writer.newLine();
            writer.write("19:04 [ATTACK] SQL Injection detected in login form."); writer.newLine();

            System.out.println("Test server.log created successfully!");
        } catch (IOException e) {
            System.err.println("Failed to create test logs: " + e.getMessage());
        }

        System.out.println("Starting Cyber Security Shield...");
        analyzelogs(logFile, reportFile, "ATTACK");
    }
}


