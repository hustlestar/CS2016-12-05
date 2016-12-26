package by.it.sardyka.lesson05;

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

    private class Point implements Comparable<Point> {
        int x;
        int index;//-1 start.max stop

        private Point(int x, int index) {
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

    private int partition(Point[] points, int start, int end) {
        Point point = points[start];
        int part = start;
        Point temp;
        for (int i = start + 1; i < end; i++) {
            if (points[i].compareTo(point) <= 0) {
                part++;
                temp = points[part];
                points[part] = points[i];
                points[i] = temp;
            }
        }
        temp = points[start];
        points[start] = points[part];
        points[part] = temp;
        return part;
    }

    private void qSort(Point[] points, int start, int end) {
        if (start < end) {
            int part = partition(points, start, end);
            qSort(points, start, part - 1);
            qSort(points, part + 1, end);
        }
    }

    private void quickSort(Point[] points) {
        qSort(points, 0, points.length);
    }
    //отрезок
   /* private class Segment  implements Comparable{
        int start;
        int stop;

        Segment(int start, int stop){
            this.start = start;
            this.stop = stop;
            //тут вообще-то лучше доделать конструктор на случай если
            //концы отрезков придут в обратном порядке
        }

        @Override
        public int compareTo(Object o) {
            //подумайте, что должен возвращать компаратор отрезков
            return 0;
        }
    }*/


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
        int ind = 0;
        //читаем сами отрезки
        for (int i = 0; i < n; i++) {
            //читаем начало и конец каждого отрезка
            int start = scanner.nextInt();
            int stop = scanner.nextInt();
            if (start > stop) {
                int temp = stop;
                stop = start;
                start = temp;
            }
            points[ind++] = new Point(start, -1);
            points[ind++] = new Point(stop, m + 1);
        }
        //читаем точки
        for (int i = 0; i < m; i++) {
            points[ind++] = new Point(scanner.nextInt(), i);
        }
        //тут реализуйте логику задачи с применением быстрой сортировки
        quickSort(points);
        int CountSegment = 0;
        for (Point p : points) {
            if (p.index < 0) {
                CountSegment++;
            } else if (p.index > m) {
                CountSegment--;
            } else {
                result[p.index] = CountSegment;
            }
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
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
