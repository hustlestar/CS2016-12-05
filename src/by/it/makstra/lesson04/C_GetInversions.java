package by.it.makstra.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

/*
Рассчитать число инверсий одномерного массива.
Сложность алгоритма должна быть не хуже, чем O(n log n)
Первая строка содержит число 1<=n<=10000,
вторая - массив A[1…n], содержащий натуральные числа, не превосходящие 10E9.
Необходимо посчитать число пар индексов 1<=i<j<n, для которых A[i]>A[j]A[i]>A[j].
    (Такая пара элементов называется инверсией массива.
    Количество инверсий в массиве является в некотором смысле
    его мерой неупорядоченности: например, в упорядоченном по неубыванию
    массиве инверсий нет вообще, а в массиве, упорядоченном по убыванию,
    инверсию образуют каждые (т.е. любые) два элемента.
    )
Sample Input:
5
2 3 9 2 9
Sample Output:
2
Головоломка (т.е. не обязательно).
Попробуйте обеспечить скорость лучше, чем O(n log n) за счет многопоточности.
Докажите рост производительности замерами времени.
Большой тестовый массив можно прочитать свой или сгенерировать его программно.
*/


public class C_GetInversions {

    private static int inversions;

    int calc(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!
        //размер массива
        int n = scanner.nextInt();
        //сам массив
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!     тут ваше решение   !!!!!!!!!!!!!!!!!!!!!!!!

        mergeSort(a);


        return inversions;
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
                    inversions += (b1.length-1);
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
        InputStream stream = new FileInputStream(root + "by/it/a_khmelov/lesson04/dataC.txt");
        C_GetInversions instance = new C_GetInversions();
        //long startTime = System.currentTimeMillis();
        int result = instance.calc(stream);
        //long finishTime = System.currentTimeMillis();
        System.out.print(result);
    }
}
