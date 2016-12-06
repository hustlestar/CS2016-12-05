package by.it.mrlokans;

import java.math.BigInteger;

/*
 * Вам необходимо выполнить три способа вычисления чисел Фибоначчи
 */
public class Fibo {

    private long startTime = System.currentTimeMillis();
    private static int FIBO_MATRIX_INDEX = 1;

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
        } else if (n == 1) {
            return BigInteger.ONE;
        }

        return slowA(n-1).add(slowA(n - 2));
    }

    BigInteger fastB(Integer n) {
        // Initialize array sized with N elements
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
    }

    BigInteger fasterC(Integer n) {

        //попробуйте здесь релизовать самый быстрый и эффективный по использованию памяти
        //вариант, какой только сумеете

        // Here we use fibonacci number calcaulation algorithm, based
        // on the matrix exponentiation
        // see https://en.wikipedia.org/wiki/Fibonacci_number#Matrix_form
        BigInteger[] initialMatrix = new BigInteger[]{
            BigInteger.ONE, BigInteger.ONE, BigInteger.ONE, BigInteger.ZERO
        };

        BigInteger[] calculatedMatrix = exponentiateMatrix(initialMatrix, n);
        return calculatedMatrix[FIBO_MATRIX_INDEX];
    }

    public static BigInteger[] exponentiateMatrix(BigInteger[] matrix, int power){
        BigInteger[] result = {BigInteger.ONE, BigInteger.ZERO, BigInteger.ZERO, BigInteger.ONE};

        // Here we use exponentiation by squaring to optimize calculations
        // https://en.wikipedia.org/wiki/Exponentiation_by_squaring
        while (power != 0){
            if (power % 2 != 0){
                result = multiplySquareMatrices(result, matrix);
            }
            power /= 2;
            matrix = multiplySquareMatrices(matrix, matrix);
        }
        return result;
    }

    // Multiplies two 2x2 square matrices
    public static BigInteger[] multiplySquareMatrices(BigInteger[] first, BigInteger[] second){
        // multiplies row by column
        BigInteger[] result = new BigInteger[] {
            first[0].multiply(second[0]).add(first[1].multiply(second[2])),
            first[0].multiply(second[1]).add(first[1].multiply(second[3])),
            first[2].multiply(second[0]).add(first[3].multiply(second[2])),
            first[2].multiply(second[1]).add(first[3].multiply(second[3]))
        };
        return result;
    }


}
