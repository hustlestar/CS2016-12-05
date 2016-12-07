package by.it.hustlestar.lesson01;

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
        int fibo1 = 0;
        int fibo2 = 1;
        int fibo = 0;
        for (int i = 2; i <= n; i++) {
            fibo = fibo1+fibo2;
            fibo1 = fibo2;
            fibo2 = fibo;
        }
        return fibo;
    }


    BigInteger slowA(Integer n) {
        //рекурсия
        //здесь нужно реализовать вариант без ограничения на размер числа,
        //в котором код совпадает с мат.определением чисел Фибоначчи
        //время O(2^n)
        if (n<=1){
            return BigInteger.valueOf(n);
        } else{
            return slowA(n-1).add(slowA(n-2));
        }
    }

    BigInteger fastB(Integer n) {
        //здесь нужно реализовать вариант с временем O(n) и памятью O(n)
        BigInteger[] bigs = new BigInteger[n+1];
        bigs[0] = BigInteger.valueOf(0);
        bigs[1] = BigInteger.valueOf(1);
        for (int i = 2; i < bigs.length; i++) {
            bigs[i] = bigs[i-1].add(bigs[i-2]);
        }
        return bigs[n];
    }

    BigInteger fasterC(Integer n) {

        //попробуйте здесь релизовать самый быстрый и эффективный по использованию памяти
        //вариант, какой только сумеете
        BigInteger bigs = BigInteger.valueOf(0);
        BigInteger bigs1 = BigInteger.valueOf(0);
        BigInteger bigs2 = BigInteger.valueOf(1);
        for (int i = 2; i < n+1; i++) {
            bigs = bigs1.add(bigs2);
            bigs1 = bigs2;
            bigs2 = bigs;
        }
        return bigs;
    }


}
