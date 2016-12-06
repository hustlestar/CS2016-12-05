package by.it.vadimsquorpikkor.lesson01;

//Created by SquorpikkoR on 06.12.2016.

import java.math.BigInteger;

public class FiboOfMine {
    public static void main(String[] args) {
        int n = 5;

        BigInteger f1 = BigInteger.valueOf(0);
        BigInteger f2 = BigInteger.valueOf(1);

        BigInteger fibo = BigInteger.valueOf(1);

        for(int i = 0; i < n; i++) {
            System.out.print(fibo + " ");
            fibo = f1.add(f2);
            f1 = f2;
            f2 = fibo;
        }
    }

}
