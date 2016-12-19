package by.it.makstra.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Первая строка содержит число 1<=n<=10000, вторая - n натуральных чисел, не превышающих 10.
Выведите упорядоченную по неубыванию последовательность этих чисел.
При сортировке реализуйте метод со сложностью O(n)
*/

public class B_CountSort {


    int[] countSort(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //размер массива
        int n = scanner.nextInt();
        int[] points=new int[n];

        //читаем точки
        for (int i = 0; i < n; i++) {
            points[i]=scanner.nextInt();
        }

        return cSort(points);
    }

    //метод для сортировки подсчётом(подходит только для сортировки неотрицательных значений)
    /*
    1.Находим максимальное и минимальное значения массива
    2.Создаём вспомогательный массив counter[], где каждый counter[i]=x; i равно одному из значений исходного массива, из
    которого вычли минимальное значение, а x - количество повторений этого значения.
    3.Далее, используя массив counter[], переписываем исходный массив, делая его отсортированным.
     */
    public static int[] cSort(int[] array) {
        int min, max = min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
            if (array[i] > max) {
                max = array[i];
            }
        }

        int[] counter = new int[max - min + 1];
        for (int i = 0; i < array.length; i++) {
            counter[array[i] - min]++;
        }

        int idCounter = 0;
        for (int i = 0; i < counter.length; i++) {
            for (int j = 0; j < counter[i]; j++) {
                array[idCounter] = i + min;
                idCounter++;
            }
        }
        return array;
    }

    //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream("C:\\Documents and Settings\\traenovichM\\IdeaProjects\\untitled\\src\\countSort\\dataB.txt");
        B_CountSort instance = new B_CountSort();
        int[] result=instance.countSort(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }
}