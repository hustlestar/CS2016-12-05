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

        int[][] data_dist = new int[n][m];

        int result = 0;

        result = calc_dist(str1, str2, n, m);

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

    public int calc_dist(char[] s1, char[] s2, int col, int row){
        int result = 0;
        if (col == 0){
            result = row;
        }else if (row == 0){
            result = col;
        }else if(s1[col - 1] == s2[row - 1]){
            result = calc_dist(s1, s2, col - 1, row - 1);
        }else {
            result = get_min(calc_dist(s1, s2, col - 1, row), calc_dist(s1, s2, col , row - 1),
                    calc_dist(s1, s2, col - 1, row - 1)) + 1;
        }
        return result;
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

