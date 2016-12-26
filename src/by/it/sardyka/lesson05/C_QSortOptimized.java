package by.it.sardyka.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Random;
import java.util.Scanner;

/*
Видеорегистраторы и площадь 2.
Условие то же что и в задаче А.

        По сравнению с задачей A доработайте алгоритм так, чтобы
        1) он оптимально использовал время и память:
            - за стек отвечает элиминация хвостовой рекурсии,
            - за сам массив отрезков - сортировка на месте
            - рекурсионные вызовы должны проводится на основе 3-разбиения

        2) при поиске подходящих отрезков для точки реализуйте метод бинарного поиска,
        помните при реализации, что поиск множественный
        (т.е. отрезков, подходящих для точки, может быть много)

    Sample Input:
    2 3
    0 5
    7 10
    1 6 11
    Sample Output:
    1 0 0

*/


public class C_QSortOptimized {

    //отрезок
    /*private class Segment  implements Comparable{
        int start;
        int stop;

        Segment(int start, int stop){
            this.start = start;
            this.stop = stop;
        }

        @Override
        public int compareTo(Object o) {
            //подумайте, что должен возвращать компаратор отрезков
            return 0;
        }
    }*/
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

    private void swap(Point[] a, int i, int j) {
        Point t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private int[] partition3(Point[] a, int low, int high) {
        Random random = new Random();
        int rnd = low + random.nextInt(high - low);
        int pivot = low;
        int pivEnd = low;
        swap(a, pivot, rnd);
        for (int i = low + 1; i <= high; i++) {
            if (a[i].compareTo(a[pivot]) <= 0){
                pivEnd++;
                swap(a, i, pivEnd);
                if (a[pivEnd].compareTo(a[pivot]) < 0) {
                    swap(a, pivEnd, pivot);
                    pivot++;
                }
            }
        }
        return new int[]{pivot, pivEnd};
    }

    private void qsort3(Point[] a, int low, int high) {
        while (low < high) {
            int[] mid = partition3(a, low, high);
            qsort3(a, low, mid[0] - 1);
            low = mid[1] + 1;
        }
    }

    private void qsort3(Point[] points) {
        qsort3(points, 0, points.length);
    }

    int[] getAccessory2(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //число отрезков отсортированного массива
        int n = scanner.nextInt();
        //Segment[] segments=new Segment[n];
        //число точек
        int m = scanner.nextInt();
        Point [] points = new Point[m + 2 * n];
        int[] result = new int[m];
        int ind = 0;
        //читаем сами отрезки
        for (int i = 0; i < n; i++) {
            //читаем начало и конец каждого отрезка
            //segments[i]=new Segment(scanner.nextInt(),scanner.nextInt());
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
        for (int i = 0; i < n; i++) {
            points[ind++] = new Point(scanner.nextInt(), i);
        }
        //тут реализуйте логику задачи с применением быстрой сортировки
        //в классе отрезка Segment реализуйте нужный для этой задачи компаратор
        //qsort3(points);
        int CountSegment = 0;
        /*for (Point p : points) {
            if (p.index < 0) {
                CountSegment++;
            } else if (p.index > m) {
                CountSegment--;
            } else {
                result[p.index] = CountSegment;
            }
        }*/
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelov/lesson05/dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result = instance.getAccessory2(stream);
        for (int index : result) {
            System.out.print(index + " ");
        }
    }

}
