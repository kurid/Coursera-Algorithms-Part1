package sorts;

public class HeapSort {


    private static int n;

    public HeapSort() {
    }

    public static void sort(Comparable[] array) {
        n = array.length;
        bottomUp(array);
        while (n > 1) {
            swap(1, n, array);
            n--;
            sink(1, array);

        }

    }

    private static void bottomUp(Comparable[] array) {
        for (int k = n / 2; k >= 1; k--) {
            sink(k, array);
        }

    }


    private static void sink(int k, Comparable[] array) {
        while (2*k <= n) {
            int j = 2 * k;
            if (j < n && less(array, j,j + 1)) j++;

            if (!less(array, k, j)) break;
            swap(k, j, array);
            k = j;

        }
    }


    private static void swap(int i, int j, Comparable[] array) {
        Comparable tmp = array[i -1];
        array[i -1] = array[j -1];
        array[j -1] = tmp;
    }

    public static boolean less(Comparable[] array, int a, int b) {
        return array[a -1].compareTo(array[b-1]) < 0;
    }
}
