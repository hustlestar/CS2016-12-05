package by.it.makstra.lesson1;

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
        int member1 = 0;
        int member2 = 1;
        int result = 0;
        for(int i = 2; i<= n; i++)
        {
            result = member1 + member2;
            member1 = member2;
            member2 = result;
        }
        return result;
    }


    BigInteger slowA(Integer n) {
        //рекурсия
        //здесь нужно реализовать вариант без ограничения на размер числа,
        //в котором код совпадает с мат.определением чисел Фибоначчи
        //время O(2^n)
        if(n==0) return BigInteger.valueOf(0);
        if(n==1) return BigInteger.valueOf(1);
        return slowA(n-1).add(slowA(n-2));
    }

    BigInteger fastB(Integer n) {
        //здесь нужно реализовать вариант с временем O(n) и памятью O(n)
        BigInteger[] a = new BigInteger[n+1];
        a[0] = BigInteger.valueOf(0);
        a[1] = BigInteger.valueOf(1);
        for(int i = 2; i<= n; i++)
        {
            a[i] = a[i-1].add(a[i-2]);
        }
        return a[n];

    }

    BigInteger fasterC(Integer n) {
        BigInteger res = BigInteger.valueOf(0);
        BigInteger mem1 = BigInteger.valueOf(0);
        BigInteger mem2 = BigInteger.valueOf(1);
        for (int i = 2; i < n+1; i++) {
            res = mem1.add(mem2);
            mem1 = mem2;
            mem2 = res;
        }
        return res;
    }


}
