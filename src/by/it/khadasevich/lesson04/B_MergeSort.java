

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
        int[] a = new int[n];
        for (int i = 1; i <= n; i++) {
            a[i-1] = scanner.nextInt();
        }
        // тут ваше решение (реализуйте сортировку слиянием)
        // https://ru.wikipedia.org/wiki/Сортировка_слиянием

        int left = 0;
        int right = n - 1;
        if (a.length <= 1){
            return a;
        }
        mergeSort(a,left,right);








        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return a;
    }

    public void mergeSort(int[] a, int left, int right) {
        if (left < right) {
            int middle = left + (right - left) / 2;
            mergeSort(a,left, middle);
            mergeSort(a, middle + 1, right);
            merge(a,left,middle,right);

        }


    }
     public void merge(int[] a, int left, int middle, int right){
         int [] buffer = new int[a.length];
            for (int i = left; i <= right; i++){
                buffer[i] = a[i];
         }

         int i = left;
         int j = middle + 1;
         int k = left;
         while (i <= middle && j <= right){
             if (buffer[i] <= buffer[j]) {
                 a[k] = buffer[i];
                 i++;
             } else {
                 a[k] = buffer[j];
                 j++;
             }
             k++;
         }
         while (i <= middle){
             a[k] = buffer[i];
             k++;
             i++;
         }
     }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/khadasevich/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        //long startTime = System.currentTimeMillis();
        int[] result=instance.getMergeSort(stream);
        //long finishTime = System.currentTimeMillis();
        for (int index:result){
            System.out.print(index+" ");
        }
    } 


}
