public class StringTruncator {
    public static void main(String[] args) {
        String originalText = "Hello, world!";
        int targetLength = 5;
        String resultText = truncateString(originalText, targetLength);
        System.out.println(resultText);
    }
    public static String truncateString(String str, int length) {
        if (str == null || length < 0) {
            return "";
        }
        if (str.length() <= length) {
            return str;
        }
        return str.substring(0, length);
    }
}
