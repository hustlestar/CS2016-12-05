package by.it.a_khmelov.lesson01.vadyasquorpikkor.les01;

//Created by SquorpikkoR on 06.12.2016.

public class FiboOfMine {
    public static void main(String[] args) {
        int f1 = 0;
        int f2 = 1;

        int fibo;

        for(int i = 0; i < 50; i++) {
            fibo = f1 + f2;
            System.out.print(fibo + " ");
            f1 = f2;
            f2 = fibo;
        }
    }

}
