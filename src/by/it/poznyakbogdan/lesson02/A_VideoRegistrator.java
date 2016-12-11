package by.it.poznyakbogdan.lesson02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class A_VideoRegistrator {

    public static void main(String[] args) {
        A_VideoRegistrator instance=new A_VideoRegistrator();
        double[] events=new double[]{1, 1.1, 1.6, 2.2, 2.4, 2.7, 3.9, 8.1, 9.1, 5.5, 3.7};
        List<Double> starts=instance.calcStartTimes(events,1); //рассчитаем моменты старта, с длинной сеанса 1
        System.out.println(starts);                            //покажем моменты старта
    }
    //модификаторы доступа опущены для возможности тестирования
    List<Double> calcStartTimes(double[] events, double workDuration){
        //events - события которые нужно зарегистрировать
        //timeWorkDuration время работы видеокамеры после старта
        List<Double> result;
        result = new ArrayList<>();
        int i = events.length;                              //i - это индекс события events[i]
        int k = 0;                                        //комментарии от проверочного решения сохранены для подсказки, но вы можете их удалить.
        double x0 = 0;
        double xk = 0;                                                            //подготовка к жадному поглощению массива событий
         Arrays.sort(events);                                     //hint: сортировка Arrays.sort обеспечит скорость алгоритма
         while (k != i - 1) {
             if (events[k] >= x0 && events[k] <= xk) {
                 k++;
             } else {
                 x0 = events[k];
                 xk = x0 + workDuration;
                 result.add(events[k]);
                 k++;
             }
         }

        System.out.println(result);
        return result;                        //вернем итог
    }
}
