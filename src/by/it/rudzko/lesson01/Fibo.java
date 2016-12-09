package by.it.rudzko.lesson01;

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
        if (n>1){
            return calc(n-1)+(calc(n-2));
        }else if (n==1){
            return 1;
        } else if(n==0){
            return 0;
        }else
            System.out.println("Invalid data");
        return -1;
    }


    BigInteger slowA(Integer n) {
        if (n>1){
            return slowA(n-1).add(slowA(n-2));
        }else if (n==1){
            return BigInteger.ONE;
        } else if(n==0){
            return BigInteger.ZERO;
        }else
            System.out.println("Invalid data");
        return BigInteger.valueOf((long)-1.0);
    }

    BigInteger fastB(Integer n) {
        //здесь нужно реализовать вариант с временем O(n) и памятью O(n)
        BigInteger[] res=null;
        if (n>1) {
            res = new BigInteger[n + 1];
            res[0]=BigInteger.ZERO; res[1]=BigInteger.ONE;
            for (int i = 2; i < res.length; i++) {
                res[i]=res[i-1].add(res[i-2]);
            }
        }else if (n==1){
            return BigInteger.ONE;
        } else if (n==0){
            return BigInteger.ZERO;
        } else{
            System.out.println("Invalid data");
            return BigInteger.valueOf((long)-1.0);
        }
            return res[n];
        }


    BigInteger fasterC(Integer n) {

        //попробуйте здесь релизовать самый быстрый и эффективный по использованию памяти
        //вариант, какой только сумеете
        if (n>1) {
            BigInteger[][] ar = {{BigInteger.ZERO, BigInteger.ONE}, {BigInteger.ONE, BigInteger.ONE}};
            BigInteger[][] mat = ar;
            BigInteger[][] add=new BigInteger[2][2];
            for (int a=1; a<n; a++){
            for (int s = 0; s < add.length; s++) {
                for (int j = 0; j < add[0].length; j++) {
                    BigInteger sum = BigInteger.ZERO;
                    for (int i = 0; i < mat.length; i++) {
                        sum =sum.add(mat[s][i].multiply(ar[i][j]));
                    }
                    add[s][j] = sum;
                }
            }
            mat=add;
            }
            BigInteger[] vec={BigInteger.ZERO, BigInteger.ONE};
            BigInteger[]res=new BigInteger[2];
            for (int i = 0; i < mat.length; i++) {
                BigInteger sum = BigInteger.ZERO;
                for (int j = 0; j < vec.length; j++) {
                    sum=sum.add(mat[i][j].multiply(vec[j]));

                }
                res[i] = sum;
            }
            return res[0];
        }else if (n==1){
            return BigInteger.ONE;
        } else if (n==0){
            return BigInteger.ZERO;
        } else
            System.out.println("Invalid data");
        return BigInteger.valueOf((long)-1);
    }


}
