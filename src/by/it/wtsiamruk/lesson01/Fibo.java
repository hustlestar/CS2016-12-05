package by.it.wtsiamruk.lesson01;

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
        n = 33;
        System.out.printf("fastB(%d)=%d \n\t time=%d \n\n", n, fibo.fastB(n), fibo.time());

        //вычисление чисел фибоначчи самым быстрым методом
        fibo = new Fibo();
        n = 33;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n), fibo.time());
    }


    private int calc(int n) {
        //здесь простейший вариант, в котором код совпадает с мат.определением чисел Фибоначчи
        //время O(2^n)
        return 0;
    }


    BigInteger slowA(Integer n) {
        //рекурсия
        //здесь нужно реализовать вариант без ограничения на размер числа,
        //в котором код совпадает с мат.определением чисел Фибоначчи
        //время O(2^n)
        if (n == 1) return BigInteger.ONE;
        if (n==0) return BigInteger.ZERO;
        return  (slowA(n-1)).add (slowA(n-2));
    }

    BigInteger fastB(Integer n) {
        //здесь нужно реализовать вариант с временем O(n) и памятью O(n)
        if (n == 0) return BigInteger.ZERO;
        if (n == 1) return BigInteger.ONE;
        BigInteger[] array = new BigInteger[n+1];
        array[0] = BigInteger.ZERO;
        array[1] = BigInteger.ONE;
        for (int i = 2; i < array.length; i++) {
            array[i] = array[i -1].add(array[i-2]);
        }
        return array[n];
    }

    BigInteger fasterC(Integer n) {
        //попробуйте здесь релизовать самый быстрый и эффективный по использованию памяти
        //вариант, какой только сумеете
        if (n == 0) return BigInteger.ZERO;
        if (n == 1) return BigInteger.ONE;

        BigInteger fibo1 = BigInteger.ONE;
        BigInteger fibo2 = BigInteger.ZERO;
        BigInteger fibonachi = BigInteger.ZERO;
        for (int i = 0; i < n; i++) {
            fibonachi = fibo1.add(fibo2);
            fibo1 = fibo2;
            fibo2 = fibonachi;
        }


        return fibonachi;
    }


}
