package by.it.makstra.lesson04;

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
        return mergeSort(a);
    }

    public static int[] mergeSort(int[] a)
    {
        if(a.length<=1) return a;
        int m = a.length/2;
        int[] a1 = Arrays.copyOfRange(a,0,m);
        int[] a2 = Arrays.copyOfRange(a,m,a.length);
        return merge(mergeSort(a1), mergeSort(a2));
    }

    public static int[] merge(int[] b1, int[] b2)
    {
        int n = b1.length+b2.length;
        int[] b = new int[n];
        int i1=0;
        int i2=0;
        for(int i=0;i<n;i++)
        {
            if(i1==b1.length)
            {
                b[i] = b2[i2++];
            }
            else if(i2==b2.length)
            {
                b[i] = b1[i1++];
            }
            else
            {
                if(b1[i1]<b2[i2])
                {
                    b[i]=b1[i1++];
                }
                else
                    {
                        b[i]=b2[i2++];
                    }
            }
        }
        return b;
    }
    //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/makstra/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        //long startTime = System.currentTimeMillis();
        int[] result=instance.getMergeSort(stream);
        //long finishTime = System.currentTimeMillis();
        for (int index:result){
            System.out.print(index+" ");
        }
    }


}
