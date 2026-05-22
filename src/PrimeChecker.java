import java.util.Scanner;
public class PrimeChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите число: ");
        int num = scanner.nextInt();
        int i;
        for ( i = 2; i < num; i++) { // проходит, ищет делители числа
            if (num %  i == 0) { // если найден делитель числа
                break; // выход из цикла и сохрание в переменную i делителя
            }
        }
        if (i == num && num > 1 ) {
            System.out.println("Это простое число.");
        } else if (num <= 1) { 
            System.out.println("Это и не простое, и не составное число.");
        } else {
            System.out.println("Это составное число.");
        }
        scanner.close();
    }
}