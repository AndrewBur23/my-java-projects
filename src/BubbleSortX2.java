import java.util.Scanner;
import java.util.Arrays;

public class BubbleSortX2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Сколько чисел будет в вашем массиве? ");
        int size = input.nextInt();
        int[] arr = new int[size];
        System.out.println("Вводите числа через пробел или Enter: ");
        for (int a = 0; a < size; a++) {
            arr[a] = input.nextInt();
        }


        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                int weightJ;
                if (arr[j] % 2 != 0) {
                    weightJ = arr[j] * 2;
                } else {
                    weightJ = arr[j];
                }
                int weightNext;
                if (arr[j + 1] % 2 != 0) {
                    weightNext = arr[j + 1] * 2;
                } else {
                    weightNext = arr[j + 1];
                }
                if (weightJ > weightNext) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println("Результат: " + Arrays.toString(arr));
    }
}