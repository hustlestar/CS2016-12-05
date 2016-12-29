package by.it.poznyakbogdan.lesson08;

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
        int n = one.length();
        int m = two.length();

        char[] str1 = new char[n];
        char[] str2 = new char[m];

        for (int i = 0; i < str1.length; i++) {
            str1[i] = one.charAt(i);
        }

        for (int i = 0; i < str2.length; i++) {
            str2[i] = two.charAt(i);
        }

        int[][] data_dist = new int[n + 1][m + 1];

        for (int i = 0; i <= str1.length; i++){
            data_dist[i][0] = i;
        }

        for (int i = 0; i <= str2.length; i++){
            data_dist[0][i] = i;
        }


        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                if (str1[i - 1] == str2[j - 1]){
                    data_dist[i][j] = data_dist[i - 1][j - 1];
                    System.out.printf(data_dist[i][j] + ", ");
                }else {
                    data_dist[i][j] = get_min(data_dist[i - 1][j], data_dist[i][j - 1], data_dist[i - 1][j - 1]) + 1;
                    System.out.printf(data_dist[i][j] + ", ");
                }
            }
            System.out.println();
        }

        int result = data_dist[n][m];
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    int get_min(int i, int j, int k){
        int res = 0;
        if (i <= j && i <= k){
            res = i;
        }else if(j <= i && j <= k ){
            res = j;
        }else {
            res = k;
        }
        return res;
    }



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
