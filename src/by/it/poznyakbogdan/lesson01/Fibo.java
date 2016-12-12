package by.it.poznyakbogdan.lesson01;

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
        int n = 46;
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
        //время O(2^
        int f0 = 0;
        int f1 = 1;
        int f = 0;
        for (int i = 2; i <= n; i++){
           f = f1 + f0;
           f0 = f1;
           f1 = f;

           System.out.println(Integer.toString(f));
        }
        return f;
    }


    BigInteger slowA(Integer n) {
        //рекурсия
        //здесь нужно реализовать вариант без ограничения на размер числа,
        //в котором код совпадает с мат.определением чисел Фибоначчи
        //время O(2^n)
        if (n > 1){
            return slowA(n-1).add(slowA(n-2));

        }else{
            return BigInteger.valueOf(n);
        }
    }

    BigInteger fastB(Integer n) {
        //здесь нужно реализовать вариант с временем O(n) и памятью O(n)

       //int i = n;
       BigInteger fib[] = new BigInteger[n + 1];
       fib[0] = BigInteger.ZERO;
       fib[1] = BigInteger.ONE;
       for (int i = 2; i <= n; i++) {
        fib[i] = fib[i - 1].add(fib[i - 2]);
       }
       return fib[n];
    }


    BigInteger fasterC(Integer n) {

        //попробуйте здесь релизовать самый быстрый и эффективный по использованию памяти
        //вариант, какой только сумеете
        BigInteger fibo2 = BigInteger.ZERO;
        BigInteger fibo1 = BigInteger.ONE;
        BigInteger fibo = BigInteger.ZERO;
        for (int i = 2; i <= n; i++){
            fibo = fibo1.add(fibo2);
            fibo2 = fibo1;
            fibo1 = fibo;
        }
        return fibo;
    }


}
