/* This program finds the maximum element of an array by dividing it into 2 sub-array and solve it to find the problem's solution using Fork-Join Framework. It uses the Fork-Join pool and RecursiveTask class. The Fork-Join pool is used to execute the Fork-Join task and the RecursiveTask class is used to return the result of the task. The compute() method is used to split the task into 2 subtasks and execute them in parallel. The join() method is used to return the result of the task. */

package Lab10;

// Program to find the maximum element of an array by dividing it into 2 sub-array and solve it to find the problem's solution using Fork-Join Framework.

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;


public class ArrayMax {

    static final int THRESHOLD = 10000; // The threshold for splitting the task

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ForkJoinPool pool = new ForkJoinPool();
        int max = pool.invoke(new MaxTask(array, 0, array.length));
        System.out.println("Maximum element: " + max);
    }

    static class MaxTask extends RecursiveTask<Integer> {
       
        private final int[] array;
        private final int start;
        private final int end;

        MaxTask(int[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            if (end - start <= THRESHOLD) { // if the task is small enough, compute the maximum element directly
                int max = Integer.MIN_VALUE;
                for (int i = start; i < end; i++) {
                    if (array[i] > max) {
                        max = array[i];
                    }
                }
                return max;
            } else { // otherwise, split the task into two subtasks and execute them in parallel
                int mid = start + (end - start) / 2;
                MaxTask leftTask = new MaxTask(array, start, mid);
                MaxTask rightTask = new MaxTask(array, mid, end);
                leftTask.fork();
                int rightMax = rightTask.compute();
                int leftMax = leftTask.join();
                return Math.max(leftMax, rightMax);
            }
        }
    }
}