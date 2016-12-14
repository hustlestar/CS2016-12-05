package by.it.apilipenka.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelov/lesson04/dataC.txt");
        C_GetInversions instance = new C_GetInversions();
        //long startTime = System.currentTimeMillis();
        long result = instance.calc(stream);
        //long finishTime = System.currentTimeMillis();
        System.out.print(result);
        System.out.println();

        System.out.print(instance.slowGetInversions(new int[]{1, 5, 3, 7, 9, 1}));
        System.out.println();

        Counter counter = new Counter();
        instance.mergeSort(new int[]{1, 5, 3, 7, 9, 1}, counter);
        System.out.print(counter.getCount());
        System.out.println();

        for (int i = 10; i <= 100000; i *= 10) {
            int[] arr = new int[i];
            for (int j = 0; j < arr.length; j++) arr[j] = (int) Math.ceil(Math.random() * 100);
            long startTime = System.currentTimeMillis();
            System.out.print(i + " ");
            System.out.print(instance.slowGetInversions(arr));
            long finishTime = System.currentTimeMillis();
            System.out.print(" " + (finishTime - startTime));


            startTime = System.currentTimeMillis();
            counter = new Counter();
            int[] arr1 = instance.mergeSort(arr, counter);
            finishTime = System.currentTimeMillis();
            System.out.print(" " + counter.getCount());
            System.out.print(" " + (finishTime - startTime));

            System.out.println();
        }


    }

    long calc(InputStream stream) throws FileNotFoundException {
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
        long result = 0;
        //!!!!!!!!!!!!!!!!!!!!!!!!     тут ваше решение   !!!!!!!!!!!!!!!!!!!!!!!!
        result = slowGetInversions(a);

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    long slowGetInversions(int[] arr) {
        int i = 0;
        long k = 0;
        while (i < arr.length) {
            int j = i + 1;
            while (j < arr.length) {
                if (arr[i] > arr[j]) k++;
                j++;
            }
            i++;

        }

        return k;
    }

    private int[] merge(int[] a, int[] b, Counter counter) {
        int[] c = new int[a.length + b.length];

        int i = 0;
        int j = 0;
        int k = 0;

        while (i < a.length && j < b.length) {
            if (a[i] > b[j]) {
                c[k] = b[j];
                j++;
                counter.inc(a.length - i);

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
            //counter.inc();
        }
        return c;
    }


    private int[] mergeSort(int[] a, Counter counter) {
        if (a.length == 1) return a;
        if (a.length == 2) {
            if (a[0] > a[1]) {
                int temp = a[0];
                a[0] = a[1];
                a[1] = temp;
                counter.inc(1);
            }
            return a;
        }
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
        return merge(mergeSort(a1, counter), mergeSort(a2, counter), counter);
    }


}

class Counter {
    long count;

    public Counter() {
        this.count = 0;
    }

    /*public void inc() {
        this.count++;
    }*/

    public void inc(int i) {
        this.count += i;
    }

    public long getCount() {
        return count;
    }

}