import java.util.Scanner;
public class CharMerger {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        theMain_cycle: while (true) {
            StringBuilder chars = new StringBuilder();
            while (true) {
                System.out.println("Введите первый символ (или exit для выхода): ");
                String userInput = input.next();
                if (userInput.equalsIgnoreCase("exit")) {
                    System.out.println("Программа завершена.");
                    break theMain_cycle;
                }
                if (userInput.length() == 1) {
                    chars.append(userInput.charAt(0));
                    break;
                }
                System.err.println("Ошибка! Нужно ввести ровно ОДИН символ.");
            }
            while (true) {
                System.out.println("Введите второй символ: ");
                String userInput = input.next();
                if (userInput.length() == 1) {
                    chars.append(userInput.charAt(0));
                    break;
                }
                System.err.println("Ошибка! Нужно ввести ровно ОДИН символ.");
            }
            while (true) {
                System.out.println("Введите третий символ: ");
                String userInput = input.next();
                if (userInput.length() == 1) {
                    chars.append(userInput.charAt(0));
                    break;
                }
                System.err.println("Ошибка! Нужно ввести ровно ОДИН символ.");
            }
            String result = chars.toString();
            System.out.println("Результат: " + result);
        }
        input.close();
    }
}
