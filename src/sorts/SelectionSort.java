package sorts;

public class SelectionSort<Item> extends Sort {

    public SelectionSort(Comparable<Item>[] unsortedArray) {
        super(unsortedArray);
    }

    @Override
    public Comparable<Item>[] sort() {
        for (int i = 0; i < unsortedArray.length - 1; i++ ) {
            int pos = smallestItemPositionFromRightPart(i + 1);
            if (less(unsortedArray[pos], unsortedArray[i])) {
                swap(i, pos);
            }
        }
        return unsortedArray;
    }


    private int smallestItemPositionFromRightPart(int i) {
        int result = i;
        Comparable smallestItem = unsortedArray[i];
        for (int k = i + 1; k < unsortedArray.length; k++) {
            if (less(unsortedArray[k], smallestItem)) {
                smallestItem = unsortedArray[k];
                result = k;
            }
        }
        return result;
    }

}
