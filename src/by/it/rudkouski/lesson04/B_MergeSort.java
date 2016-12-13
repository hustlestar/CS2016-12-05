package by.it.rudkouski.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

/*
Реализуйте сортировку слиянием для одномерного массива.
Сложность алгоритма должна быть не хуже, чем O(n log n)

Первая строка содержит число 1<=n<=10000,
вторая - массив A[1…n], содержащий натуральные числа, не превосходящие 10E9.
Необходимо отсортировать полученный массив.

Sample Input:
5
2 3 9 2 9
Sample Output:
2 2 3 9 9
*/
public class B_MergeSort {

    int[] getMergeSort(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        //размер массива
        int n = scanner.nextInt();
        //сам массива
        int[] a = new int[n];
        for (int i = 1; i <= n; i++) {
            a[i - 1] = scanner.nextInt();
        }
        // тут ваше решение (реализуйте сортировку слиянием)
        // https://ru.wikipedia.org/wiki/Сортировка_слиянием
        for (int k = 1; k <= n; k *= 2) {
            for (int i = 0; i < n; i += 2 * k) {
                int[] tmp1;
                if (i + k > n) {
                    tmp1 = Arrays.copyOfRange(a, i, n);
                } else {
                    tmp1 = Arrays.copyOfRange(a, i, i + k);
                }
                int[] tmp2 = null;
                if ((i + 2 * k) <= n) {
                    tmp2 = Arrays.copyOfRange(a, i + k, i + 2 * k);
                } else {
                    if (i + k < n) {
                        tmp2 = Arrays.copyOfRange(a, i + k, n);
                    }
                }
                a = mergeSort(a, i, tmp1, tmp2);
            }
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return a;
    }

    int[] mergeSort(int[] result, int index, int[] a, int[] b) {
        if (a == null || b == null) {
            return result;
        }
        int indexA = 0;
        int indexB = 0;
        for (int i = 0; i < a.length + b.length; i++) {
            if (indexA >= a.length) {
                result[index + i] = b[indexB];
                indexB++;
            } else {
                if (indexB >= b.length) {
                    result[index + i] = a[indexA];
                    indexA++;
                } else {
                    if (a[indexA] <= b[indexB]) {
                        result[index + i] = a[indexA];
                        indexA++;
                    } else {
                        result[index + i] = b[indexB];
                        indexB++;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelov/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        long startTime = System.currentTimeMillis();
        int[] result = instance.getMergeSort(stream);
        long finishTime = System.currentTimeMillis();
        for (int index : result) {
            System.out.print(index + " ");
        }
        System.out.println();
        System.out.println("time: " + (finishTime - startTime));
    }
}
