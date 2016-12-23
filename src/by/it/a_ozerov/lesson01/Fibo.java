package by.it.a_ozerov.lesson01;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

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
        if (n <= 1) {return n;}

        int n_1 = 1, n_2 = 0;
        for (int i = 2; i < (n - 1); i++){
            n_1 = n_1 + n_2;
            n_2 = n_1 - n_2;
        }

        return (n_1 + n_2);

    }


    BigInteger slowA(Integer n) {
        //рекурсия
        //здесь нужно реализовать вариант без ограничения на размер числа,
        //в котором код совпадает с мат.определением чисел Фибоначчи
        //время O(2^n)

        if (n <= 1) {
            return BigInteger.valueOf(n);
        }
        return slowA(n - 2).add(slowA(n - 1));
    }

        BigInteger fastB(Integer n) {
            //здесь нужно реализовать вариант с временем O(n) и памятью O(n)
            List<BigInteger> fList = new ArrayList<>();
            fList.add(BigInteger.ZERO);
            fList.add(BigInteger.ONE);
            for (int i = 2; i <= n; i++) {
                fList.add(fList.get(i - 2).add(fList.get(i - 1)));
            }
            return fList.get(n);
        }

                BigInteger fasterC(Integer n) {

                    //попробуйте здесь релизовать самый быстрый и эффективный по использованию памяти
                    //вариант, какой только сумеете
                    BigInteger f0 = BigInteger.ZERO;
                    BigInteger f1= BigInteger.ONE;

                    if (n > 1) {
                        for (int i = 2; i <= n; i++) {
                            if (i % 2 == 0) {
                                f0 = f0.add(f1);
                            } else {
                                f1 = f1.add(f0);
                            }
                        }
                    }

                    if (n % 2 ==0){
                        return f0;
                    } else {
                        return f1;
                    }
                }
            }
