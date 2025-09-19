package oop.multithreading;

import java.math.BigInteger;

public class FactorialCalculator {

    public static void main(String[] args) {
        for (int i = 1; i <= 100; i++) {
            final int threadNum = i;
            Thread thread = new Thread(() -> {
                BigInteger result = calculateFactorial(threadNum);
                System.out.println("Поток " + threadNum + ": Факториал числа " + threadNum + " = " + result);
            });
            thread.start();
        }
    }

    public static BigInteger calculateFactorial(int n) {
        BigInteger result = BigInteger.ONE;
        for (int i = 1; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }
}
