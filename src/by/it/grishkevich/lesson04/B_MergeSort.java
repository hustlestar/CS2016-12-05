package by.it.grishkevich.lesson04;

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
            a[i-1] = scanner.nextInt();
        }
        // тут ваше решение (реализуйте сортировку слиянием)
        // https://ru.wikipedia.org/wiki/Сортировка_слиянием

        int fold = 1;
        int change;
        int newElement;
        int[] b;
        while (fold < n) {
            change = 0;
            while (change < n) {
                if (change + fold >= n) break;

                if (change + fold * 2 > n){
                    newElement = n - (change + fold);
                } else {
                    newElement = fold;
                }
                b = mergeArrays(Arrays.copyOfRange(a, change, change + fold),
                        Arrays.copyOfRange(a, change + fold, change + fold + newElement));
                for (int i = 0; i < fold + newElement; ++i)
                    a[change + i] = b[i];
                change += fold * 2;
            }
            fold *= 2;
        }
        return a;
    }

    private static int[] mergeArrays(int[] array_1, int[] array_2) {
        int j = 0, k = 0;
        int[] result = new int[array_1.length + array_2.length];
        for (int i = 0; i < (array_1.length + array_2.length); i++) {
            if (k < array_2.length && j < array_1.length) {
                if (array_1[j] > array_2[k]) {
                    result[i] = array_2[k++];
                }
                else {
                    result[i] = array_1[j++];
                }
            } else if (k < array_2.length) {
                result[i] = array_2[k++];
            } else {
                result[i] = array_1[j++];
            }
        }
        return result;
    }
    //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelov/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        //long startTime = System.currentTimeMillis();
        int[] result=instance.getMergeSort(stream);
        //long finishTime = System.currentTimeMillis();
        for (int index:result){
            System.out.print(index+" ");
        }
    }
}