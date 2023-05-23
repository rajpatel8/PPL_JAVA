// This program demonstrates the use of the Callable interface and the
// Future interface to calculate the factorial of a number.
// FactorialCalculator implements the Callable interface and overrides
// the call() method. The call() method is invoked when an object of
// FactorialCalculator is passed to a thread as a task to be executed.
// The call() method calculates the factorial of a given number and
// returns the result.
// In the main() method, the factorial of 10 is calculated.

package Lab9;

import java.util.concurrent.*;

class FactorialCalculator implements Callable<Long> {
    private int number;

    public FactorialCalculator(int number) {
        this.number = number;
    }

    @Override
    public Long call() throws Exception {
        return factorial(number);
    }

    private long factorial(int n) throws InterruptedException {
        long result = 1;
        while (n != 0) {
            result = n * result;
            n = n - 1;
            Thread.sleep(100);
        }
        return result;
    }
}

public class DemoCall {
    public static void main(String args[]) throws Exception {
        ExecutorService es = Executors.newSingleThreadExecutor();
        System.out.println("Submitted callable task to calculate factorial of 10");
        Future<Long> result10 = es.submit(new FactorialCalculator(10));
        System.out.println("Calling get method of Future to fetch the result of factorial");
        long factorialOf10 = result10.get();
        System.out.println("Factorial of 10 is: " + factorialOf10);
        es.shutdown();
    }
}
