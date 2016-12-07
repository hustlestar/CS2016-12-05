package by.it.VeronicaDemesh.lesson01;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
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
        //рекурсия
        //здесь нужно реализовать вариант без ограничения на размер числа,
        //в котором код совпадает с мат.определением чисел Фибоначчи
        //время O(2^n)
        if (n == 0) {
            return BigInteger.ZERO;
        }
        if (n == 1) {
            return BigInteger.ONE;
        } else {
            BigInteger big1 = slowA(n - 1);
            BigInteger big2 = slowA(n - 2);
            return big1.add(big2);
        }
    }

    BigInteger fastB(Integer n) {
        //здесь нужно реализовать вариант с временем O(n) и памятью O(n)

        BigInteger big1 = new BigInteger("0");
        BigInteger big2 = new BigInteger("1");

        for (int i = 0; i < n; i++) {
            BigInteger temp = big1;
            big1 = big2;
            big2 = temp.add(big1);
        }

        return big1;
    }

    BigInteger fasterC(Integer n) {
        BigInteger arr1[][] = {{BigInteger.ONE, BigInteger.ONE}, {BigInteger.ONE, BigInteger.ZERO}};
        if (n == 0)
            return BigInteger.ZERO;
        else {
            power(arr1, n - 1);
            return arr1[0][0];
        }
    }

    private void power(BigInteger arr1[][], int n) {
        if (n == 0 || n == 1)
            return;
        BigInteger arr2[][] = {{BigInteger.ONE, BigInteger.ONE}, {BigInteger.ONE, BigInteger.ZERO}};
        power(arr1, n / 2);
        multiply(arr1, arr1);
        if (n % 2 != 0)
            multiply(arr1, arr2);
    }

    private void multiply(BigInteger arr1[][], BigInteger arr2[][]) {
        BigInteger x = (arr1[0][0].multiply(arr2[0][0])).add(arr1[0][1].multiply(arr2[1][0]));
        BigInteger y = (arr1[0][0].multiply(arr2[0][1])).add(arr1[0][1].multiply(arr2[1][1]));
        BigInteger z = (arr1[1][0].multiply(arr2[0][0])).add(arr1[1][1].multiply(arr2[1][0]));
        BigInteger w = (arr1[1][0].multiply(arr2[0][1])).add(arr1[1][1].multiply(arr2[1][1]));
        arr1[0][0] = x;
        arr1[0][1] = y;
        arr1[1][0] = z;
        arr1[1][1] = w;
    }

}
