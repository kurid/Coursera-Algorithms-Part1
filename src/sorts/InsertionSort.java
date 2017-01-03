package sorts;

public class InsertionSort<Item> extends Sort {


    public InsertionSort(Comparable<Item>[] unsortedArray) {
        super(unsortedArray);
    }

    @Override
    public Comparable[] sort() {
        for (int i = 0; i < unsortedArray.length; i++) {
            for (int j = i; j > 0
                    && less(unsortedArray[j], unsortedArray[j - 1]); j--) {
                swap(j, j - 1);
            }
        }
        return unsortedArray;

    }

}
