import java.util.Scanner;
public class BaseConverter {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("Введите число для перевода (или exit для выхода): ");
            String userInput = input.next();
            if (userInput.equalsIgnoreCase("exit")) {
                System.out.println("Программа завершена.");
                break;
            }
            int num;
            try {
                num = Integer.parseInt(userInput);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Вы ввели не число! Попробуйте еще раз.");
                continue;
            }
            System.out.println("Введите основание степени исчисления для перевода: ");
            int base = input.nextInt();
            if (base < 2 || base > 16) {
                System.err.println("Неверное основание: " + base + ". Должно быть от 2 до 16");
                continue;
            }
            String result = Integer.toString(num, base);
            System.out.println("Результат перевода: " + result.toUpperCase());
        }
        input.close();
    }
}