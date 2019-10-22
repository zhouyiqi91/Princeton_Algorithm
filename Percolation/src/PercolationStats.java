/* *****************************************************************************
 *  Name:              Yiqi Zhou
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int trials;
    private double[] thresh;
    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials){
        if (n<1 || trials<1) throw new IllegalArgumentException("Illegal n or trialNum,please check");
        this.trials = trials;
        thresh = new double[trials];
        for(int i=0;i<trials;i++) {
            Percolation p = new Percolation(n);
            while(!p.percolates()) {
                int row = StdRandom.uniform(n)+1;
                int col = StdRandom.uniform(n)+1;
                p.open(row,col);
                if (p.percolates()) break;
            }
            thresh[i] = (double)p.numberOfOpenSites()/(n*n);
        }
    }

    // sample mean of percolation threshold
    public double mean(){
        return StdStats.mean(thresh);
    }

    // sample standard deviation of percolation threshold
    public double stddev(){
        return StdStats.stddev(thresh);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo(){
        return mean()-1.96*stddev()/Math.sqrt(trials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(trials);
    }
    // test client (see below)
    public static void main(String[] args) {

    }
}
