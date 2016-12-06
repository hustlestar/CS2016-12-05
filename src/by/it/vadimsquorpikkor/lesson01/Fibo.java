package by.it.vadimsquorpikkor.lesson01;

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
        //рекурсия
        //здесь нужно реализовать вариант без ограничения на размер числа,
        //в котором код совпадает с мат.определением чисел Фибоначчи
        //время O(2^n)

        return BigInteger.ZERO;
    }

    BigInteger fastB(Integer n) {
        //здесь нужно реализовать вариант с временем O(n) и памятью O(n)

        BigInteger[] helperArray;
                helperArray = new BigInteger[n + 1];

                        for (int i = 0; i <= n; i++){
                        if (i == 0){
                                helperArray[i] = BigInteger.ZERO;
                            } else if (i == 1) {
                                helperArray[i] = BigInteger.ONE;
                            } else {
                                helperArray[i] = helperArray[i-1].add(helperArray[i-2]);
                            }
                    }
                BigInteger result = helperArray[n];
                return result;

        //return BigInteger.ZERO;
    }

    BigInteger fasterC(Integer n) {

        //попробуйте здесь релизовать самый быстрый и эффективный по использованию памяти
        //вариант, какой только сумеете

        BigInteger f1 = BigInteger.valueOf(0);
        BigInteger f2 = BigInteger.valueOf(1);

        BigInteger fibo = BigInteger.valueOf(0);

        for(int i = n; i < 1000; i++) {
            System.out.print(fibo + " ");
            fibo = f1.add(f2);
            f1 = f2;
            f2 = fibo;
        }

        return fibo;
        //return BigInteger.ZERO;
    }


}
