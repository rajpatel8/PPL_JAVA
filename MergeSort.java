package Lab10;

// Program to sort the array elements using Merge sort using Fork-Join Framework.

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class MergeSort {

    public static void main(String[] args) {
        int[] array = {5} ;
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(new SortTask(array, 0, array.length));
        System.out.println(Arrays.toString(array));
    }

    // This code sorts an array of integers in ascending order. It uses the
// fork-join framework to sort the array in parallel.

static class SortTask extends RecursiveAction {
        private final int[] array;
        private final int start;
        private final int end;

        SortTask(int[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            if (end - start > 1) { // if the array has more than one element
                int mid = start + (end - start) / 2;
                SortTask leftTask = new SortTask(array, start, mid);
                SortTask rightTask = new SortTask(array, mid, end);
                invokeAll(leftTask, rightTask); // execute the subtasks in parallel

                // merge the sorted subarrays
                int[] temp = new int[end - start];
                int i = start;
                int j = mid;
                int k = 0;
                while (i < mid && j < end) {
                    if (array[i] < array[j]) {
                        temp[k++] = array[i++];
                    } else {
                        temp[k++] = array[j++];
                    }
                }
                while (i < mid) {
                    temp[k++] = array[i++];
                }
                while (j < end) {
                    temp[k++] = array[j++];
                }
                System.arraycopy(temp, 0, array, start, temp.length);
            }
        }
    }
}