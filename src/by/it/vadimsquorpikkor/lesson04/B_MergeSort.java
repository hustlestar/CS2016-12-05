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

        divideTheArray(a, 0, a.length);






        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return a;
    }

    /*void mergeTheArray(int[] a, int[] b, int[] mergedArray) {
        int i = 0;
        int j = 0;


        for (int k = 0; k<a.length+b.length; k++) {
            if ((a[i] <= b[j] && i<=a.length) || j>b.length) {
                mergedArray[k] = a[i];
                i++;
            } else {
                mergedArray[k] = b[j];
                j++;
            }
        }
    }*/

    void mergeTheArray(int[] a, int start, int middle, int end) {
        int endOfA = middle;
        int[] temp = new int[end - start];

        for (int k = start; k<end; k++) {
            if ((a[start] <= a[middle] && start<=endOfA) || middle>end) {
                temp[k] = a[start];
                start++;
            } else {
                temp[k] = a[middle];
                middle++;
            }
        }
    }
    void divideTheArray(int[] a, int start, int end) {
        int middle;
        while (true) {
            middle = (start + end) / 2;
            divideTheArray(a, start, middle);
            divideTheArray(a, middle+1, end);
            mergeTheArray(a, start, middle, end);
        }

    }


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
