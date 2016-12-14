package by.it.hustlestar.lesson04;

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
        int left = 0;
        int right = n - 1;

        mergeSort(a, left, right);


        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return a;
    }

    private void mergeSort(int[] a, int left, int right) {
        /*if (right == left) {
            return;
        }
        int mid = left - (left - right) / 2;
        mergeSort(a, left, mid);
        mergeSort(a, mid + 1, right);

        int[] buf = Arrays.copyOf(a, a.length);

        System.arraycopy(a, left, buf, left, right + 1 - left);
        int i = left, j = mid + 1;
        for (int k = left; k <= right; k++) {

            if (i > mid) {
                a[k] = buf[j];
                j++;
            } else if (j > right) {
                a[k] = buf[i];
                i++;
            } else if (buf[j] < buf[i]) {
                a[k] = buf[j];
                j++;
            } else {
                a[k] = buf[i];
                i++;
            }
        }
        */
        if (left + 1 >= right) {
            return;
        }
        int mid = (left + right) / 2;
        mergeSort(a, left, mid);
        mergeSort(a, mid, right);
        merge(a, left, mid, right);
    }

    private void merge(int[] a, int left, int mid, int right) {
        int it1 = 0;
        int it2 = 0;
        int result[] = new int[right - left];

        while (left + it1 < mid && mid + it2 < right) {

            if (a[left + it1] < a[mid + it2]) {
                result[it1 + it2] = a[left + it1];
                it1 += 1;
            } else {
                result[it1 + it2] = a[mid + it2];
                it2 += 1;
            }
        }
        while (left + it1 < mid) {
            result[it1 + it2] = a[left + it1];
            it1 += 1;
        }
        while (mid + it2 < right) {
            result[it1 + it2] = a[mid + it2];
            it2 += 1;
        }
        for (int i = 0; i < it1 + it2; i++) {
            a[left + i] = result[i];
        }
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelov/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        //long startTime = System.currentTimeMillis();
        int[] result = instance.getMergeSort(stream);
        //long finishTime = System.currentTimeMillis();
        for (int index : result) {
            System.out.print(index + " ");
        }
    }


}
