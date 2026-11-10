public class FibonacciMemoization {
    public static long[] cache;

    public static void main(String args[]) {
        int n = 59;
        long result = optimizedFibonacci(n);
        System.out.println("Число Фибоначчи " + n + " - это " + result);
    }

    public static long optimizedFibonacci(int n) {
        cache = new long[n + 1];
        return fibonacci(n);
    }

    public static long fibonacci(int n) {
        if (n <= 1) return n;
        if (cache[n] != 0) {
            return cache[n];
        }

        cache[n] = fibonacci(n - 1) + fibonacci(n - 2);

        return cache[n];
    }
}
