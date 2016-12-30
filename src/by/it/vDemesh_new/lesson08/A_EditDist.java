package by.it.vDemesh_new.lesson08;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
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
    Sample Output 2:
    5

*/

public class A_EditDist {
    static int [][]dist;
    char [] word1;
    char [] word2;

    int getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        word1 = one.toCharArray();
        word2 = two.toCharArray();

        dist = new int [word1.length+1][word2.length+1];
        for(int [] row : dist) {
            Arrays.fill(row ,Integer.MAX_VALUE);
        }
        int i = word1.length;
        int j = word2.length;
        int result = EditDistTD(i, j);
        return result;
    }

     private int EditDistTD(int i,int j)
     {
         if(dist[i][j]==Integer.MAX_VALUE)
         {
             if(i==0){
                 dist[i][j] = j;
             }
             else if(j==0) {
                 dist[i][j] = i;
             }else{
                 int dif = (word1[i - 1] == word2[j - 1]) ? 0 : 1;
                 int ins = EditDistTD(i,j-1)+1;
                 int del = EditDistTD(i-1,j)+1;
                 int rep = EditDistTD(i-1,j-1) + dif;
                 dist[i][j] = Math.min(Math.min(ins, del), rep);
                    }
                }
         return dist[i][j];
     }
       //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/vDemesh_new/lesson08/dataABC.txt");
        A_EditDist instance = new A_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }
}

