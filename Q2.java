package Lab9;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

// This program demonstrates the use of the Callable interface and the
// Future interface to calculate the factorial of a number.
// FactorialCalculator implements the Callable interface and overrides
// the call() method. The call() method is invoked when an object of
// FactorialCalculator is passed to a thread as a task to be executed.
// The call() method calculates the factorial of a given number and
// returns the result.

class FactorialCalculator2 implements Callable<Long> {
    private int number;

    public FactorialCalculator2(int number) {
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

// In the main() method, the factorial of 10 is calculated.

public class Q2 {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        List<Future<Long>> list = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Callable<Long> task = new FactorialCalculator2(i);
            Future<Long> future = executor.submit(task);
            list.add(future);
        }
        executor.shutdown();

        for (Future<Long> future : list) {
            System.out.println("Result of factorial calculation: " + future.get());
        }
    }
}