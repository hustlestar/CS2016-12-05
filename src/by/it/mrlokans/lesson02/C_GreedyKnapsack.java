package by.it.mrlokans.lesson02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class C_GreedyKnapsack {
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

            double priceWeightRatio = cost / weight;
            double otherPriceWeightRatio = o.cost / o.weight;

            if (priceWeightRatio > otherPriceWeightRatio){
                return 1;
            // Note that we have obvious float number comparison errors
            } else if (priceWeightRatio == otherPriceWeightRatio){
                return 0;
            } else {
                return -1;
            }
        }
    }

    double maximumNumberOfItems(Item item, int knapsackCapacity){
        return (double) knapsackCapacity / (double) item.weight;
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
        for (Item item:items) {
            System.out.println(item);
        }
        System.out.printf("Всего предметов: %d. Рюкзак вмещает %d кг.\n",n,W);

        Arrays.sort(items, Collections.reverseOrder());


        double result = 0;

        double remainingWeight = (double) W;

        for(Item item: items){
            if (remainingWeight == 0){
                break;
            }
            if (remainingWeight - item.weight >= 0.0){
                result += item.cost;
                remainingWeight -=  item.weight;
            } else {
                result += remainingWeight * item.cost / item.weight;
                break;
            }
        }

        System.out.printf("Удалось собрать рюкзак на сумму %f\n",result);
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        String root=System.getProperty("user.dir")+"/src/";
        File f=new File(root+"by/it/a_khmelov/lesson02/greedyKnapsack.txt");
        double costFinal=new C_GreedyKnapsack().calc(f);
        long finishTime = System.currentTimeMillis();
        System.out.printf("Общая стоимость %f (время %d)",costFinal,finishTime - startTime);
    }
}