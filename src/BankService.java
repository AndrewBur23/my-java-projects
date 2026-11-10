import java.util.HashMap;
import java.util.Scanner;

public class BankService {

    private static HashMap<Integer, Integer> accounts = new HashMap<>();

    public static void transferMoney (int fromID, int toID, int amount) {

        if (!accounts.containsKey(fromID)) {
            System.err.println("Error: Sender not found!");
            return;
        }

        if (!accounts.containsKey(toID)) {
            System.err.println("\nError: Receiver not found!");
            return;
        }

        if (amount <= 0) {
            System.err.println("\nError: Transfer amount must be greater than 0!");
            return;
        }

        int senderBalance = accounts.get(fromID);
        accounts.put(fromID, senderBalance - amount);
        accounts.put(toID, accounts.get(toID) + amount);
        System.out.println("Success! Transferred " + amount + " EUR.");
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("--- Bank Service Started ---");

        while (true) {
            System.out.println("\nCommands: 1 (Open Account), 2 (Transfer Money), 3 (View balance), 4 (Exit)");
            System.out.print("Choose command: ");
            String command = input.nextLine();

            switch (command) {
                case "1":
                    System.out.print("Create unique Account ID (number): ");
                    int newId = Integer.parseInt(input.nextLine());
                    System.out.print("Enter initial balance in EUR: ");
                    int initialBalance = Integer.parseInt(input.nextLine());

                    accounts.put(newId, initialBalance);
                    System.out.println("Success! Account " + newId + " opened with " + initialBalance + " EUR.");
                    break;

                case "2":
                    System.out.print("Enter sender ID: ");
                    int from = Integer.parseInt(input.nextLine());
                    System.out.print("Enter receiver ID: ");
                    int to = Integer.parseInt(input.nextLine());
                    System.out.print("Enter amount in EUR: ");
                    int cash = Integer.parseInt(input.nextLine());


                    transferMoney(from, to, cash);
                    break;

                case "3":
                    System.out.print("Enter Account ID to check balance: ");
                    int checkId = Integer.parseInt(input.nextLine());

                    if (accounts.containsKey(checkId)) {
                        System.out.println("Account " + checkId + " balance: " + accounts.get(checkId) + " EUR");
                    } else {
                        System.err.println("Error: Account not found!");
                    }
                    break;


                case "4":
                    System.out.println("Stopping bank service... Goodbye!");
                    input.close();
                    return;

                default:
                    System.out.println("Unknown command! Please choose 1, 2, 3 or 4.    ");
                    break;
            }
        }
    }
}
