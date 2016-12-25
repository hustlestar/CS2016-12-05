package by.it.eugene_kooks.lesson01;

import java.math.BigInteger;

/*Вам необходимо выполнить три способа вычисления чисел Фибоначчи
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
        if (n == 1){
            return 1;
        } else if (n == 2){
            return 1;
        } else {
            int znach = calc(n - 1) + calc(n - 2);
            return znach;
        }
            }
    BigInteger slowA(Integer n) {
        //рекурсияInformation:Using javac 1.8.0_111 to compile java sources
        //здесь нужно реализовать вариант без ограничения на размер числа,
        //в котором код совпадает с мат.определением чисел Фибоначчи
        //время O(2^n)
        if (n ==1 || n==2){
            return BigInteger.ONE;}
             else return slowA(n-1).add(slowA(n-2));
    }

    BigInteger fastB(Integer n) {
        //здесь нужно реализовать вариант с временем O(n) и памятью O(n)
        BigInteger[] mass = new BigInteger[n];
        mass[0] = BigInteger.ONE;
        mass[1] = BigInteger.ONE;
        for (int i = 2; i < n; i++){
            mass[i] = mass[i - 1].add(mass[i - 2]);
        }
        return mass[n-1];
    }

    BigInteger fasterC(Integer n) {

        //попробуйте здесь релизовать самый быстрый и эффективный по использованию памяти
        //вариант, какой только сумеете
        BigInteger a;
        BigInteger b = new BigInteger("1");
        BigInteger c = new BigInteger("2");

        if (n == 1 || n == 2){
            return BigInteger.ONE;
        } else {
            for(int i = 3; i <= n - 1; i++){
                a = b;
                b = c;
                c = c.add(a);
            }
            return c;
        }
    }

}



