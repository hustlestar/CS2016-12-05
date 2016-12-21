package by.it.alesnax.lesson03;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Lesson 3. C_Heap.
// Задача: построить max-кучу = пирамиду = бинарное сбалансированное дерево на массиве.
// ВАЖНО! НЕЛЬЗЯ ИСПОЛЬЗОВАТЬ НИКАКИЕ КОЛЛЕКЦИИ, КРОМЕ ARRAYLIST (его можно, но только для массива)

//      Проверка проводится по данным файла
//      Первая строка входа содержит число операций 1 ≤ n ≤ 100000.
//      Каждая из последующих nn строк задают операцию одного из следующих двух типов:

//      Insert x, где 0 ≤ x ≤ 1000000000 — целое число;
//      ExtractMax.

//      Первая операция добавляет число x в очередь с приоритетами,
//      вторая — извлекает максимальное число и выводит его.

//      Sample Input:
//      6
//      Insert 200
//      Insert 10
//      ExtractMax
//      Insert 5
//      Insert 500
//      ExtractMax
//
//      Sample Output:
//      200
//      500


public class C_HeapMax {

    private class MaxHeap {
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! НАЧАЛО ЗАДАЧИ !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
        //тут запишите ваше решение.
        //Будет мало? Ну тогда можете его собрать как Generic и/или использовать в варианте B
        private List<Long> heap = new ArrayList<>();





        int siftDown(int i) { //просеивание
            long parent = heap.get(i);
            int leftPos = 2 * i + 1;
            int rightPos= 2 * i + 2;
            long left, right, temp;
            if(leftPos >= heap.size()){
                return i;
            } else if(rightPos >= heap.size()){
                left = heap.get(leftPos);
                if(left > parent){
                    temp = left;
                    heap.set(leftPos, parent);
                    heap.set(i, temp);
                }
                return i;
            } else {
                left = heap.get(leftPos);
                right = heap.get(rightPos);
                int maxChildPos = left < right ? rightPos : leftPos;
                if(heap.get(maxChildPos) > parent){
                    temp = heap.get(maxChildPos);
                    heap.set(maxChildPos, parent);
                    heap.set(i, temp);
                    siftDown(maxChildPos);
                    return i;
                } else {
                    return i;
                }
            }
        }

        int siftUp(int i) { //просеивание
            if(heap.size() <= 1 || i == 0){
                return i;
            } else {
                int parPos = (i - 1)/2;
                long par = heap.get(parPos);
                long cur = heap.get(i);
                if(par > cur){
                    return i;
                } else{
                    long temp = cur;
                    heap.set(i, par);
                    heap.set(parPos, temp);
                    siftUp(parPos);
                    return i;
                }
            }
        }

        void insert(Long value) { //вставка
            heap.add(value);
            siftUp(heap.size() -1);
        }

        Long extractMax() { //извлечение и удаление максимума
            if(heap.size() == 0){
                return null;
            } else if(heap.size() <= 2){
                return heap.remove(0);
            } else {
                Long result = heap.remove(0);
                heap.add(0, heap.remove(heap.size() - 1));
                siftDown(0);
                return result;
            }
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! КОНЕЦ ЗАДАЧИ !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
    }

    //эта процедура читает данные из файла, ее можно не менять.
    Long findMaxValue(InputStream stream) {
        Long maxValue=0L;
        MaxHeap heap = new MaxHeap();
        //прочитаем строку для кодирования из тестового файла
        Scanner scanner = new Scanner(stream);
        Integer count = scanner.nextInt();
        for (int i = 0; i < count; ) {
            String s = scanner.nextLine();
            if (s.equalsIgnoreCase("extractMax")) {
                Long res=heap.extractMax();
                if (res!=null && res>maxValue) maxValue=res;
                System.out.println();
                i++;
            }
            if (s.contains(" ")) {
                String[] p = s.split(" ");
                if (p[0].equalsIgnoreCase("insert"))
                    heap.insert(Long.parseLong(p[1]));
                i++;
            System.out.println(heap); //debug
            }
        }
        return maxValue;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/alesnax/lesson03/heapData.txt");
        C_HeapMax instance = new C_HeapMax();
        System.out.println("MAX="+instance.findMaxValue(stream));
    }

    // РЕМАРКА. Это задание исключительно учебное.
    // Свои собственные кучи нужны довольно редко.
    // "В реальном бою" все существенно иначе. Изучите и используйте коллекции
    // TreeSet, TreeMap, PriorityQueue и т.д. с нужным CompareTo() для объекта внутри.
}
