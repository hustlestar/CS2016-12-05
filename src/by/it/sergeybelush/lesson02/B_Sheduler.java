package by.it.sergeybelush.lesson02;

import java.util.*;
import java.util.stream.Collectors;

public class B_Sheduler  {

    //событие у аудитории(два поля: начало и конец)
    static class Event {
        int start;
        int stop;

        Event(int start, int stop) {
            this.start = start;
            this.stop = stop;
        }

        boolean intersects(Event newEvent) {
            return newEvent.start < this.stop;
        }

        @Override
        public String toString() {

            return "(" + start + ":" + stop + ")";
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Event event = (Event) o;
            return start == event.start &&
                    stop == event.stop;
        }

        @Override
        public int hashCode() {
            return Objects.hash(start, stop);
        }
    }

    public static void main(String[] args) {
        B_Sheduler instance = new B_Sheduler();
        Event[] events = {new Event(0, 3), new Event(0, 1), new Event(1, 2), new Event(3, 5),
                new Event(1, 3), new Event(1, 3), new Event(1, 3), new Event(3, 6),
                new Event(2, 7), new Event(2, 3), new Event(2, 7), new Event(7, 9),
                new Event(3, 5), new Event(2, 4), new Event(2, 3), new Event(3, 7),
                new Event(4, 5), new Event(6, 7), new Event(6, 9), new Event(7, 9),
                new Event(8, 9), new Event(4, 6), new Event(8, 10), new Event(7, 10)
        };

        List<Event> starts = instance.calcStartTimes(events, 0, 10);  //рассчитаем оптимальное заполнение аудитории
        System.out.println(starts);                                   //покажем рассчитанный график занятий
    }

    //events - события которые нужно распределить в аудитории
    //в период [from, to] (включительно).
    //оптимизация проводится по наибольшему числу непересекающихся событий.
    //начало и конец событий могут совпадать.
    List<Event> calcStartTimes(Event[] events, int from, int to) {
        List<Event> input = Arrays.stream(events)
                .distinct()//delete repeated elements
                .filter(event -> event.start >= from && event.stop <= to)
                .sorted((x, y) -> Integer.compare(x.stop, y.stop))
                .collect(Collectors.toList());//add results to a list

        if (input.isEmpty()) {
            return new ArrayList<>();
        }

        List<Event> result = new ArrayList<>();
        Event last = input.get(0);
        result.add(last);
        for (int i = 1; i < input.size(); i++) {
            Event current = input.get(i);
            if (!last.intersects(current)) {
                result.add(current);
                last = current;
            }
        }

        return result;
    }
}
