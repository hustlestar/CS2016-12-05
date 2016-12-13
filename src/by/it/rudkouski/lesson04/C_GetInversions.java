package by.it.rudkouski.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

/*
Рассчитать число инверсий одномерного массива.
Сложность алгоритма должна быть не хуже, чем O(n log n)

Первая строка содержит число 1<=n<=10000,
вторая - массив A[1…n], содержащий натуральные числа, не превосходящие 10E9.
Необходимо посчитать число пар индексов 1<=i<j<n, для которых A[i]>A[j]A[i]>A[j].

    (Такая пара элементов называется инверсией массива.
    Количество инверсий в массиве является в некотором смысле
    его мерой неупорядоченности: например, в упорядоченном по неубыванию
    массиве инверсий нет вообще, а в массиве, упорядоченном по убыванию,
    инверсию образуют каждые (т.е. любые) два элемента.
    )

Sample Input:
5
2 3 9 2 9
Sample Output:
2

Головоломка (т.е. не обязательно).
Попробуйте обеспечить скорость лучше, чем O(n log n) за счет многопоточности.
Докажите рост производительности замерами времени.
Большой тестовый массив можно прочитать свой или сгенерировать его программно.
*/


public class C_GetInversions {

    int calc(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!
        //размер массива
        int n = scanner.nextInt();
        //сам массив
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int result = 0;
        //!!!!!!!!!!!!!!!!!!!!!!!!     тут ваше решение   !!!!!!!!!!!!!!!!!!!!!!!!
        for (int k = 1; k <= n; k *= 2) {
            for (int i = 0; i < n; i += 2 * k) {
                int[] tmp1;
                if (i + k > n) {
                    tmp1 = Arrays.copyOfRange(a, i, n);
                } else {
                    tmp1 = Arrays.copyOfRange(a, i, i + k);
                }
                int[] tmp2 = null;
                if ((i + 2 * k) <= n) {
                    tmp2 = Arrays.copyOfRange(a, i + k, i + 2 * k);
                } else {
                    if (i + k < n) {
                        tmp2 = Arrays.copyOfRange(a, i + k, n);
                    }
                }
                if (tmp1 != null && tmp2 != null) {
                    int indexA = 0;
                    int indexB = 0;
                    for (int z = 0; z < tmp1.length + tmp2.length; z++) {
                        if (indexA >= tmp1.length) {
                            a[i + z] = tmp2[indexB];
                            indexB++;
                        } else {
                            if (indexB >= tmp2.length) {
                                a[i + z] = tmp1[indexA];
                                indexA++;
                            } else {
                                if (tmp1[indexA] <= tmp2[indexB]) {
                                    a[i + z] = tmp1[indexA];
                                    indexA++;
                                } else {
                                    a[i + z] = tmp2[indexB];
                                    indexB++;
                                    result++;
                                }
                            }
                        }
                    }
                }
            }
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelov/lesson04/dataC.txt");
        C_GetInversions instance = new C_GetInversions();
        long startTime = System.currentTimeMillis();
        int result = instance.calc(stream);
        long finishTime = System.currentTimeMillis();
        System.out.println(result);
        System.out.println("time: " + (finishTime - startTime));
    }
}
