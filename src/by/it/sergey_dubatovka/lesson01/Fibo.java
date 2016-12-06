package by.it.sergey_dubatovka.lesson01;

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

        if (n == 0) return BigInteger.valueOf(0);
        if (n == 1) return BigInteger.valueOf(1);
        return slowA(n - 1).add(slowA(n - 2));


        //рекурсия
        //здесь нужно реализовать вариант без ограничения на размер числа,
        //в котором код совпадает с мат.определением чисел Фибоначчи
        //время O(2^n)

        //return BigInteger.ZERO;
    }

    BigInteger fastB(Integer n) {
        BigInteger[] arr = new BigInteger[n + 1];
        arr[0] = BigInteger.ZERO;
        arr[1] = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            arr[i] = arr[i - 1].add(arr[i - 2]);
        }
        //здесь нужно реализовать вариант с временем O(n) и памятью O(n)
        return arr[n];
    }

    BigInteger fasterC(Integer n) {
  //      BigInteger previousNumber = BigInteger.ZERO, currentNumber = BigInteger.ONE, nextNumber = BigInteger.ZERO;
       if (n == 0) return BigInteger.ZERO;
       if (n == 1) return BigInteger.ONE;
        BigInteger prevNumber = BigInteger.ZERO;
        BigInteger currNumber = BigInteger.ONE;
        BigInteger nextNumber = BigInteger.ZERO;
        for (int i = 2; i <= n; i++) {
            nextNumber = currNumber.add(prevNumber);
            prevNumber = currNumber;
            currNumber = nextNumber;
        }


        return nextNumber;
    }


}
