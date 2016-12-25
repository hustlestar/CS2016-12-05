package by.it.poznyakbogdan.lesson05;


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


    int[] getAccessory2(InputStream stream) throws FileNotFoundException {
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
            if (point1 > point2) {
                int tmp = point1;
                point1 = point2;
                point2 = tmp;
            }
            points[i * 2] = new Point(point1, -1);
            points[i * 2 + 1] = new Point(point2, points.length);
        }
        //читаем точки
        for (int i = 0; i < m; i++) {
            points[n * 2 + i] = new Point(scanner.nextInt(), i);
        }
        for (int i = 0; i < points.length; i++) {
            System.out.println(points[i].x + " " + points[i].index);
        }
        //тут реализуйте логику задачи с применением быстрой сортировки
        //в классе отрезка Segment реализуйте нужный для этой задачи компаратор
        quickSort3(points);
        int camQty = 0;
        for (int i = 0; i < points.length; i++) {
            int index = points[i].index;
            if (index == -1) {
                camQty++;
            } else {
                if (index == points.length) {
                    camQty--;
                } else {
                    result[index] = camQty;
                }
            }
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    private void quickSort3(Point[] points) {
        qSort3(points, 0, points.length);
    }

    private void qSort3(Point[] points, int start, int end) {
        while (start < end) {
            int[] divider = divider(points, start, end);
            qSort3(points, start, divider[0] - 1);
            start = divider[1] + 1;
        }
    }

    private int[] divider(Point[] points, int start, int end) {
        int tmp = start + new Random().nextInt(end - start);
        int divStart = start;
        int divEnd = start;
        changePoint(points, divStart, tmp);
        for (int i = start + 1; i < end; i++) {
            if (points[i].compareTo(points[divStart]) <= 0) {
                divEnd++;
                changePoint(points, i, divEnd);
                if (points[divEnd].compareTo(points[divStart]) < 0) {
                    changePoint(points, divStart, divEnd);
                    divStart++;
                }
            }
        }
        return new int[]{divStart, divEnd};
    }

    private void changePoint(Point[] points, int index1, int index2) {
        Point tmp = points[index1];
        points[index1] = points[index2];
        points[index2] = tmp;
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