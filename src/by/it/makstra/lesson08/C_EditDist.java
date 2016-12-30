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

        //для информативности выводим получившийся массив
        for(int[] x: D)
        {
            for(int y: x)
            {System.out.print(y+" ");}
            System.out.println();
        }

        //начиная отсюда,поэтапно записываем в sb ответы
        StringBuilder sb = new StringBuilder("");
        int i = first.length;
        int j = second.length;
        while(i>0 || j>0){
            if(j>0 && D[i][j]>D[i][j-1])
            {
                sb.append(","+second[j-1]+"+");
                j--;
                continue;
            }
            if(i>0 && D[i][j]>D[i-1][j])
            {
                sb.append(","+first[i-1]+"-");
                i--;
                continue;
            }
            if(D[i][j]==D[i-1][j-1])
            {
                sb.append(",#");
                i--;
                j--;
                continue;
            }
            if(D[i][j]>D[i-1][j-1])
            {
                sb.append(","+second[j-1]+"~");
                i--;
                j--;
                continue;
            }
        }
        //т.к. ответы мы записывали, начиная с конца, сделаем реверс строки
        return sb.reverse().toString();
    }

    public int min(int a, int b, int c)
    {
        return Math.min(a, Math.min(b,c));
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/makstra/lesson08/dataABC.txt");
        C_EditDist instance = new C_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }
}
