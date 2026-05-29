import java.time.DateTimeException;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.Period;
public class DateOfBirthProcessor {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("Введите ваш день рождения: (1-31) ");
            int day = input.nextInt();
            System.out.println("Введите ваш месяц рождения: (01-12) ");
            int month = input.nextInt();
            System.out.println("Введите ваш год рождения: ");
            int year = input.nextInt();
            try {
                LocalDate birthDay = LocalDate.of(year, month, day);
                LocalDate currentDate = LocalDate.now();
                Period age = Period.between(birthDay, currentDate );
                System.out.println("Ваш точный возраст: ");
                System.out.println(age.getYears() + " лет");
                System.out.println(age.getMonths() + " месяцев");
                System.out.println(age.getDays() + " дней");
                break;
            } catch (DateTimeException e) {
                System.err.println("Ошибка! Введена несуществующая дата рождения");
            }
        }
        input.close();
    }
}