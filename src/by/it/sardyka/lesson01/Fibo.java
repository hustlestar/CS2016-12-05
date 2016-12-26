package by.it.sardyka.lesson01;

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
        if (n <= 1) {
            return n;
        } else {
            return calc(n - 1) + calc(n - 2);
        }
    }


    BigInteger slowA(int n) {
        //рекурсия
        //здесь нужно реализовать вариант без ограничения на размер числа,
        //в котором код совпадает с мат.определением чисел Фибоначчи
        //время O(2^n)
        BigInteger n1;
        BigInteger n2;
        BigInteger n3;
        if (n == 0) {
            return BigInteger.ZERO;
        }
        if (n == 1) {
            return BigInteger.ONE;
        }
        n1 = BigInteger.ZERO;
        n2 = BigInteger.ONE;
        n3 = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            n3 = n2.add(n1);
            n1 = n2;
            n2 = n3;
        }
        return n3;
    }

    BigInteger fastB(int n) {
        //здесь нужно реализовать вариант с временем O(n) и памятью O(n)
        if (n == 0) {
            return BigInteger.ZERO;
        }
        if (n == 1) {
            return BigInteger.ONE;
        }

        BigInteger nn[] = new BigInteger[n + 1];
        nn[0] = BigInteger.ZERO;
        nn[1] = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            nn[i] = nn[i - 1].add(nn[i - 2]);
        }
        return nn[n];
    }

    BigInteger fasterC(Integer n) {

        //попробуйте здесь релизовать самый быстрый и эффективный по использованию памяти
        //вариант, какой только сумеете
        BigInteger n1 = BigInteger.ZERO;
        BigInteger n2 = BigInteger.ONE;
        BigInteger n3 = null;
        for (int i = 2; i <= n; i++) {
            if (i % 2 == 0) {
                n1 = n1.add(n2);
            } else {
                n2 = n2.add(n1);
            }
        }
        if (n % 2 == 0) {
            n3 = n1;
        } else {
            n3 = n2;
        }
        return n3;
    }


}
