package by.it.v_zhukovski.lesson01;

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
        System.out.println();

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
        if (n == 1){
            return 1;
        } else if (n == 2){
            return 1;
        } else {
            int val;
            val = calc(n - 1) + calc(n - 2);
            return val;
        }
    }


    BigInteger slowA(Integer n) {
        if (n == 1){
            return BigInteger.ONE;
        } else if (n == 2){
            return BigInteger.ONE;
        } else {
            BigInteger val;
            val =(slowA(n - 1).add(slowA(n - 2)));
            return val;
        }
    }

    BigInteger fastB(Integer n) {
        BigInteger[] arr = new BigInteger[n];
        arr[0] = BigInteger.ONE;
        arr[1] = BigInteger.ONE;
        for (int i = 2; i < n; i++){
            arr[i] = arr[i - 1].add(arr[i - 2]);
        }
        return arr[n-1];
    }

    BigInteger fasterC(Integer n) {

        BigInteger first;
        BigInteger second = new BigInteger("1");
        BigInteger third = new BigInteger("2");

        if (n == 1 | n == 2){
            return BigInteger.ONE;
        } else {
            for(int i = 3; i <= n - 1; i++){
                first = second;
                second = third;
                third = third.add(first);
            }
            return third;
        }
    }

}
