package by.it.sergeybelush.lesson02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class A_VideoRegistrator {

    public static void main(String[] args) {
        A_VideoRegistrator instance = new A_VideoRegistrator();
        double[] events = new double[]{1, 1.1, 1.6, 2.2, 2.4, 2.7, 3.9, 8.1, 9.1, 5.5, 3.7};
        List<Double> starts = instance.calcStartTimes(events, 1);
        System.out.println(starts);
    }
    //рассчитаем моменты старта, с длинной сеанса 1
    //покажем моменты старта
    //модификаторы доступа опущены для возможности тестирования
    //events - события которые нужно зарегистрировать
    //timeWorkDuration время работы видеокамеры после старта
    List<Double> calcStartTimes(double[] events, double workDuration){

        List<Double> result = new ArrayList<>();

        Arrays.sort(events);
        int i = 0;
        while (i < events.length) {
            result.add(events[i]);
            double r = events[i] + 1;
            i = i + 1;
            while (i < events.length && events[i] <= r) {
                i = i + 1;
            }
        }
        return result;

        //i - это индекс события events[i]
        //комментарии от проверочного решения сохранены для подсказки, но вы можете их удалить.
                                              //подготовка к жадному поглощению массива событий
                                              //hint: сортировка Arrays.sort обеспечит скорость алгоритма
                                              //C*(n log n) + C1*n = O(n log n)
                                              //пока есть незарегистрированные события
                                                //получим одно событие по левому краю
                                                //и запомним время старта видеокамеры
                                                //вычислим момент окончания работы видеокамеры
                                                //и теперь пропустим все покрываемые события
                                                //за время до конца работы, увеличивая индекс
                                                //вернем итог
    }
}
