import java.util.Scanner;
import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Сколько чисел будет в вашем массиве? ");
        int size = input.nextInt();

        int[] array = new int[size];

        System.out.println("Вводите числа через пробел или Enter:");
        for (int a = 0; a < size; a++) {
            array[a] = input.nextInt();
        }

        for (int b = 0; b < array.length - 1; b++) { // проходим по массиву (количество)
            for (int c = 0; c < array.length - 1 - b; c++) { // сравниваем соседей
                if (array[c] > array[c + 1]) { // если левое больше правого
                    int yacheika = array[c]; // кидаем левое в ячейку
                    array[c] = array[c + 1]; // правое вперед
                    array[c + 1] = yacheika; // левое вытащили и в конец
                }
            }
        }

        System.out.println("Отсортированный массив: " + Arrays.toString(array));

        input.close();

    }
}

// -1 не проверяем ячейку которой нет
//-b не проверяем уже отсортированные в конце числа