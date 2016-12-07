package by.it.stereoby.lesson01;

import java.math.BigInteger;
import java.util.ArrayList;

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
        if (n <= 2) {
            return 1;													//O(1)
        } else {
            return calc(n - 1) + calc(n - 2);							//O(2^(n-2))
        }
    }


    public BigInteger slowA(Integer n) {
    	if(n.compareTo(2) <= 0) {
    		return new BigInteger("1");
    	} else {
    		return slowA(n-1).add(slowA(n-2));
    	}
        // Итого: 
        //		Скорость выполнения: 1 + 2^(n-2) = 2^(n-2) = O(2^n)
        //		Память:	O(2^n)?   	
    }

    public BigInteger fastB(Integer n) {
    	ArrayList<BigInteger> sequence = new ArrayList<>((int)n);		//O(n)
    	sequence.add(BigInteger.ZERO);									//O(1)
    	sequence.add(BigInteger.ONE);									//O(1)
    	
    	for(int i = 2; i <= (int) n; i++) {								//O(n)
    		sequence.add(sequence.get(i-1).add(sequence.get(i-2)));		//O(3)
    	}
    	
    	return sequence.get((int) n);									//O(1)
    	// Итого: 
        //		Скорость выполнения: n + 1 + 1 + n * 3 + 1 = 4*n + 3 = O(n)
        //		Память: O(n)
    }

    public BigInteger fasterC(Integer n) {
    	BigInteger previous = BigInteger.ZERO;							//O(1)
    	BigInteger current = BigInteger.ONE;							//O(1)
    	BigInteger temp = null;											//O(1)
        for(int i = 1; i < (int) n; i++) {								//O(n)
        	temp = current;												//O(1)
        	current = current.add(previous);							//O(1)
        	previous = temp;											//O(1)
        }

        return current;													//O(1)
    	// Итого: 
        //		Скорость выполнения: 1 + 1 + 1 + n * 1 + n * 1 + n * 1 + 1 = 3*n + 4 = O(n)
        //		Память:	O(3)?
    }


}
