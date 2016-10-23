package Assignment1_Percoalation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {


    private final WeightedQuickUnionUF weightedQuickUnionUF;
    private final WeightedQuickUnionUF weightedQuickUnionUFOnlyTop;

    private boolean[] isOpenSite;

    private int n;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException();
        this.n = n;
        int arrayLength = n * n + 2;
        isOpenSite = new boolean[arrayLength];
        isOpenSite[0] = true;
        isOpenSite[arrayLength - 1] = true;
        weightedQuickUnionUF = new WeightedQuickUnionUF(arrayLength);
        weightedQuickUnionUFOnlyTop = new WeightedQuickUnionUF(arrayLength - 1);
        connectToImaginaryTopAndBottom();
    }

    private void connectToImaginaryTopAndBottom() {
        for (int j = 1; j <= n; j++) {
            weightedQuickUnionUF.union(0, j);
            weightedQuickUnionUFOnlyTop.union(0, j);
            weightedQuickUnionUF.union(getPositionInArray(n, j), isOpenSite.length - 1);
        }
    }

    // open site (row i, column j) if it is not open already

    public void open(int i, int j) {
        check(i, j);
        if (isOpen(i, j)) return;
//        System.out.println(" i = " + i + " j = " + j);
        isOpenSite[getPositionInArray(i, j)] = true;
        int positionInArray = getPositionInArray(i, j);
        connectToOpenNeighbor(i - 1, j, positionInArray);
        connectToOpenNeighbor(i + 1, j, positionInArray);
        connectToOpenNeighbor(i, j + 1, positionInArray);
        connectToOpenNeighbor(i, j - 1, positionInArray);

    }

    private void connectToOpenNeighbor(int newI, int newJ, int positionInArray) {
        if (isInRange(newI, newJ) && isOpen(newI, newJ)) {
            int neighborPositionInArray = getPositionInArray(newI, newJ);
            weightedQuickUnionUF.union(neighborPositionInArray, positionInArray);
            weightedQuickUnionUFOnlyTop.union(neighborPositionInArray, positionInArray);
        }

    }


    private boolean isInRange(int newI, int newJ) {
        return newI > 0 && newI <= n && newJ > 0 && newJ <= n;
    }

    private int getPositionInArray(int i, int j) {
        return (i - 1) * n + j;
    }

    // is site (row i, column j) open?

    public boolean isOpen(int i, int j) {
        check(i, j);
        return isOpenSite[getPositionInArray(i, j)];
    }

    private void check(int i, int j) {
        if (!isInRange(i, j)) {
            throw new IndexOutOfBoundsException();

        }
    }

    // is site (row i, column j) full?

    public boolean isFull(int i, int j) {
        check(i, j);
        int pos = getPositionInArray(i, j);
        return isOpenSite[pos] && weightedQuickUnionUFOnlyTop.connected(0, pos);
    }

    // does the system percolate?

    public boolean percolates() {
        boolean hasAtLeastOneOpenTopAndBottom = true;
        if (n == 1) {
            hasAtLeastOneOpenTopAndBottom = isOpenSite[getPositionInArray(1, 1)];
        }
        if (n == 2) {
            hasAtLeastOneOpenTopAndBottom =
                    (isOpenSite[getPositionInArray(1, 1)] && isOpenSite[getPositionInArray(2, 1)]) ||
                            (isOpenSite[getPositionInArray(1, 2)] && isOpenSite[getPositionInArray(2, 2)]);


        }
        return hasAtLeastOneOpenTopAndBottom && weightedQuickUnionUF.connected(0, isOpenSite.length - 1);
    }


}
