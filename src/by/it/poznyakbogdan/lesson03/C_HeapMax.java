package by.it.poznyakbogdan.lesson03;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Array;
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
        private ArrayList<Long> heap = new ArrayList<>();

        int siftDown(int i) { //просеивание вниз
            int current = i;
            int down_elem = getMaxChild(2 * i, 2 * i + 1);
            while (heap.get(current) <= heap.get(down_elem)) {
                replaceElem(current, down_elem);
                current = down_elem;
                down_elem = getMaxChild(2 * i, 2 * i + 1);
            }
            i = current;
            return i;
        }

        public void replaceElem(int m, int n){
            Long temp = heap.get(m);
            heap.add(m, heap.get(n));
            heap.add(n, temp);
        }

        int getMaxChild(int m, int n){
            if (heap.get(m) >= heap.get(n)){
                return m;
            }else {
                return n;
            }
        }

        int siftUp(int i) { //просеивание вверх
            int current = i;
            int up_elem = (int) i/2;
            while (heap.get(current) >= heap.get(up_elem)) {
                replaceElem(current, up_elem);
                current = up_elem;
                up_elem = (int) i/2;
            }
            i = current;
            return i;
        }

        void insert(Long value) { //вставка
            int current = heap.size();
            heap.add(current, value);
            if(current > 1) {                   //где то происходит зацикливание по причине того что в массиве
                siftUp(current);                //индексы начинаются с 0(нужно устранить этот баг)
            }
        }

        Long extractMax() { //извлечение и удаление максимума
            Long result = null;

            return result;
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
            //System.out.println(heap); //debug
            }
        }
        return maxValue;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelov/lesson03/heapData.txt");
        C_HeapMax instance = new C_HeapMax();

        System.out.println("MAX="+instance.findMaxValue(stream));
    }

    // РЕМАРКА. Это задание исключительно учебное.
    // Свои собственные кучи нужны довольно редко.
    // "В реальном бою" все существенно иначе. Изучите и используйте коллекции
    // TreeSet, TreeMap, PriorityQueue и т.д. с нужным CompareTo() для объекта внутри.
}
