package Assignment1_Percoalation;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private double constant = 1.96;
    private int trials;
    private int n;
    private double openedSitedCounter;

    private double[] results;

    // perform trials independent experiments on an n-by-n gridc class Assignment1_Percoalation.PercolationStats {
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException();


        this.n = n;
        this.trials = trials;
        results = new double[trials];
        simulation();
    }

    private void simulation() {
        for (int k = 0; k < trials; k++) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                int i = StdRandom.uniform(1, n + 1);
                int j = StdRandom.uniform(1, n + 1);
                if (percolation.isOpen(i, j))
                    continue;
                percolation.open(i, j);
                openedSitedCounter++;
            }
            results[k] = openedSitedCounter / (n * n);
            openedSitedCounter = 0;
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(results);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(results);
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (constant * stddev()) / Math.sqrt(trials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (constant * stddev()) / Math.sqrt(trials);

    }


    // test client (described below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(n, t);

        System.out.println("mean                    = " + ps.mean());
        System.out.println("stddev                  = " + ps.stddev());
        System.out.println("95% confidence interval = " + ps.confidenceLo() + ", " + ps.confidenceHi());

    }
}
