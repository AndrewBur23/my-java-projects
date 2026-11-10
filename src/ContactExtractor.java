import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

public class ContactExtractor {

    public static void extractEmails(String rawFile, String resultFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(rawFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(resultFile))) {

            String line;
            int count = 0;
            HashSet<String> uniqueEmails = new HashSet<>();

            while ((line = reader.readLine()) != null) {
                if (line.contains("@")) {
                    uniqueEmails.add(line.trim());
                }
            }

            for (String email : uniqueEmails) {
                writer.write(email);
                writer.newLine();
                count++;
            }

            System.out.println("Parser: Success! Found " + count + " unique emails.");

        } catch (IOException e) {
            System.err.println("Error processing files: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String inputLog = "dirty_chat.txt";
        String outputEmails = "extracted_emails.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(inputLog))) {
            writer.write("10:00 Manager: Hello! Our support is help@company.com"); writer.newLine();
            writer.write("10:05 System: User connected from IP 133.0.0.1"); writer.newLine();
            writer.write("10:12 Client: My personal email is andrey23@mail.ru delete it please"); writer.newLine();
            writer.write("10:15 System: Backup completed successfully."); writer.newLine();
            writer.write("10:18 Client: Duplicate connection from andrey23@mail.ru"); writer.newLine();
            writer.write("10:20 Admin: Send contract to boss@firm.org urgently."); writer.newLine();
        } catch (IOException e) {
            System.err.println("Failed to create test file: " + e.getMessage());
        }

        extractEmails(inputLog, outputEmails);
    }
}



