package by.it.du4.lesson05;

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

    //отрезок
    private class Point implements Comparable<Point> {
        int x;
        int index;
        int p;

        Point(int x, int index) {
            this.x = x;
            this.index = index;
            //тут вообще-то лучше доделать конструктор на случай если
            //концы отрезков придут в обратном порядке
        }

        @Override
        public int compareTo(Point o) {
            return x - o.x;
        }
    }

    int[] getAccessory(InputStream stream) throws FileNotFoundException {
        Scanner sc = new Scanner(stream);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] result = new int[m];
        Point[] points = new Point[2 * n + m];


        for (int i = 0; i < 2 * n; i++) {
            int start = sc.nextInt();
            int finish = sc.nextInt();
            if (start < finish) {
                points[i] = new Point(start, -1);
                points[++i] = new Point(finish, points.length);
            } else {
                points[i] = new Point(finish, -1);
                points[++i] = new Point(start, points.length);
            }
        }
        int l = 0;
        for (int i = 2 * n; i < 2 * n + m; i++) {
            points[i] = new Point(sc.nextInt(), l++);
        }
        qSort(points);
        int camNo = 0;
        for (Point point : points) {
            if (point.index < 0) {
                camNo++;
            } else if (point.index == points.length) {
                camNo--;
            } else {
                result[point.index] = camNo;
            }
        }
        return result;
    }

    private void qSort(Point[] a) {
        quickSort(a, 0, a.length - 1);
    }

    private static void quickSort(Point[] array, int low, int high) {
        if (array == null || array.length == 0) {
            return;
        }

        if (low >= high) {
            return;
        }

        int middle = low + (high - low) / 2;
        Point pivot = array[middle];

        int i = low, j = high;
        while (i <= j) {
            while (array[i].x < pivot.x) {
                i++;
            }

            while (array[j].x > pivot.x) {
                j--;
            }
            if (i <= j) {
                Point temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }
        if (low < j)
            quickSort(array, low, j);

        if (high > i)
            quickSort(array, i, high);
    }

    private int[] numbers;
    private int number;

    public void sort(int[] values) {
        if (values == null || values.length == 0) {
            return;
        }
        this.numbers = values;
        number = values.length;
        quickSort(0, number - 1);
    }


    private void quickSort(int low, int high) {
        int i = low, j = high;
        int pivot = numbers[low + (high - low) / 2];
        while (i <= j) {
            while (numbers[i] < pivot) {
                i++;
            }
            while (numbers[j] > pivot) {
                j--;
            }
            if (i <= j) {
                exchange(i, j);
                i++;
                j--;
            }
        }
        if (low < j)
            quickSort(low, j);
        if (i < high)
            quickSort(i, high);
    }

    private void exchange(int i, int j) {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/du4/lesson05/dataA.txt");
        A_QSort instance = new A_QSort();
        int[] result = instance.getAccessory(stream);
        for (int index : result) {
            System.out.print(index + " ");
        }
    }

}
