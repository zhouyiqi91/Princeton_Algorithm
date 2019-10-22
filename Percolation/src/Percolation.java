/* *****************************************************************************
 *  Name:              Yiqi Zhou
 *  Coursera User ID:  123456
 *  Last modified:     10/18/2019
 **************************************************************************** */
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[] open;
    private WeightedQuickUnionUF grid;
    private WeightedQuickUnionUF gridHelper;
    private final int n;
    private int count=0;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n<=0) throw new IllegalArgumentException();
        this.n = n;
        int size = n * n + 2;  //use idenx from 1 to n*n(size-1)
        grid = new WeightedQuickUnionUF(size);
        gridHelper = new WeightedQuickUnionUF(size-1);
        open = new boolean[size];
        for (int i=1;i<size;i++) open[i] = false;
        open[0] = true;
        open[size-1] = true;
    }

    // In grid
    private boolean inGrid(int row,int col){
        if (row <= 0 || row > n || col <= 0 || col > n) return false;
        return true;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col){
        if (!inGrid(row,col)) throw new IllegalArgumentException();
        if (!isOpen(row,col)) {
            int index = (row-1) * n + col;
            open[index] = true;
            int[] up = {row-1,col};
            int[] down = {row+1,col};
            int[] left = {row,col-1};
            int[] right = {row,col+1};
            int[][] fourDirection = {up,down,left,right};
            for (int[] i : fourDirection){
                if (inGrid(i[0],i[1]) && isOpen(i[0],i[1])) {
                    grid.union(index, (i[0] - 1) * n + i[1]);
                    gridHelper.union(index, (i[0] - 1) * n + i[1]);
                }
            }
            count++;
            // head & tail
            if(row == 1) {
                grid.union(0, index);
                gridHelper.union(0, index);
            }
            if(row == n)
                grid.union(index,n*n+1);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        if (!inGrid(row,col)) throw new IllegalArgumentException();
        int index = (row-1) * n + col;
        return (open[index]);
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (!inGrid(row, col)) throw new IllegalArgumentException();
        int index = (row-1)*n+col;
        return (gridHelper.connected(0,index));
    }

    // returns the number of open sites
    public int numberOfOpenSites(){
        return count;
    }

    // does the system percolate?
    public boolean percolates(){
        return grid.connected(0,n*n+1);
    }

    // test client (optional)
    public static void main(String[] args) {

    }
}
