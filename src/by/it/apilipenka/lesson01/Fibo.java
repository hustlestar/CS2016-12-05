package by.it.apilipenka.lesson01;

import java.math.BigInteger;

/*
 * Вам необходимо выполнить три способа вычисления чисел Фибоначчи.
 */
public class Fibo {

    private long startTime = System.currentTimeMillis();
    private long startTimeN = System.nanoTime();

    public static void main(String[] args) {
        Fibo fibo = new Fibo();
        int n = 33;
        System.out.printf("calc(%d)=%d \n\t time=%d \n\t timeN=%d \n\n", n, fibo.calc(n), fibo.time(), fibo.timeN());

        //вычисление чисел фибоначчи медленным методом (рекурсией)
        fibo = new Fibo();
        n = 33;
        System.out.printf("slowA(%d)=%d \n\t time=%d \n\t  timeN=%d \n\n", n, fibo.slowA(n), fibo.time(), fibo.timeN());

        //вычисление чисел простым быстрым методом
        fibo = new Fibo();
        n = 55555;
        System.out.printf("fastB(%d)=%d \n\t time=%d \n\t  timeN=%d \n\n", n, fibo.fastB(n), fibo.time(), fibo.timeN());

        //вычисление чисел фибоначчи самым быстрым методом
        fibo = new Fibo();
        n = 55555;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\t  timeN=%d \n\n", n, fibo.fasterC(n), fibo.time(), fibo.timeN());

        //вычисление чисел фибоначчи самым быстрым методом
        fibo = new Fibo();
        n = 55555;
        System.out.printf("fasterD(%d)=%d \n\t time=%d \n\t  timeN=%d \n\n", n, fibo.fasterD(n), fibo.time(), fibo.timeN());

    }

    long time() {
        return System.currentTimeMillis() - startTime;
    }

    long timeN() {
        return System.nanoTime() - startTimeN;
    }

    private int calc(int n) {

        if (n <= 1) return n;

        return calc(n - 1) + calc(n - 2);
    }


    BigInteger slowA(Integer n) {

        int i = n;

        if (i <= 1) return BigInteger.valueOf(i);

        return slowA(i - 1).add(slowA(i - 2));
    }

    BigInteger fastB(Integer n) {

        int i = n;

        BigInteger a[] = new BigInteger[i + 1];
        a[0] = BigInteger.ZERO;
        a[1] = BigInteger.ONE;

        for (int j = 2; j <= i; j++) {
            a[j] = a[j - 1].add(a[j - 2]);
        }

        return a[i];
    }

    BigInteger fasterC(Integer n) {


        int i = n;


        BigInteger a1 = BigInteger.ZERO;
        BigInteger a2 = BigInteger.ONE;


        for (int j = 2; j <= i; j++) {
            if (j % 2 == 0)
                a1 = a1.add(a2);
            else
                a2 = a1.add(a2);
        }
        if (i % 2 == 0)
            return a1;
        else
            return a2;

    }

    BigInteger fasterD(Integer n) {

        int i = n;


        long l[] = new long[i];
        l[0] = 0;
        l[1] = 1;

        BigInteger a[] = new BigInteger[i + 1];


        int j = 2;
        while (j <= i) {
            l[j] = l[j - 1] + l[j - 2];
            if (l[j] <= 0) {
                a[j - 2] = BigInteger.valueOf(l[j - 2]);
                a[j - 1] = BigInteger.valueOf(l[j - 1]);
                while (j <= i) {
                    a[j] = a[j - 1].add(a[j - 2]);
                    j++;
                }
                return a[i];
            }
            j++;

        }

        return BigInteger.valueOf(l[i]);
    }


}
