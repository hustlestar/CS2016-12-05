package by.it.hustlestar.lesson05;

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
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //число отрезков отсортированного массива
        int n = scanner.nextInt();
        //число точек
        int m = scanner.nextInt();
        int[] result = new int[m];
        Point[] points = new Point[2 * n + m];


        //читаем сами отрезки
        for (int i = 0; i < 2 * n; i++) {
            //читаем начало и конец каждого отрезка
            int start = scanner.nextInt();
            int finish = scanner.nextInt();
            if (start < finish) {
                points[i] = new Point(start, -1);
                points[++i] = new Point(finish, points.length);
            } else {
                points[i] = new Point(finish, -1);
                points[++i] = new Point(start, points.length);
            }
        }
        //читаем точки
        int l = 0;
        for (int i = 2 * n; i < 2 * n + m; i++) {
            points[i] = new Point(scanner.nextInt(), l++);
        }
        //тут реализуйте логику задачи с применением быстрой сортировки
        //в классе отрезка Point реализуйте нужный для этой задачи компаратор
        qSort(points);
        //Arrays.sort(points);
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
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    private void qSort(Point[] a) {
        quickSort(a, 0, a.length - 1);
    }

    private static void quickSort(Point[] arr, int low, int high) {
        if (arr == null || arr.length == 0)
            return;

        if (low >= high)
            return;

        // pick the pivot
        int middle = low + (high - low) / 2;
        Point pivot = arr[middle];

        // make left < pivot and right > pivot
        int i = low, j = high;
        while (i <= j) {
            while (arr[i].x < pivot.x) {
                i++;
            }

            while (arr[j].x > pivot.x) {
                j--;
            }
            if (i <= j) {
                Point temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }
        // recursively sort two sub parts
        if (low < j)
            quickSort(arr, low, j);

        if (high > i)
            quickSort(arr, i, high);
    }

    private int[] numbers;
    private int number;

    public void sort(int[] values) {
        // check for empty or null array
        if (values == null || values.length == 0) {
            return;
        }
        this.numbers = values;
        number = values.length;
        quicksort(0, number - 1);
    }


    private void quicksort(int low, int high) {
        int i = low, j = high;
        // Get the pivot element from the middle of the list
        int pivot = numbers[low + (high - low) / 2];

        // Divide into two lists
        while (i <= j) {
            // If the current value from the left list is smaller then the pivot
            // element then get the next element from the left list
            while (numbers[i] < pivot) {
                i++;
            }
            // If the current value from the right list is larger then the pivot
            // element then get the next element from the right list
            while (numbers[j] > pivot) {
                j--;
            }

            // If we have found a values in the left list which is larger then
            // the pivot element and if we have found a value in the right list
            // which is smaller then the pivot element then we exchange the
            // values.
            // As we are done we can increase i and j
            if (i <= j) {
                exchange(i, j);
                i++;
                j--;
            }
        }
        // Recursion
        if (low < j)
            quicksort(low, j);
        if (i < high)
            quicksort(i, high);
    }

    private void exchange(int i, int j) {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/hustlestar/lesson05/dataA.txt");
        A_QSort instance = new A_QSort();
        int[] result = instance.getAccessory(stream);
        for (int index : result) {
            System.out.print(index + " ");
        }
    }

}
