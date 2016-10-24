/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tasks;

/**
 *
 * @author milos
 */
public class Solver {

    public Solver(Board initial) {
        
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
}
