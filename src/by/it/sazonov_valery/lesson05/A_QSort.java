package by.it.sazonov_valery.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Видеорегистраторы и площадь.
На площади установлена одна или несколько камер.
Известны данные о том, когда каждая из них включалась и выключалась (отрезки работы)
Известен список событий на площади (время начала каждого события).
Вам необходимо определить для каждого события сколько камер его записали.

В первой строке задано два целых числа:
    число включений камер (отрезки) 1<=n<=50000
    число событий (точки) 1<=m<=50000.

Следующие n строк содержат по два целых числа ai и bi (ai<=bi) -
координаты концов отрезков (время работы одной какой-то камеры).
Последняя строка содержит m целых чисел - координаты точек.
Все координаты не превышают 10E8 по модулю (!).

Точка считается принадлежащей отрезку, если она находится внутри него или на границе.

Для каждой точки в порядке их появления во вводе выведите,
скольким отрезкам она принадлежит.
    Sample Input:
    2 3
    0 5
    7 10
    1 6 11
    Sample Output:
    1 0 0

*/

public class A_QSort {

    //точка
    private class MyPoint implements Comparable<MyPoint> {
        int x;
        int index;

        public MyPoint(int x, int index) {
            this.x = x;
            this.index = index;

        }

        @Override
        public int compareTo(MyPoint o) {
            if (x - o.x != 0) {
                return x - o.x;
            } else {
                return index - o.index;
            }
        }
    }


    int[] getAccessory(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //число отрезков отсортированного массива
        int n = scanner.nextInt();

        //число точек
        int m = scanner.nextInt();

        MyPoint[] myPoints = new MyPoint[2 * n + m];
        int[] result = new int[m];
        int d = 0;
        for (int i = 0; i < n; i++) {
            int start = scanner.nextInt();
            int stop = scanner.nextInt();
            if (start > stop) {
                int temp = start;
                start = stop;
                stop = temp;
            }
            myPoints[d++] = new MyPoint(start, -1);
            myPoints[d++] = new MyPoint(stop, myPoints.length);
        }
        for (int i = 0; i < m; i++) {
            int x = scanner.nextInt();
            myPoints[d++] = new MyPoint(x, i);
        }
        //тут реализуйте логику задачи с применением быстрой сортировки
        //в классе отрезка Segment реализуйте нужный для этой задачи компаратор

        myQuickSort(myPoints, 0, myPoints.length);

        int cam = 0;
        for (int i = 0; i < myPoints.length; i++) {
            if (myPoints[i].index < 0) {
                cam++;
            } else if (myPoints[i].index == myPoints.length) {
                cam--;
            } else {
                result[myPoints[i].index] = cam;
            }
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    private int myPartition(MyPoint a[], int low, int high) {
        int m = low;
        MyPoint x = a[low];
        for (int i = low + 1; i < high; i++) {
            if (a[i].compareTo(x) <= 0) {
                m++;
                MyPoint tmp = a[i];
                a[i] = a[m];
                a[m] = tmp;
            }
            MyPoint tmp = a[low];
            a[low] = a[m];
            a[m] = tmp;
        }
        return m;
    }

    private void myQuickSort(MyPoint a[], int low, int high) {
        if (low < high) {
            int m = myPartition(a, low, high);
            myQuickSort(a, low, m - 1);
            myQuickSort(a, m + 1, high);
        }
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelov/lesson05/dataA.txt");
        A_QSort instance = new A_QSort();
        int[] result = instance.getAccessory(stream);
        for (int index : result) {
            System.out.print(index + " ");
        }
    }

}
