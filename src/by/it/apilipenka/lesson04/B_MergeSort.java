package by.it.apilipenka.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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


        a = mergeSort(a);

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return a;
    }

    private int[] merge(int[] a, int[] b) {
        int[] c = new int[a.length + b.length];

        int i = 0;
        int j = 0;
        int k = 0;

        while (i < a.length && j < b.length) {
            if (a[i] > b[j]) {
                c[k] = b[j];
                j++;

            } else {
                c[k] = a[i];
                i++;
            }
            k++;
        }
        while (i < a.length) {
            c[k] = a[i];
            i++;
            k++;
        }
        while (j < b.length) {
            c[k] = b[j];
            j++;
            k++;
        }
        return c;
    }


    private int[] mergeSort(int[] a) {
        if (a.length == 1) return a;
<<<<<<< HEAD
        if (a.length == 2) {
            if (a[0] > a[1]) {
                int temp = a[0];
                a[0] = a[1];
                a[1] = temp;
            }
            return a;
        }
=======
>>>>>>> origin/master
        int[] a1 = new int[(int) Math.floor(a.length / 2)];
        int i = 0;
        while (i < a.length && i < a1.length) {
            a1[i] = a[i++];
        }
        int[] a2 = new int[a.length - (int) Math.floor(a.length / 2)];
        int k = 0;
        while (i < a.length) {
            a2[k++] = a[i++];
        }
        return merge(mergeSort(a1), mergeSort(a2));
    }

}
