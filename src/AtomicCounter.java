import java.util.concurrent.ForkJoinWorkerThread;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter {
    public static void main(String[] args) {
        AtomicInteger counter = new AtomicInteger(0);

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.incrementAndGet();
            }
        });
        t1.start();

        for (int i = 0; i < 1000; i++) {
            counter.incrementAndGet();
        }

        try {
            t1.join();
        } catch (InterruptedException e) {
            System.err.println("Error joining thread!");
        }

        System.out.printf("[SUCCESS] Safe multithreaded count: %d\n", counter.get());

    }
}
