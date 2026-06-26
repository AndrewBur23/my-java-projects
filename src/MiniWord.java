public class MiniWord {
    public static void main(String[] args) {
        String source = "молоко";
        String target = "лом";
        for (char c : target.toCharArray()) {
            // Поочередно удаляем по одной букве из target
            source = source.replaceFirst(String.valueOf(c), "");
        }
        // Если длины совпали, значит все буквы нашлись
        System.out.println(source.length() == "молоко".length() - target.length());
    }
}