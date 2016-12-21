package by.it.a_fedorov.lesson02;

import java.util.ArrayList;
import java.util.List;

public class B_Sheduler {
    //событие у аудитории(два поля: начало и конец)
    static class Event {
        int start;
        int stop;

        Event(int start, int stop) {
            this.start = start;
            this.stop = stop;
        }

        @Override
        public String toString() {
            return "("+ start +":" + stop + ")";
        }
    }

    public static void main(String[] args) {
        B_Sheduler instance = new B_Sheduler();
        Event[] events = {  new Event(0, 3),  new Event(0, 1), new Event(1, 2), new Event(3, 5),
                            new Event(1, 3),  new Event(1, 3), new Event(1, 3), new Event(3, 6),
                            new Event(2, 7),  new Event(2, 3), new Event(2, 7), new Event(7, 9),
                            new Event(3, 5),  new Event(2, 4), new Event(2, 3), new Event(3, 7),
                            new Event(4, 5),  new Event(6, 7), new Event(6, 9), new Event(7, 9),
                            new Event(8, 9),  new Event(4, 6), new Event(8, 10), new Event(7, 10)
                          };

        List<Event> starts = instance.calcStartTimes(events,0,10);  //рассчитаем оптимальное заполнение аудитории
        System.out.println(starts);                                 //покажем рассчитанный график занятий
    }

    List<Event> calcStartTimes(Event[] events, int from, int to) {
        //events - события которые нужно распределить в аудитории
        //в период [from, int] (включительно).
        //оптимизация проводится по наибольшему числу непересекающихся событий.
        //начало и конец событий могут совпадать.
        List<Event> result;
        result = new ArrayList<>();
        //ваше решение.
        int i=0;
        int start=0;
        int stop=0;
        Event temp = null;

        for ( i = events.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {

                if (events[j].start == events[j + 1].start)
                    if (events[j].stop+events[j].start > events[j + 1].stop+events[j+1].start)  {
                        temp = events[j];
                        events[j] = events[j + 1];
                        events[j + 1] = temp;
                    }
            }
        }

        for ( i = events.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (events[j].start > events[j + 1].start) {
                    temp = events[j];
                    events[j] = events[j + 1];
                    events[j + 1] = temp;
                }

            }
        }
        for ( i = events.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {

                if (events[j].start == events[j + 1].start)
                    if (events[j].stop+events[j].start > events[j + 1].stop+events[j+1].start)  {
                        temp = events[j];
                        events[j] = events[j + 1];
                        events[j + 1] = temp;
                    }
            }
        }
        while(i<events.length){
            start=events[i].start;
            stop=events[i].stop;
            result.add(events[i]);
            while(events[i].start<stop){
                i++;
                if (i == events.length)break;
            }
        }





        return result;                        //вернем итог
    }
}
