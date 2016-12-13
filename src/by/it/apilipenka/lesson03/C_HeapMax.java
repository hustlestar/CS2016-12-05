package by.it.apilipenka.lesson03;

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

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelov/lesson03/heapData.txt");
        C_HeapMax instance = new C_HeapMax();
        System.out.println("MAX=" + instance.findMaxValue(stream));
    }

    //эта процедура читает данные из файла, ее можно не менять.
    Long findMaxValue(InputStream stream) {
        Long maxValue = 0L;
        Heap<Long> heap = new Heap<>();
        //прочитаем строку для кодирования из тестового файла
        Scanner scanner = new Scanner(stream);
        Integer count = scanner.nextInt();
        for (int i = 0; i < count; ) {
            String s = scanner.nextLine();
            if (s.equalsIgnoreCase("extractMax")) {
                Long res = heap.extractMax();
                if (res != null && res > maxValue) maxValue = res;
                System.out.println();
                i++;
            }
            if (s.contains(" ")) {
                String[] p = s.split(" ");
                if (p[0].equalsIgnoreCase("insert"))
                    heap.insert(Long.parseLong(p[1]));
                i++;
                // System.out.println(heap); //debug
            }
        }
        return maxValue;
    }


    // РЕМАРКА. Это задание исключительно учебное.
    // Свои собственные кучи нужны довольно редко.
    // "В реальном бою" все существенно иначе. Изучите и используйте коллекции
    // TreeSet, TreeMap, PriorityQueue и т.д. с нужным CompareTo() для объекта внутри.
}

class Heap<T extends Comparable<T>> {
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! НАЧАЛО ЗАДАЧИ !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
    //тут запишите ваше решение.
    //Будет мало? Ну тогда можете его собрать как Generic и/или использовать в варианте B

    // parameter: true - Max Heap; false - Min Heap
    private boolean isMaxHeap;
    private List<T> heap = new ArrayList<>();

    Heap() {
        this.isMaxHeap = true;
    }

    Heap(boolean isMaxHeap) {
        this.isMaxHeap = isMaxHeap;
    }


    private void siftDown(int i) {
        if (heap.size() < 2) return;
        int j;
        int n = i;
        if (isMaxHeap) {


            while (n < heap.size()) {

                if (heap.size() - 1 > 2 * n + 1)
                    j = heap.get(2 * n + 1).compareTo(heap.get(2 * n + 2)) > 0 ? 2 * i + 1 : 2 * i + 2;
                else if (heap.size() - 1 > 2 * n) j = 2 * n + 1;
                else return;
                if (heap.get(n).compareTo(heap.get(j)) < 0) {
                    T temp = heap.get(n);
                    heap.set(n, heap.get(j));
                    heap.set(j, temp);
                    n = j;
                } else
                    return;


            }
        } else {
            if (heap.size() - 1 > 2 * n + 1)
                j = heap.get(2 * n + 1).compareTo(heap.get(2 * n + 2)) < 0 ? 2 * i + 1 : 2 * i + 2;
            else if (heap.size() - 1 > 2 * n) j = 2 * n + 1;
            else return;

            while (n < heap.size()) {

                if (heap.get(n).compareTo(heap.get(j)) > 0) {
                    T temp = heap.get(n);
                    heap.set(n, heap.get(j));
                    heap.set(j, temp);
                    n = j;
                } else
                    return;


            }
        }
    }

    private void siftUp(int i) { //просеивание вверх
        if (heap.size() < 2) return;
        int j = (i - 1) / 2;
        int n = i;
        if (isMaxHeap)
            while (n > 0 && heap.get(n).compareTo(heap.get(j)) > 0) {
                T temp = heap.get(n);
                heap.set(n, heap.get(j));
                heap.set(j, temp);
                n = j;
                j = (n - 1) / 2;

            }
        else
            while (n > 0 && heap.get(n).compareTo(heap.get(j)) < 0) {
                T temp = heap.get(n);
                heap.set(n, heap.get(j));
                heap.set(j, temp);
                n = j;
                j = (n - 1) / 2;

            }

    }

    void insert(T value) {
        heap.add(value);
        siftUp(heap.size() - 1);
    }


    void add(T value) {
        insert(value);
    }

    T extractMax() { //извлечение и удаление максимума

        if (heap.size() == 0) return null;
        T result = heap.get(0);
        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        siftDown(0);
        return result;

    }

    T poll() { //извлечение и удаление максимума

        return extractMax();

    }

    T peek() { //извлечение и удаление максимума

        if (heap.size() == 0) return null;
        return heap.get(0);

    }

    long size() {
        return heap.size();
    }


}

//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! КОНЕЦ ЗАДАЧИ !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1

