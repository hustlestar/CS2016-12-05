package by.it.jahstreet.lesson01;

import java.math.BigInteger;

/*
 * Вам необходимо выполнить три способа вычисления чисел Фибоначчи.
 */
public class Fibo {

    private long startTime = System.currentTimeMillis();

<<<<<<< HEAD
=======
    private long time() {
        return System.currentTimeMillis() - startTime;
    }

>>>>>>> origin/master
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

<<<<<<< HEAD
    private long time() {
        return System.currentTimeMillis() - startTime;
    }
=======
>>>>>>> origin/master

    private int calc(int n) {
        //здесь простейший вариант, в котором код совпадает с мат.определением чисел Фибоначчи
        //время O(2^n)
<<<<<<< HEAD
        if (n == 0) return 0;
        if (n == 1) return 1;
        return calc(n - 1) + (calc(n - 2));
=======
        return 0;
>>>>>>> origin/master
    }


    BigInteger slowA(Integer n) {
        //рекурсия
        //здесь нужно реализовать вариант без ограничения на размер числа,
        //в котором код совпадает с мат.определением чисел Фибоначчи
        //время O(2^n)
<<<<<<< HEAD
        if (n == 0) return BigInteger.ZERO;
        if (n == 1) return BigInteger.ONE;
        return slowA(n - 1).add(slowA(n - 2));
=======
        if (n==0) return BigInteger.ZERO;
        if (n==1) return BigInteger.ONE;
        return slowA(n-1).add(slowA(n-2));
>>>>>>> origin/master
    }

    BigInteger fastB(Integer n) {
        //здесь нужно реализовать вариант с временем O(n) и памятью O(n)
<<<<<<< HEAD
        BigInteger[] arr = new BigInteger[3];
        arr[0] = BigInteger.ZERO;
        arr[1] = BigInteger.ONE;

        if (n <= 0) return arr[0];
        if (n == 1) return arr[1];

        for (int i = 2; i <= n; i++) {
            arr[2] = arr[0].add(arr[1]);
            arr[0] = arr[1];
            arr[1] = arr[2];
        }
        return arr[2];
=======
        return BigInteger.ZERO;
>>>>>>> origin/master
    }

    BigInteger fasterC(Integer n) {

        //попробуйте здесь релизовать самый быстрый и эффективный по использованию памяти
        //вариант, какой только сумеете
<<<<<<< HEAD

        BigInteger zero = BigInteger.ZERO;
        BigInteger one = BigInteger.ONE;
        BigInteger res = one;
        for (int i = 2; i <= n; i++) {
            res = zero.add(one);
            zero = one;
            one = res;
        }

        return res;
=======
        return BigInteger.ZERO;
>>>>>>> origin/master
    }


}
