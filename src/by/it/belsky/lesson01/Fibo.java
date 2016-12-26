package by.it.belsky.lesson01;

import java.math.BigInteger;

/**
 * Created by misha on 25.12.2016.
 */
public class Fibo {
    public long time = System.currentTimeMillis();

    public long time() {
        return (System.currentTimeMillis() - time);
    }

    public static void main(String[] args) {
        Fibo fibo = new Fibo();
        int n = 100000;
        System.out.printf("n=%d starttime=%d fibo=%d\n endtime=%d time=%d\n", n, fibo.time, fibo.simplecalc(n), System.currentTimeMillis(), fibo.time());
        n = 8;
        System.out.printf("n=%d fibo=%d time=%d\n",n, fibo.slowA(n), fibo.time());
        n = 100;
        System.out.printf("n=%d fibo=%d\n time=%d\n",n, fibo.fastB(n), fibo.time());

    }

    public BigInteger simplecalc(int n) {
        BigInteger fibo1 = BigInteger.ZERO;
        BigInteger fibo2 = BigInteger.ONE;
        BigInteger fibo = BigInteger.ZERO;
        for (int i = 2; i <= n; i++) {
            fibo = fibo1 .add( fibo2);
            fibo1 = fibo2; // первое фибо приравнивается второму
            fibo2 = fibo; // второе фибо приравнивается предыдущей посчитанной сумме
        }
        return fibo;
    }

    public BigInteger slowA(Integer n) {
        BigInteger rez = BigInteger.ZERO;
        if (n > 1) {
            rez = slowA(n - 1).add(slowA(n - 2));
        } else if (n == 1) {
            rez = BigInteger.ONE;
        }
        return rez;
    }

    BigInteger fastB(Integer n) {

        BigInteger[] bigs = new BigInteger[n + 1];
        bigs[0] = BigInteger.ZERO;
        bigs[1] = BigInteger.ONE;
        for (int i = 2; i < bigs.length; i++) {
            bigs[i] = bigs[i - 1].add(bigs[i - 2]);
        }
        return bigs[n];
    }
}