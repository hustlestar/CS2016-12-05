package by.it.a_fedorov.lesson01;

import java.math.BigInteger;
import java.math.BigDecimal;

import static java.lang.Math.*;

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
        int mas[]=new int[n+1];

        mas[0]=0;
        mas[1]=1;
        int result;

        for (int i=2;i<=n;i++){

            mas[i]=mas[i-1]+mas[i-2];

        }//1 и 2 значение записано, поэтому i=2;
        result=mas[n];
        return result;
        //Алгоритм не медленный, но проигрывает за счет памяти.
        //Хотя если сохранить массив и брать от туда значения в дальнейшем, он будет сверхбыстрый
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
        }
        if (n > 0) {
            return BigInteger.valueOf(calc(n - 1) + calc(n - 2));
        } else {
            return BigInteger.valueOf(calc(n + 2) - calc(n + 1));
        }
    }

    BigInteger fastB(Integer n) {
        //здесь нужно реализовать вариант с временем O(n) и памятью O(n)
        int i=2;
        BigInteger oneNumber=BigInteger.ZERO;
        BigInteger twoNumber=BigInteger.ONE;
        BigInteger sum=BigInteger.ZERO;

        if (n==0){return oneNumber;}//0 число
        if (n==1){return twoNumber;}//1 число

        while(i<=n){
            i++;
            sum=oneNumber.add(twoNumber);
            oneNumber=twoNumber;
            twoNumber=sum;

        }//возвращает со 2-го чилса
        return sum;
    }

    BigDecimal fasterC(Integer n) {

        //попробуйте здесь релизовать самый быстрый и эффективный по использованию памяти
        //вариант, какой только сумеете
       /* BigDecimal one=BigDecimal.ONE;// это 1
        BigDecimal two=one.add(one);// это 2
        BigDecimal sqrt_5=BigDecimal.valueOf(2.2360679775);

        BigDecimal temp = (one.add(sqrt_5));
        BigDecimal fi=temp.divide (two);

        BigDecimal result_1=pow(fi,n);
        BigDecimal result_2=result_1.divide(sqrt_5);
        BigDecimal result_3=result_2.add(one);

        if (n==0){*/
            return BigDecimal.ZERO;
       /* }

        if (n%2==0){


            return result_2;
        }
        else{
            return result_3;//незнаю откуда +1. но при проверках, не четные значения меньше на 1
        }*/
    }

   /* private BigDecimal pow(BigDecimal fi, Integer n) {
        BigDecimal res=BigDecimal.ONE;
        for(int i=0;i<n;i++){
            res=fi.multiply(res);
        }

        return res;
    }
*/
}
