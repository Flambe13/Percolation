import edu.princeton.cs.algs4.StdOut;

public class Percolation2 {

    private int grid[][];
    private int numOpen;
    private boolean successfulPercolation;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation2(int n) {
        grid = new int[n][n];
        numOpen = 0;
        successfulPercolation = false;
    }

    public void full(int row, int col) {
        grid[row][col] = 2;
        if (row == grid.length - 1) {
            successfulPercolation = true;
        }
        if (row != 0) {
            if (grid[row - 1][col] == 1) {
                full(row - 1, col);
            }
        }
        if (col != 0) {
            if (grid[row][col - 1] == 1) {
                full(row, col - 1);
            }
        }
        if (col != grid.length - 1) {
            if (grid[row][col + 1] == 1) {
                full(row, col + 1);
            }
        }
        if (row != grid.length - 1) {
            if (grid[row + 1][col] == 1) {
                full(row + 1, col);
            }
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (grid[row][col] == 0) {
            grid[row][col] = 1;
            numOpen += 1;
            if (row == 0) {
                full(row, col);
            }
            else if (grid[row - 1][col] == 2) {
                full(row, col);
            }
            else if (grid[row][col - 1] == 2) {
                full(row, col);
            }
            else if (grid[row][col + 1] == 2) {
                full(row, col);
            }
            else if (grid[row + 1][col] == 2) {
                full(row, col);
            }
        }

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return grid[row][col] == 1 || grid[row][col] == 2;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        return grid[row][col] == 2;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numOpen;
    }

    // does the system percolate?
    public boolean percolates() {
        return successfulPercolation;
    }

    // unit testing (required)
    public static void main(String[] args) {
        Percolation2 trial = new Percolation2(5);
        trial.open(2, 2);
        StdOut.print(trial.isOpen(2, 2));
        trial.open(1, 2);
        StdOut.print(trial.isOpen(1, 2));
        trial.open(0, 2);
        StdOut.print(trial.isOpen(0, 2));
        trial.open(2, 3);
        trial.open(3, 3);
        trial.open(4, 3);
        StdOut.print(trial.percolates());

    }

}



