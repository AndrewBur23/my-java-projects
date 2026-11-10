public class ParrarelTransactionAgregattor {
    public static void main(String[] args) {
        int data[] = {8431, 12054, 4612, 89302, 7341, 15890, 3245, 67120, 9043, 5112,
                23948, 7654, 11023, 4981, 35670, 8129, 14305, 6219, 95431, 3084,
                18762, 5493, 42109, 7856, 13940, 2671, 58324, 9102, 16453, 3890,
                72145, 6034, 29481, 8710, 10564, 4329, 61903, 7432, 19854, 5217,
                83049, 2156, 14780, 6923, 36541, 8894, 25103, 4762, 53912, 1045};

        int[] result1 = {0};
        int[] result2 = {0};

        System.out.println("[CORE] Starting parallel MapReduce engine...");

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 25; i++) {
                result1[0] += data[i];
            }
        });
        t1.start();

        Thread t2 = new Thread(() -> {
            for (int i = 25; i < 50; i++) {
                result2[0] += data[i];
            }
        });
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.err.println("Error joining threads!");
        }

        int totalSum = result1[0] + result2[0];
        System.out.println("=========================================");
        System.out.printf("[SUCCESS] Total transaction volume: %d RUB\n", totalSum);
        System.out.println("=========================================");
    }
}

