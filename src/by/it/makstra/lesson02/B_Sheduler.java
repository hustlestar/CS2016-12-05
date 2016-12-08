package by.it.makstra.lesson02;

import java.util.ArrayList;
<<<<<<< HEAD
import java.util.Arrays;
=======
>>>>>>> origin/master
import java.util.List;

public class B_Sheduler {
    //событие у аудитории(два поля: начало и конец)
<<<<<<< HEAD
    static class Event implements Comparable<Event>{
        private int start;
        private int stop;
=======
    static class Event {
        int start;
        int stop;
>>>>>>> origin/master

        Event(int start, int stop) {
            this.start = start;
            this.stop = stop;
        }

<<<<<<< HEAD
        public int getStop()
        {
            return stop;
        }

        public int getStart()
        {
            return start;
        }

=======
>>>>>>> origin/master
        @Override
        public String toString() {
            return "("+ start +":" + stop + ")";
        }
<<<<<<< HEAD


        @Override
        public int compareTo(Event o) {
            if(this.stop<o.getStop())
            {return -1;}
            if(this.stop>o.getStop())
            {return 1;}
            else
                return 0;
        }
=======
>>>>>>> origin/master
    }

    public static void main(String[] args) {
        B_Sheduler instance = new B_Sheduler();
        Event[] events = {  new Event(0, 3),  new Event(0, 1), new Event(1, 2), new Event(3, 5),
<<<<<<< HEAD
                new Event(1, 3),  new Event(1, 3), new Event(1, 3), new Event(3, 6),
                new Event(2, 7),  new Event(2, 3), new Event(2, 7), new Event(7, 9),
                new Event(3, 5),  new Event(2, 4), new Event(2, 3), new Event(3, 7),
                new Event(4, 5),  new Event(6, 7), new Event(6, 9), new Event(7, 9),
                new Event(8, 9),  new Event(4, 6), new Event(8, 10), new Event(7, 10)
        };
=======
                            new Event(1, 3),  new Event(1, 3), new Event(1, 3), new Event(3, 6),
                            new Event(2, 7),  new Event(2, 3), new Event(2, 7), new Event(7, 9),
                            new Event(3, 5),  new Event(2, 4), new Event(2, 3), new Event(3, 7),
                            new Event(4, 5),  new Event(6, 7), new Event(6, 9), new Event(7, 9),
                            new Event(8, 9),  new Event(4, 6), new Event(8, 10), new Event(7, 10)
                          };
>>>>>>> origin/master

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
<<<<<<< HEAD
        Arrays.sort(events);

        int i =0;
        result.add(events[i]);
        Event currentEvent = events[i];

        for(i=1; i<events.length; i++)
            if(currentEvent.getStop()<=events[i].getStart())
            {
                result.add(events[i]);
                currentEvent = events[i];
            }
        return result;                        //вернем итог
    }
}
=======






        return result;                        //вернем итог
    }
}
>>>>>>> origin/master
