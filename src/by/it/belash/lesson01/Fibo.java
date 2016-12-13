package by.it.belash.lesson01;

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
        if (n == 0){
            return 0;
        }
        else if (n == 1){
            return 1;
        }
        else {
            int fib  = 0;
            int fib1 = 0;
            int fib2 = 1;
            for (int i = 2; i <= n; i++){
                fib = fib2 + fib1;
                fib1 = fib2;
                fib2 = fib;
            }
            return fib;
        }
    }


    BigInteger slowA(Integer n) {
        //рекурсия
        //здесь нужно реализовать вариант без ограничения на размер числа,
        //в котором код совпадает с мат.определением чисел Фибоначчи
        //время O(2^n)
        if (n == 0){
            return BigInteger.ZERO;
        }
        else if (n == 1){
            return BigInteger.ONE;
        }
        else {
            return (slowA(n - 2).add(slowA(n - 1)));
        }
    }

    BigInteger fastB(Integer n) {
        //здесь нужно реализовать вариант с временем O(n) и памятью O(n)
        BigInteger[] FibArray;
        FibArray = new BigInteger[n + 1];
        for (int i = 0; i <= n; i++){
            if (i == 0){
                FibArray[i] = BigInteger.ZERO;
            }
            else if (i == 1) {
                FibArray[i] = BigInteger.ONE;
            }
            else {
                FibArray[i] = FibArray[i-1].add(FibArray[i-2]);
            }
        }
        return FibArray[n];
    }

    BigInteger fasterC(Integer n) {

        //попробуйте здесь релизовать самый быстрый и эффективный по использованию памяти
        //вариант, какой только сумеете
        return fastB(n);
    }


}
