import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    // array to store number of open sites
    private double[] threshold;
    // number of trials
    private int trials;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        this.trials = trials;
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        threshold = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation test = new Percolation(n);
            while (!test.percolates()) {

                int row = StdRandom.uniform(0, n);
                int col = StdRandom.uniform(0, n);
                test.open(row, col);
            }
            threshold[i] = (double) test.numberOfOpenSites() / (double) (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        double mean = StdStats.mean(threshold);
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        double stddev = StdStats.stddev(threshold);
        return stddev;

    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        double low = mean() - (1.96 * stddev()) / (Math.sqrt(trials));
        return low;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        double high = mean() + (1.96 * stddev()) / (Math.sqrt(trials));
        return high;
    }

    // test client (see below)
    public static void main(String[] args) {
        PercolationStats test = new PercolationStats(100, 6400);
        test.mean();
        test.stddev();
        test.confidenceLow();
        test.confidenceHigh();
    }

}
