package by.it.mrlokans.lesson02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
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

    // Checks whether two given events intersect in time
    boolean eventsIntersect(Event e1, Event e2){
        if (e1.start <= e2.start && e1.stop > e2.start){
            return true;
        } else if (e2.start <= e1.start && e2.stop > e1.start){
            return true;
        }
        return false;
    }

    List<Event> calcStartTimes(Event[] events, int from, int to) {
        //events - события которые нужно распределить в аудитории
        //в период [from, int] (включительно).
        //оптимизация проводится по наибольшему числу непересекающихся событий.
        //начало и конец событий могут совпадать.
        List<Event> result;
        result = new ArrayList<>();

        // Here we preliminarily sort events by its stop time
        Arrays.sort(events, (e1, e2) -> Integer.compare(e1.stop, e2.stop));

        Event lastAdded = events[0];
        result.add(lastAdded);

        int i = 1;
        while (i < events.length){
            if (!eventsIntersect(lastAdded, events[i])){
                result.add(events[i]);
                lastAdded = events[i];
            }
            i++;
        }
        return result;
    }
}
