package by.it.du4.lesson1;

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
        n =  55555;
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

        if (n==0)return BigInteger.ZERO;
        if (n==1)return BigInteger.ONE;
        return slowA(n-1).add(slowA(n-2));
    }

    BigInteger fastB(Integer n) {
        //здесь нужно реализовать вариант с временем O(n) и памятью O(n)
        if (n==0)return BigInteger.ZERO;
        if (n==1)return BigInteger.ONE;

        BigInteger [] array = new BigInteger[n+1];

        array[0] = BigInteger.ZERO;
        array[1] = BigInteger.ONE;

        for (int i = 2 ; i <= n ; i++){
            array[i] = array[i-1].add(array[i-2]);
        }

        return array[n];
    }

    BigInteger fasterC(Integer n) {

        //попробуйте здесь релизовать самый быстрый и эффективный по использованию памяти
        //вариант, какой только сумеете

        if (n==1)return BigInteger.ZERO;
        if (n==2)return BigInteger.ONE;

        BigInteger bigIntegerF1 = BigInteger.ZERO;
        BigInteger bigIntegerF2 = BigInteger.ONE;
        BigInteger tmpBigInteger;

        for (int i = 2 ; i <= n ; i++){
            tmpBigInteger = bigIntegerF2;
            bigIntegerF2 = bigIntegerF2.add(bigIntegerF1);
            bigIntegerF1 = tmpBigInteger;
        }

        return bigIntegerF2;
    }


}
