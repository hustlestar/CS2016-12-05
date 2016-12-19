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
    private class Segment  implements Comparable<Segment>{
        int start;
        int stop;

        Segment(int start, int stop){
            this.start = start;
            this.stop = stop;
            //тут вообще-то лучше доделать конструктор на случай если
            //концы отрезков придут в обратном порядке
        }

        @Override
        public int compareTo(Segment o) {
            //подумайте, что должен возвращать компаратор отрезков
            return Integer.compare(this.start, o.start);
        }
    }


    int[] getAccessory(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //число отрезков отсортированного массива
        int n = scanner.nextInt();
        Segment[] segments=new Segment[n];
        //число точек
        int m = scanner.nextInt();
        int[] points=new int[m];
        int[] result=new int[m];

        //читаем сами отрезки
        for (int i = 0; i < n; i++) {
            //читаем начало и конец каждого отрезка
            segments[i]=new Segment(scanner.nextInt(),scanner.nextInt());
        }
        //читаем точки
        for (int i = 0; i < m; i++) {
            points[i]=scanner.nextInt();
        }
        //тут реализуйте логику задачи с применением быстрой сортировки
        //в классе отрезка Segment реализуйте нужный для этой задачи компаратор

        //Мои комментарии:
        //Реализованное мною решение предполагает следующее:
        //Сортируем массив отрезков segments[] по возрастанию;
        //В качестве сравниваемого значения указываем переменную start;
        //Данная манипуляция позволяет при вводе в наивный код конструкции "if(points[i]<segments[j].start) break;"
        //иногда значительно сократить количество действий над массивами по сравнению с наивным алгоритмом(в зависимости от входных данных),
        //избавив программу от необходимости проверять наличие точки в отрезках, переменная start которых
        //будет однозначно больше переменной start, которая проверялась на условие, т.к. массив отрезков отсортирован
        //по возрастанию

        //Arrays.sort(segments);
        //вместо того, чтобы воспользоваться готовым статическим методом класса Arrays,
        //пишем свой кустарный метод быстрой сортировки qSort

        segments = qSort(segments, 0, n-1);

        for(Segment x:segments)
            System.out.println("["+x.start+" : "+x.stop+"]");

        //часть кода для проверки принадлежности точки отрезкам

        for(int i =0; i<m; i++)
        {
            int count=0;
            for(int j=0;j<n;j++)
            {
                if(segments[j].start<=points[i] && points[i]<=segments[j].stop)
                {
                    count++;
                }
                else if(points[i]<segments[j].start) break;
            }
            result[i] = count;
        }
        return result;
    }

    //Реализация быстрой сортировки:
    public static Segment[] qSort(Segment segs[], int left, int right) {
        int i = left;
        int j = right;
        Random rand = new Random();
        Segment x = segs[i + rand.nextInt(j - i + 1)];
        while(i<=j)
        {
            while(segs[i].compareTo(x)==(-1))
            {
                i++;
            }
            while(segs[j].compareTo(x)==1)
            {
                j--;
            }
            if(i<=j)
            {
                Segment t = segs[i];
                segs[i] = segs[j];
                segs[j] = t;
                i++;
                j--;
            }
        }
        if (left<j)
        {
            qSort(segs, left, j);
        }
        if (i<right)
        {
            qSort(segs, i, right);
        }
        return segs;
    }


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
