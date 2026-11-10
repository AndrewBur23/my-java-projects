import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JsonDataParser {

    public static String cleanText (String text) {
        return text.replace("\"", "").trim();
    }

    public static String parseValue(String line, String key) {

        String[] parts = line.split(",");

        for (String part : parts) {
            if (part.contains("\"" + key + "\"")) {
                int colonIndex = part.indexOf(":");
                String rawValue = part.substring(colonIndex + 1);
                return cleanText(rawValue.replace("}", ""));
            }
        }
        return null;
    }

    public static void printRow(String key, String value) {
        System.out.printf("| %-15s | %-15s |\n", key, value);

    }

    public static void main(String[] args) {
        String fileName = "transactions_array.json";

        try (java.io.BufferedWriter writer = new java.io.BufferedWriter(new java.io.FileWriter(fileName))) {
            writer.write("["); writer.newLine();
            writer.write("  { \"transactionId\": \"TX-001\", \"amountEuro\": \"150\", \"status\": \"SUCCESS\" },"); writer.newLine();
            writer.write("  { \"transactionId\": \"TX-002\", \"amountEuro\": \"2300\", \"status\": \"FAILED\" },"); writer.newLine();
            writer.write("  { \"transactionId\": \"TX-003\", \"amountEuro\": \"4500\", \"status\": \"SUCCESS\" }"); writer.newLine();
            writer.write("]");
        } catch (IOException e) {
            System.err.println("Failed to create array file: " + e.getMessage());
        }

        System.out.println("+-----------------+-----------------+");
        System.out.printf("| %-15s | %-15s |\n", "BANK KEY", "VALUE");
        System.out.println("+-----------------+-----------------+");

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {

                String idVal = parseValue(line, "transactionId");
                String cashVal = parseValue(line, "amountEuro");
                String statusVal = parseValue(line, "status");

                if (idVal != null && cashVal != null && statusVal != null) {
                    printRow("transactionId", idVal);
                    printRow("amountEuro", cashVal + " EUR");
                    printRow("status", statusVal);
                    System.out.println("+-----------------+-----------------+");
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading array: " + e.getMessage());
        }
    }
}
