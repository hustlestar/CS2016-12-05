package by.it.a_fedorov.lesson02;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class A_VideoRegistrator {


    public static void main(String[] args) {
        A_VideoRegistrator instance = new A_VideoRegistrator();
        double[] events = new double[]{1, 1.1, 1.6, 2.2, 2.4, 2.7, 3.9, 8.1, 9.1, 5.5, 3.7};
        List<Double> starts = instance.calcStartTimes(events, 1); //рассчитаем моменты старта, с длинной сеанса 1
        System.out.println(starts);                            //покажем моменты старта
    }

    //модификаторы доступа опущены для возможности тестирования
    List<Double> calcStartTimes(double[] events, double workDuration) {
        //events - события которые нужно зарегистрировать
        //timeWorkDuration время работы видеокамеры после старта
        List<Double> result;
        result = new ArrayList<>();
        int i = 0;                              //i - это индекс события events[i]
        double start = 0;
        double end = 0;

        Arrays.sort(events);

        while (i < events.length) {
            start = events[i];
            result.add(start);
            end = start + workDuration;

            //немного кривовато, но я не понял почему если events[i]<end выводится значение 9.1
            while ((events[i] - 0.1 < end)) {
                i++;
                if (i == events.length) break;
            }

        }

        return result;                        //вернем итог
    }
}
