

import PriorityQueueExercise.MaxPQ;
import edu.princeton.cs.algs4.Heap;
import edu.princeton.cs.algs4.StdOut;
import sorts.HeapSort;

import java.util.*;

public class Main {

    // print array to standard output
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }

    public static void main(String[] args) {


        Comparable[] array = new Comparable[]{2, 12, 2323, 22, 33, 44, 1};
        HeapSort.sort(array);
        show(array);
    }
}



