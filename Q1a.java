// This code creates a class called DemoTask, which implements the Runnable interface.
// The class has a private String variable called name, a constructor that takes a String
// argument and sets the name variable to that value, a getName() method that returns the
// name of the task, and a run() method that prints the name of the task and sleeps for
// 2000 milliseconds.

package Lab9;
import java.util.concurrent.*;

class DemoTask implements Runnable
{
    private String name;
    
    public DemoTask(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void run() {
        try {
            System.out.println("Executing : " + name);
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// This code creates a fixed thread pool with 2 threads. 
// Then, it creates 5 DemoTask objects. 
// Each DemoTask object is a Runnable object that prints out its name 
// and the time when it starts to run. The 5 tasks are submitted to the executor and executed concurrently. 
// After all tasks are completed, the executor is shut down.

public class Q1a {
    public static void main(String[] args) {
        // create a fixed thread pool with 2 threads
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // create 5 DemoTask objects
        DemoTask task1 = new DemoTask("Task 1");
        DemoTask task2 = new DemoTask("Task 2");
        DemoTask task3 = new DemoTask("Task 3");
        DemoTask task4 = new DemoTask("Task 4");
        DemoTask task5 = new DemoTask("Task 5");

        // submit the tasks to the executor
        executor.submit(task1);
        executor.submit(task2);
        executor.submit(task3);
        executor.submit(task4);
        executor.submit(task5);

        // shutdown the executor after all tasks are completed
        executor.shutdown();
    }
}
