package by.it.dushkov.lesson02;

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
        int i=0;                              //i - это индекс события events[i]
        //комментарии от проверочного решения сохранены для подсказки, но вы можете их удалить.
                                              //подготовка к жадному поглощению массива событий
                                              //hint: сортировка Arrays.sort обеспечит скорость алгоритма
                                              //C*(n log n) + C1*n = O(n log n)
        Arrays.sort(events);
        while(i < events.length) { //пока есть незарегистрированные события
            result.add(events[i]); //получим одно событие по левому краюи запомним время старта видеокамеры
            double cameraFinishTime = events[i] + workDuration; //вычислим момент окончания работы видеокамеры
            while(events[i] <= cameraFinishTime) {
                i++;   //и теперь пропустим все покрываемые события за время до конца работы, увеличивая индекс
                if(i > events.length - 1) break;
            }
        }

        return result;                        //вернем итог
    }
}
