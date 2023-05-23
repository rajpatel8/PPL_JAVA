// This code is used to find the occurrence of an element in an array by sub diving it into 2 arrays and solving each subproblem to solve it using Fork-Join Framework.

package Lab10;

// Program to find the occurrence of an element in an array by sub diving it into 2 arrays and solving each subproblem to solve it using Fork-Join Framework.

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ArrayOccurrence {

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int element = 3;
        // Fork-Join pool is used to execute the Fork-Join task
        ForkJoinPool pool = new ForkJoinPool(); 
        // RecursiveTask class is used to return the result of the task
        int count = pool.invoke(new OccurrenceTask(array, element)); 
        System.out.println("Occurrence count of " + element + ": " + count);
    }

    static class OccurrenceTask extends RecursiveTask<Integer> { 
        // RecursiveTask class is used to return the result of the task
        private final int[] array;
        private final int element;

        OccurrenceTask(int[] array, int element) { // constructor to initialize the array and element
            this.array = array;
            this.element = element;
        }

        @Override
        protected Integer compute() {
            if (array.length == 1) { // base case: single element array
                return array[0] == element ? 1 : 0;
            } else { // split the task into two subtasks and execute them in parallel
                int mid = array.length / 2;
                OccurrenceTask leftTask = new OccurrenceTask(
                    java.util.Arrays.copyOfRange(array, 0, mid), element);
                OccurrenceTask rightTask = new OccurrenceTask(
                    java.util.Arrays.copyOfRange(array, mid, array.length), element);
                leftTask.fork();
                int rightCount = rightTask.compute();
                int leftCount = leftTask.join();
                return leftCount + rightCount;
            }
        }
    }
}