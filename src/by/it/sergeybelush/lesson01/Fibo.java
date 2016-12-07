package by.it.sergeybelush.lesson01;

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

        fibo = new Fibo();
        int n1 = 24;
        int n2 = 16;
        System.out.print("NOD = " + fibo.NOD(n1, n2));
    }

    //здесь простейший вариант, в котором код совпадает с мат.определением чисел Фибоначчи
    //время O(2^n)
    private int calc(int n) {
        if (n <= 1) {
            return n;
        } else {
            return calc(n - 1) + calc(n - 2);
        }
    }

    //рекурсия
    //здесь нужно реализовать вариант без ограничения на размер числа,
    //в котором код совпадает с мат.определением чисел Фибоначчи
    //время O(2^n)
    BigInteger slowA(Integer n) {
        if (n <= 1) {
            return BigInteger.valueOf(n);
        } else {
            return slowA(n - 1).add(slowA(n - 2));
        }
    }

    //здесь нужно реализовать вариант с временем O(n) и памятью O(n)
    BigInteger fastB(Integer n) {
        BigInteger[] number = new BigInteger[n + 1];
        number[0] = BigInteger.ZERO;
        number[1] = BigInteger.ONE;
        BigInteger sum = null;
        for (int i = 2; i < number.length; i++) {
            number[i] = number[i - 1].add(number[i - 2]);
        }
        return number[number.length - 1];
    }

    //попробуйте здесь релизовать самый быстрый и эффективный по использованию памяти
    //вариант, какой только сумеете
        BigInteger fasterC(Integer n) {
            if(n <= 1) {
                return BigInteger.valueOf(n);
            }
            BigInteger x = BigInteger.ZERO;
            BigInteger y = BigInteger.ONE;
            BigInteger fib = null;
            for(int i = 2; i <= n; i++) {
                fib = x.add(y);
                x = y;
                y = fib;
            }
            return fib;
        }

    int NOD(int c, int d) {
        if (d == 0) {
            return c;
        } else {
            int o = c % d;
            return NOD(d, o);
        }
    }
}