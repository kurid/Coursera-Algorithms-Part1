package sorts;

public class ShellSort<Item> extends Sort<Item> {

    private int N;
    private int h;

    public ShellSort(Comparable<Item>[] unsortedArray) {
        super(unsortedArray);

        N = unsortedArray.length;

        h = 1;
        while (h <= N / 3)
            h = h * 3 + 1;
    }

    @Override
    public Comparable<Item>[] sort() {
        while (h >= 1) {
            for (int i = h; i < unsortedArray.length; i++) {
                for (int j = i; j >=h
                        && less(unsortedArray[j], unsortedArray[j - h]); j -=h) {
                    swap(j, j - 1);
                }
            }
            h /= 3;
        }
        return unsortedArray;
    }

}
