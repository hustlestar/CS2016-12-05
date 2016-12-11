package by.it.du4.lesson03;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

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

        int siftDown(int i) { //просеивание вниз
            int leftChildIndex , rightChildIndex, largestIndex;
            long tmpLong;
//          while have left child
            while (2*i+1 < heap.size()){

                leftChildIndex = 2*i+1;
                rightChildIndex = 2*i+2;
                largestIndex = i;

                if (leftChildIndex < heap.size() && heap.get(leftChildIndex) > heap.get(largestIndex)){
//                if (leftChildIndex < heap.size() && heap.get(leftChildIndex).compareTo(heap.get(largestChildIndex)) > 0){
                    largestIndex = leftChildIndex;
                }
                if (rightChildIndex < heap.size() && heap.get(rightChildIndex) > heap.get(largestIndex)){
//                if (rightChildIndex < heap.size() && heap.get(rightChildIndex).compareTo(heap.get(largestChildIndex)) > 0){
                    largestIndex = rightChildIndex;
                }
                if (largestIndex <= i){
                    break;
                }
//              swap max largestChild & current
                tmpLong = heap.get(largestIndex);
                heap.set(largestIndex, heap.get(i));
                heap.set(i, tmpLong);

                i = largestIndex;
            }
            return i;
        }

        int siftUp(int i) { //просеивание вверх
            int parentIndex = (i-1)/2;
            while (i > 0 && heap.get(parentIndex).compareTo(heap.get(i)) < 0){
                long tmp = heap.get(i);
                heap.set(i, heap.get(parentIndex));
                heap.set(parentIndex,tmp);

                i = parentIndex;
                parentIndex = (i - 1) / 2;
            }
            return i;
        }

        void insert(Long value) { //вставка
            heap.add(value);
            siftUp(heap.size()-1);
        }

        Long extractMax() { //извлечение и удаление максимума
            Long result = heap.get(0);
            heap.set(0, heap.remove(heap.size()-1));
            siftDown(0);
            return result;
        }

        public void createHeap(Long [] sourceArray){
            heap = new ArrayList<>(Arrays.asList(sourceArray));
            int heapSize = sourceArray.length;
            for (int i = heapSize / 2; i >= 0; i--){
                siftDown(i);
            }
        }

        Long getMin(){
            Long result = null;

            return result;
        }

        void changePriority(int index, Long priority){
            if (heap.get(index).equals(priority)){
                return;
            }

            heap.set(index, priority);
            if (heap.get(index/2).compareTo(priority) < 0){
                siftUp(index);
            }else{
                siftDown(index);
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
            //System.out.println(heap); //debug
            }
        }
        return maxValue;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/du4/lesson03/heapData.txt");
        C_HeapMax instance = new C_HeapMax();
        System.out.println("MAX="+instance.findMaxValue(stream));
    }

    // РЕМАРКА. Это задание исключительно учебное.
    // Свои собственные кучи нужны довольно редко.
    // "В реальном бою" все существенно иначе. Изучите и используйте коллекции
    // TreeSet, TreeMap, PriorityQueue и т.д. с нужным CompareTo() для объекта внутри.
}
