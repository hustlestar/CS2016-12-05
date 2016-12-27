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
    Рекурсивно вычислить расстояние редактирования двух данных непустых строк

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

public class A_EditDist {

    private static int[][] D;
    private char[] first;
    private char[] second;

    int getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        first = one.toCharArray();
        System.out.println(first);
        second = two.toCharArray();
        System.out.println(second);

        int n = first.length+1;
        int m = second.length+1;
        D = new int[n][m];

        for(int i=0; i<n; i++) {
            for (int j = 0; j < m; j++) {
                D[i][j] = Integer.MAX_VALUE;
            }
        }

        int result = EditDistTD(n-1,m-1);

        return result;
    }

    private int EditDistTD(int i,int j)
    {
        if(D[i][j]==Integer.MAX_VALUE)
        {
            if(i==0) D[i][j]=j;
            else if(j==0) D[i][j]=i;
            else
            {
                int ins = EditDistTD(i,j-1)+1;
                int del = EditDistTD(i-1,j)+1;
                int rep;
                if (first[i - 1] == second[j - 1]) {
                    rep = EditDistTD(i-1,j-1);    //совпадение
                } else {
                    rep = EditDistTD(i-1,j-1)+1;  //замена
                }
                D[i][j] = min(ins, del, rep);
            }
        }
        return D[i][j];
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
        A_EditDist instance = new A_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }
}

