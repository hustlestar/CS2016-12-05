package by.it.grishkevich.lesson01;

import java.math.BigInteger;

/*
 * Вам необходимо выполнить три способа вычисления чисел Фибоначчи.
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
        if (n == 0){ return 0;}
        if (n == 1){ return 1;}
        else {
            int a = 0, b = 1, fib = 0;
            for(int i = 2; i <= n; i++){
            fib = a + b;
            a = b;
            b = fib;}
            return fib;
        }
    }

    BigInteger slowA(Integer n) {
        //рекурсия
        //здесь нужно реализовать вариант без ограничения на размер числа,
        //в котором код совпадает с мат.определением чисел Фибоначчи
        //время O(2^n)
        if (n == 0) {
            return BigInteger.ZERO;
        } else if (n == 1) {
            return BigInteger.ONE;
        }
        return slowA(n-1).add(slowA(n - 2));
    }

    BigInteger fastB(Integer n) {
        //здесь нужно реализовать вариант с временем O(n) и памятью O(n)
        BigInteger[] fibArr = new BigInteger[n + 1];
        for (int i = 0; i <= n; i++){
            if (i == 0){
                fibArr[i] = BigInteger.ZERO;
            }
            else if (i == 1) {
                fibArr[i] = BigInteger.ONE;
            }
            else {
                fibArr[i] = fibArr[i-1].add(fibArr[i-2]);
            }
        }
        return fibArr[n];
    }

    BigInteger fasterC(Integer n) {

        //попробуйте здесь релизовать самый быстрый и эффективный по использованию памяти
        //вариант, какой только сумеете
        BigInteger b = BigInteger.ZERO;
        BigInteger b1 = BigInteger.ZERO;
        BigInteger b2 = BigInteger.ONE;
        for (int i = 2; i < n+1; i++) {
            b = b1.add(b2);
            b1 = b2;
            b2 = b;
        }
        return b;
    }
}