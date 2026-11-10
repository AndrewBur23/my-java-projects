import java.util.Random;
import java.util.Scanner;

public class CredentialSecurityService {
    public static boolean isValid(String password) {
        if (password.length() < 8) {
            return false;
        }
        boolean hasDigit = false;
        boolean hasUpper = false;
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (Character.isDigit(c)) {
                hasDigit = true;
            }
            if (Character.isUpperCase(c)) {
                hasUpper = true;
            }
        }
        return hasDigit && hasUpper;
    }

    public static String generatePassword(int length) {
        if (length < 1) {
            throw new IllegalArgumentException("Password length must be at least 1 symbol!");
        }
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+-=";
        Random random = new Random();
        String generated = "";
        for (int a = 0; a < length; a++) {
            int randomIndex = random.nextInt(chars.length());
            generated += chars.charAt(randomIndex);

        }
        return generated;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("Enter desired password length:");
            int userLength = input.nextInt();
            input.nextLine();
            String generatedPassword = generatePassword(userLength);
            System.out.println("Generated password: " + generatedPassword);
            if (isValid(generatedPassword)) {
                System.out.println("Validation result: Perfect! This password is safe.");
            } else {
                System.err.println("Validation result: Too weak! (Make it longer or check rules).");
            }
        }
    }
}
