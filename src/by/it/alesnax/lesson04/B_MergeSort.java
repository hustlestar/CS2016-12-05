package by.it.alesnax.lesson04;

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
@SuppressWarnings("ALL")
public class B_MergeSort {

    int[] getMergeSort(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner sc = new Scanner(stream);

        //!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!

        //размер массива
        int n = sc.nextInt();
        //сам массива
        int[] a = new int[n];
        for (int i = 1; i <= n; i++) {
            a[i - 1] = sc.nextInt();
        }

        // https://ru.wikipedia.org/wiki/Сортировка_слиянием
        a = merge(a);

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return a;
    }

    public int[] merge(int[] ar) {
        int[] left;
        int[] right;
        int[] res = new int[ar.length];
        if (ar.length > 1) {
            left = new int[ar.length / 2];
            right = new int[ar.length - left.length];
            System.arraycopy(ar, 0, left, 0, left.length);
            System.arraycopy(ar, left.length, right, 0, right.length);
            left = merge(left);
            right = merge(right);
            res = mergesort(left, right);

        } else {
            return ar;
        }
        return res;
    }

    public int[] mergesort(int[] left, int[] right) {
        int[] res = new int[left.length + right.length];
        int j = 0;
        int k = 0;
        for (int i = 0; i < res.length; i++) {
            if (j < left.length) {
                if (k < right.length) {
                    if (left[j] <= right[k]) {
                        res[i] = left[j];
                        j++;
                    } else {
                        res[i] = right[k];
                        k++;
                    }
                } else {
                    res[i] = left[j];
                    j++;
                }
            } else {
                res[i] = right[k];
                k++;
            }
        }
        return res;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/alesnax/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        //long startTime = System.currentTimeMillis();
        int[] result = instance.getMergeSort(stream);
        //long finishTime = System.currentTimeMillis();
        for (int index : result) {
            System.out.print(index + " ");
        }
    }


}
