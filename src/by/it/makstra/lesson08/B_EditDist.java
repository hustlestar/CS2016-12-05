package by.it.makstra.lesson08;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: расстояние Левенштейна
    https://ru.wikipedia.org/wiki/Расстояние_Левенштейна
    http://planetcalc.ru/1721/

Дано:
    Две данных непустые строки длины не более 100, содержащие строчные буквы латинского алфавита.

Необходимо:
    Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ
    Итерационно вычислить расстояние редактирования двух данных непустых строк

    Sample Input 1:
    ab
    ab
    Sample Output 1:
    0

    Sample Input 2:
    short
    ports
    Sample Output 2:
    3

    Sample Input 3:
    distance
    editing
    Sample Output 3:
    5

*/

public class B_EditDist {

    int getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        char[] first = one.toCharArray();
        System.out.println(first);

        char[] second = two.toCharArray();
        System.out.println(second);

        //создаём массив размерностью n*m. n и m(а также индексы ячеек, соответствующие символам в строках)
        //сознательно увеличены на единицу по сравнению с длинами исходных
        //строк, т.к. в качестве начальных значений мы задаём 0-ю строку и 0-й столбец.
        int n = first.length+1;
        int m = second.length+1;
        int[][] D = new int[n][m];

        //задаём начальные значения: 0-е строки и столбцы.
        for(int i=0; i<n; i++) D[i][0]=i;
        for(int j=1; j<m; j++) D[0][j]=j;

        //заполняем массив промежуточных данных(расстояний редактирования)
        for(int i=1;i<n;i++) {
            for (int j = 1; j < m; j++) {
                int ins = D[i][j - 1] + 1; //вставка
                int del = D[i - 1][j] + 1; //удаление
                int rep;                   //замена или совпадение

                if (first[i - 1] == second[j - 1]) {
                    rep = D[i - 1][j - 1];      //совпадение
                } else {
                    rep = D[i - 1][j - 1] + 1;  //замена
                }
                D[i][j] = min(ins, del, rep);
            }
        }

        int result = D[n-1][m-1];

        return result;
    }

    public int min(int a, int b, int c)
    {
        if(a<b && a<c) return a;
        if(b<a && b<c) return b;
        else return c;
    }

    //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelov/lesson08/dataABC.txt");
        B_EditDist instance = new B_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }

}
