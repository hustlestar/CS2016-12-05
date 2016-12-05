package by.it.astro_emelya.lesson01;

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
        return 0;
    }


    BigInteger slowA(Integer n) {
        if (n == 0)
            return BigInteger.valueOf(0);
        else if (n == 1)
            return BigInteger.valueOf(1);
        else {
            return slowA(n - 1).add(slowA(n - 2));
        }
    }

    BigInteger fastB(Integer n) {
        BigInteger[] bigInteger = new BigInteger[n + 1];

        if (n == 0)
            return bigInteger[0] = BigInteger.valueOf(0);
        else if (n == 1)
            return bigInteger[1] = BigInteger.valueOf(1);
        else {
            bigInteger[0] = BigInteger.valueOf(0);
            bigInteger[1] = BigInteger.valueOf(1);
            for (int i = 2; i < bigInteger.length; i++) {
                bigInteger[i] = bigInteger[i - 1].add(bigInteger[i - 2]);
            }
            return bigInteger[n];
        }
    }

    BigInteger fasterC(Integer n) {

        //попробуйте здесь релизовать самый быстрый и эффективный по использованию памяти
        //вариант, какой только сумеете
        return BigInteger.ZERO;
    }


}
