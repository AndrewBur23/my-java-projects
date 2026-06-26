import java.util.Arrays;
import java.util.HashSet;
public class UniquePermutations {
    private static HashSet<String> uniquePermutations = new HashSet<>();
    public static void main(String[] args) {
        int[] arr = {2, 2, 3, 5, 8};
        System.out.println("Исходный массив: " + Arrays.toString(arr));
        generate(arr, 0);
        System.out.println("\nВсе уникальные перестановки:");
        for (String a : uniquePermutations) {
            System.out.println(a);
        }
        System.out.println("\nВсего вариантов: " + uniquePermutations.size());
    }
    private static void generate(int arr[], int index) {
        if (index == arr.length - 1) {
            uniquePermutations.add(Arrays.toString(arr));
            return;
        }
        for (int i = index; i < arr.length; i++) {
            swap(arr, index, i);
            generate(arr, index + 1);
            swap(arr, index, i);
        }
    }
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}