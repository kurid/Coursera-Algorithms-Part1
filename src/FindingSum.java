public class FindingSum {


    public static void main(String[] args) {
        int n = 5;
        int m = 7;
        int c = 15;
        int[] a = {-1, 2, 5, 10, 12,};
        int[] b = {-5, -3, 0, 6, 7, 10, 15};



        int j = b.length - 1;
        for (int i = 0; i < a.length; i++) {
            int k = a[i];
            while (true) {
                int t = b[j];
                if (k + t == c) {
                    System.out.println(i + " " + j);
                } else if (k + t < c) break;
                j--;
            }
        }


//        for (int i = 0; i < a.length; i++) {
//            int findPod = findMatch(c - a[i], b);
//            if (findPod != -1) {
//                System.out.println(i + " " + findPod);
//                return;
//            }
//        }

    }

    private static int findMatch(int key, int[] bigger) {
        int low = 0;
        int high = bigger.length - 1;
        while (high >= low) {
            int middle = (high + low) / 2;
            if (bigger[middle] == key) return middle;
            else if (bigger[middle] < key)
                low = middle + 1;
            else high = middle - 1;
        }
        return -1;
    }
}
