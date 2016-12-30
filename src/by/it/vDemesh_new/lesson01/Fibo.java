package by.it.vDemesh_new.lesson01;

import java.math.BigInteger;

/*
 * Вам необходимо выполнить три способа вычисления чисел Фибоначчи
 */

public class Fibo {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        Fibo fibo = new Fibo();
        int n = 33;
        System.out.printf("calc(%d)=%d \n\t time=%d \n\n", n, fibo.calc(n), fibo.time());

        //вычисление чисел фибоначчи медленным методом (рекурсией)
        fibo = new Fibo();
        n = 33;
        System.out.printf("slowA(%d)=%d \n\t time=%d \n\n", n, fibo.slowA(n), fibo.time());

        //вычисление чисел простым быстрым методом
        fibo = new Fibo();
        n = 55555;
        System.out.printf("fastB(%d)=%d \n\t time=%d \n\n", n, fibo.fastB(n), fibo.time());

        //вычисление чисел фибоначчи самым быстрым методом
        fibo = new Fibo();
        n = 55555;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n), fibo.time());
    }


    private int calc(int n) {
        //здесь простейший вариант, в котором код совпадает с мат.определением чисел Фибоначчи
        //время O(2^n)
        int n1 = 0;
        int n2 = 1;
        int res = 0;
        if (n <= 1) {
            return n;
        } else {
            for (int i = 2; i <= n; i++) {
                res = n1 + n2;
                n1 = n2;
                n2 = res;
            }
            return res;
        }
    }

    BigInteger slowA(Integer n) {
        //рекурсия
        //здесь нужно реализовать вариант без ограничения на размер числа,
        //в котором код совпадает с мат.определением чисел Фибоначчи
        //время O(2^n)
        if (n == 0) {
            return BigInteger.ZERO;
        }
        if (n == 1) {
            return BigInteger.ONE;
        } else {
            BigInteger big1 = slowA(n - 1);
            BigInteger big2 = slowA(n - 2);
            return big1.add(big2);
        }
    }

    BigInteger fastB(Integer n) {
        //здесь нужно реализовать вариант с временем O(n) и памятью O(n)

        BigInteger[] fiboSeq = new BigInteger[n+1];
        fiboSeq[0] = BigInteger.ZERO;
        fiboSeq[1] = BigInteger.ONE;
        for (int i = 2; i < fiboSeq.length; i++) {
            fiboSeq[i] = fiboSeq[i-1].add(fiboSeq[i-2]);
        }
        return fiboSeq[n];
    }

    BigInteger fasterC(Integer n) {
        BigInteger n1 = BigInteger.ZERO;
        BigInteger n2 = BigInteger.ONE;

        for (int i = 0; i < n; i++) {
            BigInteger temp = n1;
            n1 = n2;
            n2 = temp.add(n1);
        }
        return n1;
    }
}