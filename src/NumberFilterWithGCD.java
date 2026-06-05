import java.util.Scanner;
import java.nio.file.*;
import java.io.IOException;
import java.util.List;
public class NumberFilterWithGCD {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        Path path = Path.of("NumberFilter.txt");
        while (true) {
            System.out.println("Введите шестизначное число: ");
            if (!input.hasNextInt()) {
                System.err.println("Ошибка! Вы ввели не число. Попробуйте снова.");
                input.next();
                continue;
            }
            int num = input.nextInt();
            String str = Integer.toString(Math.abs(num));
            if (str.length() == 6) {
                String[] digits = str.split("");
                int sum1 = Integer.parseInt(digits[0]) + Integer.parseInt(digits[1]) + Integer.parseInt(digits[2]);
                int sum2 = Integer.parseInt(digits[3]) + Integer.parseInt(digits[4]) + Integer.parseInt(digits[5]);
                if (sum1 == sum2) {
                    String text = num + "\n";
                    Files.writeString(path, text,
                            StandardOpenOption.CREATE,
                            StandardOpenOption.APPEND);
                    System.out.println("Счастливое число, записано в файл");
                    System.out.println("Файл находится тут: " + path.toAbsolutePath());
                    continue;
                } else {
                    System.out.println("Обычное число.");
                    continue;
                }
            } else {
                System.err.println("Выход из программы. Считаем НОД...");
                List<String> lines = Files.readAllLines(path);
                int num1 = Integer.parseInt(lines.get(0).trim());
                int num2 = Integer.parseInt(lines.get(1).trim());
                int result = gcd(num1, num2);
                System.out.println("НОД первых двух чисел равен: " + result);
                break;
            }
        }
    }
    public static int gcd(int a, int b) {
        while (b != 0) {
            int tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }
}