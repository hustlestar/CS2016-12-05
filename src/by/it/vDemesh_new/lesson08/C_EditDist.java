package by.it.vDemesh_new.lesson08;

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
    Итерационно вычислить алгоритм преобразования двух данных непустых строк
    Вывести через запятую редакционное предписание в формате:
     операция("+" вставка, "-" удаление, "~" замена, "#" копирование)
     символ замены или вставки

    Sample Input 1:
    ab
    ab
    Sample Output 1:
    #,#,

    Sample Input 2:
    short
    ports
    Sample Output 2:
    ~p,-h,#,#,5+s,

    Sample Input 3:
    distance
    editing
    Sample Output 2:
    +e,#,#,-,#,~i,#,-c,~g,


    P.S. В литературе обычно действия редакционных предписаний обозначаются так:
    - D (англ. delete) — удалить,
    + I (англ. insert) — вставить,
    ~ R (replace) — заменить,
    # M (match) — совпадение.
*/


public class C_EditDist {

    String getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        char [] word1 = one.toCharArray();
        char [] word2 = two.toCharArray();
        int[][] dist = new int[word1.length+1][word2.length+1];

        for (int i = 0; i <= word1.length; i++) {
            dist[i][0] = i;
        }
        for (int j = 0; j <= word2.length; j++) {
            dist[0][j] = j;
        }

        for (int i = 1; i <= word1.length; i++) {
            for (int j = 1; j <= word2.length; j++) {
                if (i == 0) {
                    dist[i][j] = j;
                } else if (j == 0) {
                    dist[i][j] = i;
                } else {
                    int dif = (word1[i - 1] == word2[j - 1]) ? 0 : 1;
                    int ins = dist[i - 1][j] + 1;
                    int del = dist[i][j - 1] + 1;
                    int rep = dist[i - 1][j - 1] + dif;
                    dist[i][j] = Math.min(Math.min(ins, del), rep);
                }
            }
        }
        String result = "";
        int i = word1.length;
        int j = word2.length;
        while (i>0 || j>0){
            char action = '#';
            int step = dist[i][j];
            if (i>0 && j>0 && dist[i-1][j-1]< step){
                step = dist[i-1][j-1];
                action = '~';
            }
            else if (i>0 && dist[i-1][j]< step){
                step = dist[i-1][j];
                action = '-';
            }
            else if ( j>0 && dist[i][j-1]< step){
                step = dist[i][j-1];
                action = '+';
            }
            switch (action){
                case '#': {
                    i--;
                    j--;
                    result = String.format("%s,%s", action, result);
                    break;
                }
                case '~': {
                    i--;
                    j--;
                    result = String.format("%s%s,%s", action, word2[j], result);
                    break;
                }
                case '-': {
                    i--;
                    result = String.format("%s%s,%s", action, word1[i], result);
                    break;
                }
                case '+': {
                    j--;
                    result = String.format("%s%s,%s", action, word2[j], result);
                    break;
                }
            }
        }
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/vDemesh_new/lesson08/dataABC.txt");
        C_EditDist instance = new C_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }

}
