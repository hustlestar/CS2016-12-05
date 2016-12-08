package by.it.apilipenka.lesson02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class C_GreedyKnapsack {
    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        String root = System.getProperty("user.dir") + "/src/";
        File f = new File(root + "by/it/apilipenka/lesson02/greedyKnapsack.txt");
        double costFinal = new C_GreedyKnapsack().calc(f);
        long finishTime = System.currentTimeMillis();
        System.out.printf("Общая стоимость %f (время %d)", costFinal, finishTime - startTime);
    }

    private void quickSort(Item[] items, int start, int end) {
        if (start >= end) {
            return;
        }

        int i = start;
        int j = end;
        int m = i - (i - j) / 2;

        while (i < j) {
            while (i < m && items[i].compareTo(items[m]) <= 0) {
                i++;
            }
            while (j > m && items[j].compareTo(items[m]) >= 0) {
                j--;
            }
            if (i < j) {
                Item temp = items[i];
                items[i] = items[j];
                items[j] = temp;
                if (i == m)
                    m = j;
                else if (j == m)
                    m = i;
            }
        }
        quickSort(items, start, m);
        quickSort(items, m + 1, end);

    }

    double calc(File source) throws FileNotFoundException {
        Scanner input = new Scanner(source);
        int n = input.nextInt();      //сколько предметов в файле
        int W = input.nextInt();      //какой вес у рюкзака
        Item[] items = new Item[n];   //получим список предметов
        for (int i = 0; i < n; i++) { //создавая каждый конструктором
            items[i] = new Item(input.nextInt(), input.nextInt());
        }
        //покажем предметы
        for (Item item : items) {
            System.out.println(item);
        }


        System.out.printf("Всего предметов: %d. Рюкзак вмещает %d кг.\n", n, W);

        //тут необходимо реализовать решение задачи
        //итогом является максимально воможная стоимость вещей в рюкзаке
        //вещи можно резать на кусочки (непрерывный рюкзак)
        double result = 0;

        //Arrays.sort(items);
        quickSort(items, 0, items.length - 1);


        int i = 0;
        while (W > 0 && i < items.length) {
            result += items[i].cost / items[i].weight * (W < items[i].weight ? W : items[i].weight);
            W -= items[i].weight;
            i++;
        }


        System.out.printf("Удалось собрать рюкзак на сумму %f\n", result);
        return result;
    }

    private class Item implements Comparable<Item> {
        int cost;
        int weight;

        Item(int cost, int weight) {
            this.cost = cost;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "cost=" + cost +
                    ", weight=" + weight +
                    '}';
        }

        @Override
        public int compareTo(Item o) {
            //тут может быть ваш компаратор


            return o.cost / o.weight - this.cost / this.weight;
        }
    }
}