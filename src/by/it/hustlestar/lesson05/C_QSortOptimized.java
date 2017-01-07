package by.it.hustlestar.lesson05;

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

        Point(int x, int index) {
            this.x = x;
            this.index = index;
        }

        @Override
        public int compareTo(Point o) {
            return x - o.x;
        }
    }

    int[] getAccessory(InputStream stream) {
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        int[] result = new int[m];

        Point[] points = new Point[n + n + m];
        int counter = 0;
        for (int i = 0; i < n; i++) {
            int start = scanner.nextInt();
            int finish = scanner.nextInt();
            if (start > finish) {
                int tmp = start;
                start = finish;
                finish = start;
            }
            points[counter++] = new Point(start, -1);
            points[counter++] = new Point(finish, m + 1);

        }
        for (int i = 0; i < m; i++) {
            points[counter++] = new Point(scanner.nextInt(), i);
            //System.out.println(points[i].x);
        }

        qSort3(points);
        int camNo = 0;
        for (int i = 0; i < points.length; i++) {
            Point point = points[i];
            //System.out.println(point.x+point.index);;
            if (point.index < 0) {
                camNo++;
            } else if (point.index > m) {
                camNo--;
            } else {
                result[point.index] = camNo;
            }
        }
        return result;
    }

    private void qSort3(Point[] a) {
        quickSort(a, 0, a.length - 1);
    }

    private static void quickSort(Point[] arr, int low, int high) {
        if (low < high) {
            int[] m = partition3(arr, low, high);
            quickSort(arr, low, m[0] - 1);
            low = m[1] + 1;
        }
    }

    private static int[] partition3(Point[] arr, int low, int high) {
        Random random = new Random();
        int pivot = low;
        int rand = low + random.nextInt(high - low);
        int pivotEnd = low;
        swap(arr, pivot, rand);
        for (int i = low + 1; i <= high; i++) {
            if (arr[i].compareTo(arr[pivot]) <= 0) {
                pivotEnd++;
                swap(arr, i, pivotEnd);
                if (arr[pivotEnd].compareTo(arr[pivot]) < 0) {
                    swap(arr, pivotEnd, pivot++);
                }
            }
        }
        return new int[]{pivot, pivotEnd};
    }

    private static void swap(Point[] arr, int m, int i) {
        Point temp = arr[i];
        arr[i] = arr[m];
        arr[m] = temp;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelov/lesson05/dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result = instance.getAccessory(stream);
        for (int index : result) {
            System.out.print(index + " ");
        }
    }

}
