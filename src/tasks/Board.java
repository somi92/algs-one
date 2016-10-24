/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tasks;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author milos
 */
public class Board {

    private int[][] matrix;
    private int n;

    public Board(int[][] matrix) {
        this.n = matrix.length;
        this.matrix = new int[matrix.length][matrix.length];
        for (int row = 0; row < this.matrix.length; row++) {
            for (int col = 0; col < this.matrix.length; col++) {
                this.matrix[row][col] = matrix[row][col];
            }
        }
    }

    public int dimension() {
        return n;
    }

    public int hamming() {
        int priority = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int value = matrix[i][j];
                int correctValue
                        = (i + 1 == n) && (j + 1 == n)
                                ? 0 : (((i + 1) * n) - (n - (j + 1)));
                priority = priority
                        + (correctValue == value || value == 0 ? 0 : 1);
            }
        }
        return priority;
    }

    public int manhattan() {
        int priority = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int value = matrix[i][j];
                int rowVal = (value - 1) / n;
                int colVal = (value - 1) - (rowVal * n);
                int distance = (value == 0)
                        ? 0 : Math.abs(i - rowVal) + Math.abs(j - colVal);
                priority = priority + distance;
            }
        }
        return priority;
    }

    public boolean isGoal() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int value = matrix[i][j];
                int correctValue
                        = (i + 1 == n) && (j + 1 == n)
                                ? 0 : (((i + 1) * n) - (n - (j + 1)));
                if (value != correctValue) {
                    return false;
                }
            }
        }
        return true;
    }

    public Board twin() {
        int[][] newMatrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                newMatrix[i][j] = matrix[i][j];
            }
        }
        int tempIndex = 0;
        if (newMatrix[tempIndex][n - 2] == 0 || newMatrix[tempIndex][n - 1] == 0) {
            tempIndex = 1;
        }
        int tempVal = newMatrix[tempIndex][n - 2];
        newMatrix[tempIndex][n - 2] = newMatrix[tempIndex][n - 1];
        newMatrix[tempIndex][n - 1] = tempVal;
        return new Board(newMatrix);
    }

    public boolean equals(Object y) {
        if (y == null) {
            return false;
        }
        if (this.getClass() != y.getClass()) {
            return false;
        }
        if (this == y) {
            return true;
        }
        Board that = (Board) y;
        if (this.dimension() != that.dimension()) {
            return false;
        }
        for (int i = 0; i < this.dimension(); i++) {
            for (int j = 0; j < this.dimension(); j++) {
                if (this.matrix[i][j] != that.matrix[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public Iterable<Board> neighbors() {
        int emptyRow = 0;
        int emptyCol = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    emptyRow = i;
                    emptyCol = j;
                    break;
                }
            }
        }

        List<Board> neighbors = new ArrayList<>();

        // up
        if (emptyRow > 0) {
            int[][] upNeighbor = createDuplicateMatrix();

            int tempVal = upNeighbor[emptyRow][emptyCol];
            upNeighbor[emptyRow][emptyCol] = upNeighbor[emptyRow - 1][emptyCol];
            upNeighbor[emptyRow - 1][emptyCol] = tempVal;

            neighbors.add(new Board(upNeighbor));
        }

        // down
        if (emptyCol < n - 1) {
            int[][] downNeighbor = createDuplicateMatrix();

            int tempVal = downNeighbor[emptyRow][emptyCol];
            downNeighbor[emptyRow][emptyCol] = downNeighbor[emptyRow + 1][emptyCol];
            downNeighbor[emptyRow + 1][emptyCol] = tempVal;

            neighbors.add(new Board(downNeighbor));
        }

        //left
        if (emptyCol > 0) {
            int[][] leftNeighbor = createDuplicateMatrix();

            int tempVal = leftNeighbor[emptyRow][emptyCol];
            leftNeighbor[emptyRow][emptyCol] = leftNeighbor[emptyRow][emptyCol - 1];
            leftNeighbor[emptyRow][emptyCol - 1] = tempVal;

            neighbors.add(new Board(leftNeighbor));
        }

        // right
        if (emptyCol < n - 1) {
            int[][] rightNeighbor = createDuplicateMatrix();

            int tempVal = rightNeighbor[emptyRow][emptyCol];
            rightNeighbor[emptyRow][emptyCol] = rightNeighbor[emptyRow][emptyCol + 1];
            rightNeighbor[emptyRow][emptyCol + 1] = tempVal;

            neighbors.add(new Board(rightNeighbor));
        }

        return neighbors;
    }

    public String toString() {
        StringBuilder sbuilder = new StringBuilder(n + " \n ");
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                sbuilder.append(matrix[i][j]);
                sbuilder.append(" ");
            }
            sbuilder.append("\n ");
        }

        return sbuilder.toString();
    }

    private int[][] createDuplicateMatrix() {
        int[][] newMatrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                newMatrix[i][j] = matrix[i][j];
            }
        }
        return newMatrix;
    }
}
