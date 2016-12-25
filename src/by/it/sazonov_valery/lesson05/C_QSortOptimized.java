package by.it.sazonov_valery.lesson05;

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

    //точка!
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


    int[] getAccessory2(InputStream stream) throws FileNotFoundException {
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
            myPoints[d++] = new MyPoint(stop, m + 1);
        }
        for (int i = 0; i < m; i++) {
            int x = scanner.nextInt();
            myPoints[d++] = new MyPoint(x, i);
        }
        //тут реализуйте логику задачи с применением быстрой сортировки
        //в классе отрезка Segment реализуйте нужный для этой задачи компаратор

        myQuickSort3(myPoints, 0, myPoints.length);

        int cam = 0;
        for (int i = 0; i < myPoints.length; i++) {
            if (myPoints[i].index < 0) {
                cam++;
            } else if (myPoints[i].index > m) {
                cam--;
            } else {
                result[myPoints[i].index] = cam;
            }
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    private int[] myPartition3(MyPoint a[], int low, int high) {
        Random random = new Random();
        int intRandom = low + random.nextInt(high - low);
        int m = low;
        int mF = low;

        MyPoint tmp = a[m];
        a[m] = a[intRandom];
        a[intRandom] = tmp;

        for (int i = low + 1; i < high; i++) {
            if (a[i].compareTo(a[m]) <= 0) {
                mF++;
                tmp = a[i];
                a[i] = a[mF];
                a[mF] = tmp;
                if (a[mF].compareTo(a[m]) < 0) {
                    tmp = a[m];
                    a[m] = a[mF];
                    a[mF] = tmp;
                    m++;
                }
            }

        }
        return new int[]{m, mF};
    }

    private void myQuickSort3(MyPoint a[], int low, int high) {
        while (low < high) {
            int[] middle = myPartition3(a, low, high);
            myQuickSort3(a, low, middle[0] - 1);
            low = middle[1] + 1;
        }
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
