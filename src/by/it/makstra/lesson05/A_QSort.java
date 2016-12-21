package by.it.makstra.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Random;
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


    int[] getAccessory(InputStream stream) throws FileNotFoundException {
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
        qSort(points,0,2*n+m-1);

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

    //Реализация быстрой сортировки:
    public static Point[] qSort(Point p[], int left, int right) {
        int i = left;
        int j = right;
        Random rand = new Random();
        Point x = p[i + rand.nextInt(j - i + 1)];
        while(i<=j)
        {
            while(p[i].compareTo(x)<0)
            {
                i++;
            }
            while(p[j].compareTo(x)>0)
            {
                j--;
            }
            if(i<=j)
            {
                Point t = p[i];
                p[i] = p[j];
                p[j] = t;
                i++;
                j--;
            }
        }
        if (left<j)
        {
            qSort(p, left, j);
        }
        if (i<right)
        {
            qSort(p, i, right);
        }
        return p;
    }


    //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!



    //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelov/lesson05/dataA.txt");
        A_QSort instance = new A_QSort();
        int[] result=instance.getAccessory(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
