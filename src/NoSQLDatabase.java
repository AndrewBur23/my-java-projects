import java.awt.*;
import java.util.HashMap;
import java.util.Scanner;

public class NoSQLDatabase<V> {

    static class StorageEntry<V> {
        V value;
        long expirationTime;

        public StorageEntry(V value, int ttlSeconds) {
            this.value = value;
            this.expirationTime = System.currentTimeMillis() + ttlSeconds * 1000L;
        }

        public boolean isExpired() {
            return System.currentTimeMillis() > expirationTime;
        }
    }

    private final HashMap<String, StorageEntry<V>> storage = new HashMap<>();

    public void put(String key, V value, int ttlSeconds) {
        StorageEntry<V> entry = new StorageEntry<>(value, ttlSeconds);
        storage.put(key, entry);
        System.out.println("[DB] Save: " + key);
    }

    public V get(String key) {
        StorageEntry<V> entry = storage.get(key);
        if (entry == null) {
            return null;
        }
        if (entry.isExpired()) {
            storage.remove(key);
            return null;
        }
        return entry.value;
    }

    public void cleanUp() {
        storage.entrySet().removeIf(entry ->
                entry.getValue().isExpired());
        System.out.println("[CLEANUP] Background memory evacuation completed.");
    }

    public static void main(String[] args) {

        NoSQLDatabase<String> db = new NoSQLDatabase<>();

        Thread cleanerThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(15000);
                    db.cleanUp();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        cleanerThread.setDaemon(true);
        cleanerThread.start();

        Scanner scanner = new Scanner(System.in);
        System.out.println("NoSQL Engine started. " +
                "Commands: put [key] [val] [ttl], get [key], exit");
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("exit")) break;
            String[] tokens = input.split(" ");
            switch (tokens[0]) {
                case "put":
                    db.put(tokens[1], tokens[2], Integer.parseInt(tokens[3]));
                    System.out.println("[CLI] Key saved successfully.");
                    break;

                case "get":
                    String val = db.get(tokens[1]);
                    System.out.println("Result: " + val);
                    break;

                default:
                    System.err.println("Unknown command!");
            }
        }
    }
}