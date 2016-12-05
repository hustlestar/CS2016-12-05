package by.it.wtsiamruk.lesson01.classwork;

/**
 * Created by wtsiamruk on 12/5/16.
 */
class GCD {
    static int EuclidAlgorithm(int firstNumber, int secondNumber)
    {
        if (firstNumber == 0 ) return secondNumber;
        int result = (firstNumber % secondNumber);
        return EuclidAlgorithm(result,secondNumber);
    }
}
