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
import java.util.Stack;

/**
 *
 * @author milos
 */
public class Solver {

    private Node solutionNode;

    public Solver(Board initial) {
        if (initial == null) {
            throw new NullPointerException();
        }
        MinPQ<Node> priorityQueue = new MinPQ<>(nodeComparator);
        MinPQ<Node> twinPriorityQueue = new MinPQ<>(nodeComparator);

        Node initialNode = new Node(initial, 0, null);
        priorityQueue.insert(initialNode);

        Node twinInitialNode = new Node(initial.twin(), 0, null);
        twinPriorityQueue.insert(twinInitialNode);

        while (true) {
            solutionNode = processQueue(priorityQueue);
            if (solutionNode != null || processQueue(twinPriorityQueue) != null) {
                return;
            }
        }
    }

    public boolean isSolvable() {
        return solutionNode != null;
    }

    public int moves() {
        return isSolvable() ? solutionNode.movesMade : -1;
    }

    public Iterable<Board> solution() {
        if (!isSolvable()) {
            return null;
        }
        Stack<Board> nodes = new Stack<>();
        while (solutionNode != null) {
            nodes.push(solutionNode.board);
            solutionNode = solutionNode.parent;
        }
        return nodes;
    }

    public static void main(String[] args) {
        // create initial board from file
//        In in = new In(args[0]);
        In in = new In("puzzle3x3-unsolvable.txt");
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

    private Node processQueue(MinPQ<Node> queue) {
        if (queue.isEmpty()) {
            return null;
        }
        Node minNode = queue.delMin();
        if (minNode.board.isGoal()) {
            return minNode;
        }
        for (Board neighbor : minNode.board.neighbors()) {
            if (minNode.parent == null || !neighbor.equals(minNode.parent.board)) {
                Node newNode = new Node(neighbor, minNode.movesMade + 1, minNode);
                queue.insert(newNode);
            }
        }
        return null;
    }
}
