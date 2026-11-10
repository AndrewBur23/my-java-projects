import java.util.Scanner;
import java.util.HashMap;

public class InMemoryCache {
    private static HashMap<Integer, String> storage = new HashMap<>();

    public static void saveDate(int id, String data) {
        storage.put(id, data);
        System.out.println("Success: Data saved with ID " + id);
    }

    public static String getData(int id) {
        if (!storage.containsKey(id)) {
            return "Error: Data with ID " + id + " not found!";
        }
        return storage.get(id);
    }

    public static void deleteData(int id) {
        if (!storage.containsKey(id)) {
            System.err.println("Error: Cannot delete. ID " + id + " does not exist.");
            return;
        }
        storage.remove(id);
        System.out.println("Success: Data with ID " + id + " deleted.");
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("--- Simple In-Memory Database Started ---");

        while (true) {
            System.out.println("\nCommands: 1 (Save), 2 (Find), 3 (Delete), 4 (Exit)");
            System.out.print("Choose a command: ");
            String command = input.nextLine();

            switch (command) {
                case "1":
                    System.out.print("Enter unique ID (number): ");
                    int id = Integer.parseInt(input.nextLine());
                    System.out.print("Enter data (text): ");
                    String data = input.nextLine();
                    saveDate(id, data);
                    break;

                case "2":
                    System.out.print("Enter ID to search: ");
                    int searchId = Integer.parseInt(input.nextLine());
                    String result = getData(searchId);
                    if (result.startsWith("Error")) {
                        System.err.println(result);
                    } else {
                        System.out.println("Found data: " + result);
                    }
                    break;

                case "3":
                    System.out.print("Enter ID to delete: ");
                    int deleted = Integer.parseInt(input.nextLine());
                    deleteData(deleted);
                    break;

                case "4":
                    System.out.println("Stopping Database... GoodBye!");
                    input.close();
                    return;

                default:
                    System.out.println("Unknown command! Please enter a number from 1 to 4.");
                    break;
            }
        }
    }
}