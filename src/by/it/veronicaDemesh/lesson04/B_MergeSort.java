package by.it.veronicaDemesh.lesson04;

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
        int[] a=new int[n];
        for (int i = 1; i <= n; i++) {
            a[i-1] = scanner.nextInt();
        }
        // тут ваше решение (реализуйте сортировку слиянием)
        // https://ru.wikipedia.org/wiki/Сортировка_слиянием
        return sort(a);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
    }
    int[] sort(int[] a) {
        int begin = 0;
        int end = a.length;
        if (end <= 1){
            return a;
        }
        int middle = (begin + end) / 2;
        int [] part1 = sort(Arrays.copyOfRange(a, begin, middle));
        int [] part2 = sort(Arrays.copyOfRange(a, middle, end));
        a = mergeAndSort(part1, part2);
        return a;
    }
    int[] mergeAndSort(int[] part1, int[] part2) {
        int end1 = part1.length;
        int end2 = part2.length;
        int b1 = 0;
        int b2 = 0;
        int end = end1 + end2;
        int[] merged = new int[end];
        for (int i = 0; i < end; i++) {
            if (b2 < end2 && b1 < end1) {
                if (part1[b1] > part2[b2]){
                    merged[i] = part2[b2];
                    b2++;
                }else {
                    merged[i] = part1[b1];
                    b1++;
                }
            } else if (b2 < end2) {
                merged[i] = part2[b2];
                b2++;
            } else {
                merged[i] = part1[b1];
                b1++;
            }
        }
        return merged;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/veronicaDemesh/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        //long startTime = System.currentTimeMillis();
        int[] result=instance.getMergeSort(stream);
        //long finishTime = System.currentTimeMillis();
        for (int index:result){
            System.out.print(index+" ");
        }
    }
}
