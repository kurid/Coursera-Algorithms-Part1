package PriorityQueueExercise;

public class MaxPQ<Key extends Comparable<Key>> {

    Key[] pq;
    int n;

    public MaxPQ(int capacity) {
        pq = (Key[]) new Comparable[capacity];
    }

    public Key max() {
        return pq[1];
    }

    public void insert(Key key) {
        pq[++n] = key;
        swim(n);
    }

    public Key delMax() {
        Key result = pq[1];
        pq[1] = pq[n];
        sink(1);
        n--;
        return result;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }


    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(pq[j], pq[j + 1])) j++;

            if (!less(pq[k], pq[j])) break;
            swap(k, j);
            k = j;

        }
    }

    private void swim(int k) {
        while (k > 1 && less(pq[k / 2], pq[k])) {
            swap(k, k / 2);
            k /= 2;
        }
    }

    private void swap(int a, int b) {
        Key tmp = pq[a];
        pq[a] = pq[b];
        pq[b] = tmp;
    }


    public boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }
}
