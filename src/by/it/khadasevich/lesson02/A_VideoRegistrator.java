

import java.util.ArrayList;
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
         Arrays.sort(events);                 //C*(n log n) + C1*n = O(n log n)
                                                //вычислим момент окончания работы видеокамеры
         double start = events[i];              //получим одно событие по левому краю
        result.add(start);                      //и запомним время старта видеокамеры
         double finish = 0.0;
                                                //пока есть незарегистрированные события
         while (finish < events.length){        //и теперь пропустим все покрываемые события
            finish = start + workDuration;      //за время до конца работы, увеличивая индекс
             for (int j = i; finish < events.length j++){
                 if (events[j] > finish){
                     i = j;
                     start = events[i];
                     result.add(start);
                     break;

                 }
             }

         }
        
        return result;                        //вернем итог
    }
}
