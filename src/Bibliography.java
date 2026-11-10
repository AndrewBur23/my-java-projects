import java.util.*;

public class Bibliography {
    public static void main(String[] args) {
        ArrayList<String> books = new ArrayList<>();
        books.add("Пушкин: Капитанская дочка");
        books.add("Толстой: Война и Мир");
        books.add("Пушкин: Евгений Онегин");
        books.add("Лермонтов: Мцыри");
        books.add("Толстой: Анна Каренина");
        books.add("Лермонтов: Герой нашего времени");
        ArrayList<String> authors = new ArrayList<>();
        authors.add("Пушкин");
        authors.add("Лермонтов");
        authors.add("Толстой");
        for (String a : authors) {
            System.out.println("Книги " + a + ": ");
            for (String b : books) {
                if (b.contains(a)) {
                    System.out.println("- " + b);
                }
            }
        }
    }
}
// ArrayList: Список литературы