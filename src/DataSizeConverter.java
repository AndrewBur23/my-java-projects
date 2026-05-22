import java.util.Scanner;
public class DataSizeConverter {
    public static void main(String[] args) {
        Scanner input =  new Scanner(System.in);
        while (true) {
            System.out.println("Введите значение в гигабайтах: (или 0 для выхода) ");
            if (!input.hasNextLong()) {
                System.err.println("Ошибка! Введите целое число.");
                input.next();
                continue;
            }
            long gb = input.nextLong();
            if (gb == 0 ) {
                break;
            }
            if (gb < 0 ) {
                System.err.println("Ошибка! Вводите положительное число.");
                continue;
            }
            long mb = gb * 1024L;
            long kb = gb * 1024L * 1024L;
            long b = gb * 1024L * 1024L * 1024L;
            System.out.println("В " + gb + " гигабайтах " + mb + " мегабайт, " + kb + " килобайт и " + b + " байт.");
        }
        input.close();
    }
}