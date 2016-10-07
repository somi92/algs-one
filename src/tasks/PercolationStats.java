package tasks;


import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author milos
 */
public class PercolationStats {

    private double[] results;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        this.results = new double[trials];
        for (int i = 0; i < this.results.length; i++) {
            results[i] = runTrial(n);
        }
    }

    public double mean() {
        return StdStats.mean(results);
    }

    public double stddev() {
        return StdStats.stddev(results);
    }

    public double confidenceLo() {
        return mean() - margin();
    }

    public double confidenceHi() {
        return mean() + margin();
    }

    private double margin() {
        return (1.96 * stddev()) / results.length;
    }

    private double runTrial(int n) {
        Percolation percolation = new Percolation(n);
        int openSites = 0;
        while (percolation.percolates() == false) {
            int i = StdRandom.uniform(1, n + 1);
            int j = StdRandom.uniform(1, n + 1);
            if (percolation.isOpen(i, j)) {
                continue;
            }
            percolation.open(i, j);
            openSites++;
        }
        return (double) openSites / (n * n);
    }

//    public static void main(String[] args) {
//        int n = 100; //Integer.parseInt(args[0]);
//        int trials = 100; //Integer.parseInt(args[1]);
//        
//        Stopwatch sw = new Stopwatch();
//        PercolationStats stats = new PercolationStats(n, trials);
//        System.out.printf("time elapsed            = %f \n\n", sw.elapsedTime());
//        
//        System.out.printf("mean                    = %f \n", stats.mean());
//        System.out.printf("stddev                  = %f \n", stats.stddev());
//        System.out.printf("95%% confidence interval = %f, %f \n",
//                stats.confidenceLo(), stats.confidenceHi());
//    }
}
