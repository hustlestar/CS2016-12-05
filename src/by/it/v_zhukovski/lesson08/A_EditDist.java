package by.it.v_zhukovski.lesson08;

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
    Sample Output 3:
    5

*/

public class A_EditDist {

    int getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int result = 0;

        int[][] D = new int[one.length()][two.length()];
        for (int i = 0; i < one.length(); i++){
            for (int j = 0; j < two.length(); j++){
                D[i][j] = Integer.MAX_VALUE;
            }
        }

        result = editDist(D, one.length() - 1, two.length() - 1, one, two);

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;

    }


    int editDist (int[][] D, int i, int j, String one, String two){
        int val = D[i][j];
        if (val == Integer.MAX_VALUE){
            if (i == 0) {
                D[i][j] = j;
                val = j;
            }
            else if (j == 0) {
                D[i][j] = i;
                val = i;
            }
            else {
                int ins = editDist(D, i, j -1, one, two) + 1;
                int sub = editDist(D, i- 1, j - 1, one, two) + diff(one.toCharArray()[i], two.toCharArray()[j]);
                int del = editDist(D, i - 1, j, one, two) + 1;
                val =  min(ins, sub, del);
            }

        }
        return val;
    }


    int diff(char A, char B){
        if (A == B) return 0;
        else return 1;
    }

    int min(int ins, int sub, int del){
        int[] m = new int[3];
        m[0] = ins;
        m[1] = sub;
        m[2] = del;
        Arrays.sort(m);
        return m[0];

    }



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

