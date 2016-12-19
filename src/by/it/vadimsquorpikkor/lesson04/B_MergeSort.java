package by.it.vadimsquorpikkor.lesson04;

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

        //mergeSort(a, 0, a.length);

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return mergeSort(a, 0, a.length-1);
    }

    int[] merge(int[] a, int start, int middle, int end) {
        int endOfA = middle;
        int[] temp = new int[end - start + 1];//[end - start+1]

        for (int k = start; k<=end; k++) {
            if (middle>end||(start<endOfA)&&a[start] <= a[middle]) {
                temp[k] = a[start];
                start++;
            } else {
                temp[k] = a[middle];
                middle++;
            }
        }
        return temp;
    }

    int[] mergeSort(int[] a, int start, int end) {
        if (end - start <= 1) {
            return a;
        }
        int middle = (start + end) / 2;
            /*if ((end-middle <= 1)&&(middle-start <= 1)) {//middle???
                return a;
            }*/
                mergeSort(a, start, middle);
                mergeSort(a, middle+1, end);

                a = merge(a, start, middle+1, end);
            return a;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/vadimsquorpikkor/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        //long startTime = System.currentTimeMillis();
        int[] result=instance.getMergeSort(stream);
        //long finishTime = System.currentTimeMillis();
        for (int index:result){
            System.out.print(index+" ");
        }
    }


}
