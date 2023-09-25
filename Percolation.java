import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {


    // instantiating the grid
    private boolean[][] grid;
    // instantiating number of open sites in a grid
    private int numOpen;
    // instantiating union find
    private WeightedQuickUnionUF unionFind;


    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        grid = new boolean[n][n];
        numOpen = 0;
        unionFind = new WeightedQuickUnionUF(n * n + 2);
    }

    // check if the coordinates are outside the grid
    private void outOfBounds(int row, int col) {
        if (row < 0 || row >= grid.length) {
            throw new IllegalArgumentException();
        }
        if (col < 0 || col >= grid.length) {
            throw new IllegalArgumentException();
        }
    }


    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        outOfBounds(row, col);
        if (grid[row][col]) {
            return;
        }
        grid[row][col] = true;
        numOpen++;
        if (row == 0) {
            unionFind.union(0, (col + 1));
        }
        if (row == grid.length - 1) {
            unionFind.union(row * grid.length + col + 1,
                            grid.length * grid.length + 1);
        }
        if (row != 0) {
            if (grid[row - 1][col]) {
                unionFind.union(((row - 1) * grid.length + col + 1), ((row) *
                        grid.length + col + 1));
            }
        }
        if (row != grid.length - 1) {
            if (grid[row + 1][col]) {
                unionFind.union(((row + 1) * grid.length + col + 1), ((row) *
                        grid.length + col + 1));
            }
        }
        if (col != 0) {
            if (grid[row][col - 1]) {
                unionFind.union(((row) * grid.length + col), ((row) *
                        grid.length + col + 1));
            }
        }
        if (col != grid.length - 1) {
            if (grid[row][col + 1]) {
                unionFind.union(((row) * grid.length + col + 2), ((row) *
                        grid.length + col + 1));
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        outOfBounds(row, col);
        return grid[row][col];
    }


    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        outOfBounds(row, col);
        return unionFind.find(row * grid.length + col + 1) == unionFind.find(0);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numOpen;
    }

    // does the system percolate?
    public boolean percolates() {
        return (unionFind.find(grid.length * grid.length + 1) == unionFind.find(0));
    }

    // unit testing (required)
    public static void main(String[] args) {
        Percolation test = new Percolation(5);
        test.open(3, 3);
        test.isOpen(3, 3);
        test.isFull(3, 3);
        test.percolates();
        test.numberOfOpenSites();
    }

}
