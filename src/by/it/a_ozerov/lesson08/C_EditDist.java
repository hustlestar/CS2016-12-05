package by.it.a_ozerov.lesson08;

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
    -s,~p,#,#,#,+s,

    Sample Input 3:
    distance
    editing
    Sample Output 2:
    +e,#,#,-s,#,~i,#,-c,~g,


    P.S. В литературе обычно действия редакционных предписаний обозначаются так:
    - D (англ. delete) — удалить,
    + I (англ. insert) — вставить,
    ~ R (replace) — заменить,
    # M (match) — совпадение.
*/

public class C_EditDist {
    char[] a;
    char[] b;
    int [][] d;

    String getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        a = one.toCharArray();
        b = one.toCharArray();
        d = new int [a.length + 1][b.length + 1];

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j <= b.length; j++) {
                d[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i <= a.length; i++) {
            for (int j = 0; j <= b.length; j++) {
                if (i == 0) {
                    d[i][j] = j;
                } else {
                    if (j == 0) {
                        d[i][j] = i;
                    } else {
                        int ins = d[i][j - 1] + 1;
                        int del = d[i - 1][j] + 1;
                        int sub = d[i - 1][j - 1] + ((a[i - 1] == b[j - 1]) ? 0 : 1);
                        d[i][j] = Math.min(ins, Math.min(del, sub));
                    }
                }
            }
        }
        int i = a.length;
        int j = b.length;
        String result = "";

        while (i > 0 || j > 0) {
            char op = '#';
            int curr = d[i][j];
            if (i > 0 && j > 0 && d[i - 1][j - 1] < curr) {
                curr = d[i - 1][j - 1];
                op = '`';
            }
            if (i > 0 && d[i - 1][j - 1] < curr) {
                curr = d[i - 1][j];
                op = '-';
            }
            if (j > 0 && d[i][j - 1] < curr) {
                curr = d[i][j - 1];
                op = '+';
                }

            switch (op) {
                case '#' :
                    i--; j--;
                    result = String.format("%s,%s", op, result);
                    break;
                case '~' :
                    i--; j--;
                    result = String.format("%s%s,%s", op, b[j], result);
                    break;
                case '-' :
                    i--;
                    result = String.format("%s%s,%s", op, a[i], result);
                    break;
                case '+' :
                    j--;
                    result = String.format("%s,%s", op, b[j], result);
                    break;
                }

            }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelov/lesson08/dataABC.txt");
        C_EditDist instance = new C_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }

}
