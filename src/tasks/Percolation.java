package tasks;


import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author milos
 */
public class Percolation {
    
    private final WeightedQuickUnionUF alg;
    private final WeightedQuickUnionUF algWithBackwash;
    private final int virtualTop;
    private final int virtualBottom;
    private final int n;
    private final int[] openSites;
    
    private static final int BLOCKED = 0;
    private static final int OPEN = 1;
    
    public Percolation(int n) {
        if(n <= 0)
            throw new IllegalArgumentException("n cannot be less or equal to zero");
        this.n = n;
        int oneDsize = n*n;
        alg = new WeightedQuickUnionUF(oneDsize + 1);
        algWithBackwash = new WeightedQuickUnionUF(oneDsize + 2);
        virtualTop = oneDsize;
        virtualBottom = oneDsize + 1;
        this.openSites = new int[n*n];
        for(int i=0; i<oneDsize; i++) {
            this.openSites[i] = BLOCKED;
            if(i < n) {
                alg.union(virtualTop, i);
                algWithBackwash.union(i, virtualTop);
            }
            if(i >= (n * n - n) && i < n * n) {
                algWithBackwash.union(i, virtualBottom);
            }
        }
    }
    
    public void open(int i, int j) {
        validate(i);
        validate(j);
        int siteId = map2Dto1D(i, j);
        openSites[siteId] = 1;
        if(i < n && openSites[map2Dto1D(i + 1, j)] == OPEN) {
            alg.union(siteId, map2Dto1D(i + 1, j));
            algWithBackwash.union(siteId, map2Dto1D(i + 1, j));
        }
        if(i > 1 && openSites[map2Dto1D(i - 1, j)] == OPEN) {
            alg.union(siteId, map2Dto1D(i - 1, j));
            algWithBackwash.union(siteId, map2Dto1D(i - 1, j));
        }
        if(j < n && openSites[map2Dto1D(i, j + 1)] == OPEN) {
            alg.union(siteId, map2Dto1D(i, j + 1));
            algWithBackwash.union(siteId, map2Dto1D(i, j + 1));
        }
        if(j > 1 && openSites[map2Dto1D(i, j - 1)] == OPEN) {
            alg.union(siteId, map2Dto1D(i, j - 1));
            algWithBackwash.union(siteId, map2Dto1D(i, j - 1));
        }
    }
    
    public boolean isOpen(int i, int j) {
        validate(i);
        validate(j);
        int siteId = map2Dto1D(i, j);
        return OPEN == openSites[siteId];
    }
    
    public boolean isFull(int i, int j) {
        validate(i);
        validate(j);
        int siteId = map2Dto1D(i, j);
        return OPEN == openSites[siteId] && alg.connected(siteId, virtualTop);
    }
    
    public boolean percolates() {
        return algWithBackwash.connected(virtualTop, virtualBottom);
    }
    
    private int map2Dto1D(int i, int j) {
        int oneDcoor = (i * n - 1) - (n -j);
        return oneDcoor;
    }
    
    private void validate(int value) {
        if(value <= 0 || value > n)
            throw new IndexOutOfBoundsException("row index i out of bounds");
    }
    
    public static void main(String[] args) {
    }
}