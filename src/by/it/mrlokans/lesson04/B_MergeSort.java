package by.it.mrlokans.lesson04;

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

    public static int[] mergeSort(int[] a){
        // recursion exit condition
        if (a.length <= 1){
            return a;
        }

        // splitting array in half
        int[] leftHalf = new int[a.length / 2];
        int[] rightHalf = new int[a.length - a.length / 2];

        // Copy left array elements
        System.arraycopy(a, 0, leftHalf, 0, leftHalf.length);
        // Copy right array elements
        System.arraycopy(a, 0, rightHalf, 0, rightHalf.length);

        mergeSort(leftHalf);
        mergeSort(rightHalf);

        merge(leftHalf, rightHalf, a);
        return a;
    }

    public static void merge(int[] left, int[] right, int[] merged){
        int leftIndex = 0, rightIndex = 0;
        int resultIndex = 0;

        while(leftIndex < left.length && rightIndex < right.length){
            if (left[leftIndex] < right[rightIndex]){
                merged[resultIndex] = left[leftIndex];
                leftIndex++;
            } else {
                merged[resultIndex] = rightIndex;
                rightIndex++;
            }
            resultIndex++;
        }
        System.arraycopy(left, leftIndex, merged, resultIndex, left.length - leftIndex);
        System.arraycopy(right, rightIndex, merged, resultIndex, right.length - rightIndex);
    }

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




        mergeSort(a);

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return a;
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
