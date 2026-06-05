import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
public class WordGameInternet {
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        System.out.print("Введите длинное слово: ");
        String sourceWord = inputScanner.nextLine().trim().toLowerCase();
        System.out.println("Подключаемся к базе данных и ищем слова... Подожди секунду.");
        String urlString = "https://githubusercontent.com";
        try {
            URL url = new URL(urlString);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
            int count = 0;
            String dictWord;
            while ((dictWord = reader.readLine()) != null) {
                dictWord = dictWord.trim().toLowerCase();
                if (dictWord.length() < 3 || dictWord.equals(sourceWord)) {
                    continue;
                }
                if (dictWord.length() > sourceWord.length()) {
                    continue;
                }
                String tempSource = sourceWord;
                boolean canMake = true;
                for (int i = 0; i < dictWord.length(); i++) {
                    char letter = dictWord.charAt(i);
                    int index = tempSource.indexOf(letter);
                    if (index != -1) {
                        tempSource = tempSource.substring(0, index) + " " + tempSource.substring(index + 1);
                    } else {
                        canMake = false;
                        break;
                    }
                }
                if (canMake) {
                    System.out.println("- " + dictWord);
                    count++;
                }
            }
            reader.close();
            System.out.println("\nГотово! Всего найдено слов: " + count);
        } catch (Exception e) {
            System.out.println("Ошибка сети! Не удалось загрузить базу слов: " + e.getMessage());
        }
    }
}
