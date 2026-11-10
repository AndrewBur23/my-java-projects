import java.util.Scanner;

public class EncryptionService {

    public static String encrypt(String text, int key) {
        String result = "";
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            char encryptedChar = (char) (c + key);
            result += encryptedChar;
        }
        return result;
    }

    public static String decrypt(String text, int key) {
        String result = "";
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            char decryptedChar = (char) (c - key);
            result += decryptedChar;
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("--- Crypto Encryption Service Started ---");

        while (true) {
            System.out.println("\nCommands: 1 (Encrypt), 2 (Decrypt), 3 (Exit)");
            System.out.println("Choose a command: ");
            String command = input.nextLine();

            switch (command) {

                case "1":
                    System.out.println("Enter text to encrypt: ");
                    String textToEncrypt = input.nextLine();
                    System.out.println("Enter secret key (number): ");

                    try {
                        int encKey = Integer.parseInt(input.nextLine());
                        System.out.println("Encrypted result: " + encrypt(textToEncrypt, encKey));
                    } catch (NumberFormatException e) {
                        System.err.println("Error: Encryption key must be a number!");
                    }
                    break;

                case "2":
                    System.out.print("Enter text to decrypt: ");
                    String textToDecrypt = input.nextLine();
                    System.out.print("Enter secret key (number): ");

                    try {
                        int encKey = Integer.parseInt(input.nextLine());
                        System.out.println("Decrypted result: " + decrypt(textToDecrypt, encKey));
                    } catch (NumberFormatException e) {
                        System.err.println("Error: Decryption key must be a number!");
                    }
                    break;



                case "3":
                    System.out.println("Stopping service... Goodbye!");
                    input.close();
                    return;

                default:
                    System.out.println("Unknown command! Please choose 1,2 or 3.");
                    break;
            }
        }
    }
}