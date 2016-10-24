/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tasks;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;
import java.util.Comparator;

/**
 *
 * @author milos
 */
public class Solver {

    private Node solutionNode;
    
    public Solver(Board initial) {
        MinPQ<Node> priorityQueue = new MinPQ<>(nodeComparator);
        MinPQ<Node> twinPriorityQueue = new MinPQ<>(nodeComparator);
        
        
    }

    public boolean isSolvable() {
        return false;
    }

    public int moves() {
        return 0;
    }

    public Iterable<Board> solution() {
        return null;
    }

    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                blocks[i][j] = in.readInt();
            }
        }
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable()) {
            StdOut.println("No solution possible");
        } else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution()) {
                StdOut.println(board);
            }
        }
    }

    private class Node {

        private Board board;
        private int movesMade;
        private Node parent;

        public Node(Board board, int movesMade, Node parent) {
            this.board = board;
            this.movesMade = movesMade;
            this.parent = parent;
        }
    }

    private Comparator<Node> nodeComparator = new Comparator<Solver.Node>() {

        @Override
        public int compare(Node o1, Node o2) {
            return o1.board.manhattan() - o2.board.manhattan();
        }
    };      
}
