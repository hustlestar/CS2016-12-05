package by.it.rudkouski.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
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

    private class Point implements Comparable<Point> {
        int x;
        int index;

        public Point(int x, int index) {
            this.x = x;
            this.index = index;
        }

        @Override
        public int compareTo(Point o) {
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
        Point[] points = new Point[m + n * 2];
        int[] result = new int[m];

        //читаем сами отрезки
        for (int i = 0; i < n; i++) {
            //читаем начало и конец каждого отрезка
            int point1 = scanner.nextInt();
            int point2 = scanner.nextInt();
            if (point1 < point2) {
                points[i * 2] = new Point(point1, -1);
                points[i * 2 + 1] = new Point(point2, points.length);
            } else {
                points[i * 2] = new Point(point2, -1);
                points[i * 2 + 1] = new Point(point1, points.length);
            }
        }
        //читаем точки
        for (int i = 0; i < m; i++) {
            points[n * 2 + i] = new Point(scanner.nextInt(), i);
        }
        //тут реализуйте логику задачи с применением быстрой сортировки
        quickSort(points);
        int camQty = 0;
        for (int i = 0; i < points.length; i++) {
            int index = points[i].index;
            if (index == -1) {
                camQty++;
                continue;
            } else {
                if (index == points.length) {
                    camQty--;
                    continue;
                }
            }
            result[index] = camQty;
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    private void quickSort(Point[] points) {
        qSort(points, 0, points.length);
    }

    private void qSort(Point[] points, int start, int end) {
        while (start < end) {
            int divider = divider(points, start, end);
            qSort(points, start, divider - 1);
            start = divider + 1;
        }
    }

    private int divider(Point[] points, int start, int end) {
        Point point = points[start];
        int divider = start;
        Point tmp;
        for (int i = start + 1; i < end; i++) {
            if (points[i].compareTo(point) <= 0) {
                divider++;
                tmp = points[divider];
                points[divider] = points[i];
                points[i] = tmp;
            }
        }
        tmp = points[start];
        points[start] = points[divider];
        points[divider] = tmp;
        return divider;
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
