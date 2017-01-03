import sorts.ShellSort;

public class Main {

    public static void main(String[] args) {
        // write your code here

        Point p = new Point(1, 8);
        Point q = new Point(8, 4);
        Point r = new Point(8, 1);

        Integer array [] = {2, 1 ,1, 1, 4, 2, 512,7,1,10, 90};
//        System.out.println("===============RESULT 1 =================");
//        SelectionSort<Integer> selectionSort = new SelectionSort<>(array);
//        selectionSort.sort();
//        selectionSort.print();
//
//        array = new Integer[]{2, 1, 1, 1, 4, 2, 512, 7, 1, 10, 90};
//        System.out.println("===============RESULT 2 =================");
//        InsertionSort<Integer> insertionSort = new InsertionSort<>(array);
//        insertionSort.sort();
//        insertionSort.print();


        array = new Integer[]{2, 1, 1, 1, 4, 2, 512, 7, 1, 10, 90};
        System.out.println("===============RESULT 2 =================");
        ShellSort<Integer> shellSort = new ShellSort<>(array);
        shellSort.sort();
        shellSort.print();

    }
}



