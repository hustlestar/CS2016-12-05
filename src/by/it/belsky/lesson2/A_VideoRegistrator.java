package by.it.belsky.lesson2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by misha on 28.12.2016.
 */
public class A_VideoRegistrator {
    public static void main(String[] args) {
        A_VideoRegistrator instance = new A_VideoRegistrator();
        double[] events = new double[]{1, 1.1, 1.6, 2.2, 2.4, 2.7, 3.9, 8.1, 9.1, 5.5, 3.7};
          List<Double> starts = instance.calcStartTimes(events, 1); //рассчитаем моменты старта, с длинной сеанса 1
          System.out.println(starts);                            //покажем моменты старта
    }

    public List<Double> calcStartTimes(double[] events, double workDuration) {
        //events - события которые нужно зарегистрировать
        //timeWorkDuration время работы видеокамеры после старта
        List<Double> result;
        result = new ArrayList<>();

        Arrays.sort(events);
        for (int i = 0; i <events.length;) {
           double start =  events[i]; // событие слева
            result.add(start); // добавим событие в List
            double stop = events[i] + workDuration; // время работы камеры в 1 секунду , которая включилась на  собитии events[i]
            for (i=0; i<events.length && events[i]<=stop;) { // если событие "покрылось отрезком" то его  не будем вонсить в наш result;
                i++; // перебираем события посредством инкремирования счетчика
            }
        }


        return result;


    }
}