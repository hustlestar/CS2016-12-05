package by.it.makstra.lesson05;

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
    private class Point implements Comparable<Point>{
        int x;
        int index;

        public Point(int x, int index) {
            this.x = x;
            this.index = index;
        }

        @Override
        public int compareTo(Point o) {
            //подумайте, что должен возвращать компаратор
            if(x != o.x) return x-o.x;
            else return(index - o.index);
        }

        @Override
        public String toString() {
            String p = "x:"+x+" ,ind:"+index;
            return p;
        }
    }


    int[] getAccessory2(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //тут реализуйте логику задачи с применением быстрой сортировки

        //реализованное мною решение предполагает следующее:
        //Создаём массив точек, в который закладываем точки начала отрезка(присваиваем таким точкам индекс 0),
        //точки конца отрезков(присваиваем таким точкам индекс m+1, где m-число точек, принадлежность которых надо найти),
        // и непосредственно сами точки, принадлежность которых надо найти. Индекс таких точек находися в пределах [1,m]
        // в зависимости от порядкового номера.
        //Так сделано для удобства поиска этих точек после того, как массив будет отсортирован по
        //возрастанию, ведь в качестве ответа нам надо дать количество пересечений с отрезком неотсортированной точки.

        //число отрезков отсортированного массива
        int n = scanner.nextInt();

        //число точек
        int m = scanner.nextInt();

        //массив точек
        Point[] points = new Point[2 * n + m];

        //массив ответов
        int[] result = new int[m];

        //читаем сами отрезки
        for (int i = 0; i < 2 * n; i++) {
            //читаем начало отрезка
            points[i++] = new Point(scanner.nextInt(), 0);
            //читаем конец отрезка
            points[i] = new Point(scanner.nextInt(), m+1);
        }
        //читаем точки
        for (int i = 2 * n; i < (2 * n + m); i++) {
            points[i] = new Point(scanner.nextInt(), i-2*n+1);
        }

        //Arrays.sort(points);//вместо использования статического метода класса Arrays напишем свой(реализация ниже)
        qSortUp(points,0,2*n+m-1);

        for (Point x:points){
            System.out.println(x);}

        //Теперь за один проход по массиву точек мы можем дать ответ по этой задаче.
        // Индексы помогут нам определить зарактер точки: Входная, выходная или искомая.
        // Если точка входная counter++, если выходная counter--, если искомая то заносим
        // в массив ответов result в ячейку "index-1"(-1 т.к. во время вычислений произошло
        // смещение массива на единицу) текущее значение counter.

        int counter = 0;

        for (int i = 0; i < (2 * n + m); i++) {
            if (points[i].index == 0) counter++;
            else
            if (points[i].index > m) counter--;
            else {
                result[points[i].index-1] = counter;
            }
        }
        return result;
    }

    private void swap (Point[] a, int i, int j)
    {
        Point t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    //Реализация быстрой сортировки:
    public Point[] qSortUp(Point p[], int l, int r) {
        while(l<r)
        {
            int mid[] = partition(p,l,r);
            qSortUp(p,l,mid[0]-1);
            l = mid[1]+1;
        }
        return p;
    }

    private int[] partition(Point[] p, int l, int r)
    {
        Random rnd = new Random();
        int random = l+rnd.nextInt(r-l);
        int pivot = l;
        int pivotEnd = l;
        swap(p, pivot, random);
        for(int i = l+1; i<=r;i++)
        {
            if(p[i].compareTo(p[pivot])<=0) {
                pivotEnd++;
                swap(p,i,pivotEnd);
                if(p[pivotEnd].compareTo(p[pivot])<0)
                {
                    swap(p,pivotEnd,pivot);
                    pivot++;
                }
            }

        }
        int part[] = {pivot, pivotEnd};
        return part;
    }


    //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelov/lesson05/dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result=instance.getAccessory2(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
