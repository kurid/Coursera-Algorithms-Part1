package sorts;

abstract class Sort<Item> {

    Comparable<Item>[] unsortedArray;

    Sort(Comparable<Item> [] unsortedArray) {
        this.unsortedArray = unsortedArray;
    }

    public   abstract Comparable<Item>[] sort();


    public void print() {
        for (Comparable comparable : unsortedArray) {
            System.out.print(comparable.toString() + " ");
        }
        System.out.println();
    }

    public boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    public void swap(int i, int j) {
        Comparable tmp = unsortedArray[i];
        unsortedArray[i] = unsortedArray[j];
        unsortedArray[j] = tmp;
    }
}
