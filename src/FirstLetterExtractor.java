// массив строк в элементе лежит строка мин 2 символа, надо вывести первые символы каждого элемента
public class FirstLetterExtractor {
    public static void main(String[] args) {
        String[] arr = {"Основной", "Государственный", "Экзамен"};
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i].charAt(0));
        }
    }
}

