/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tasks;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

/**
 *
 * @author milos
 */
public class KdTree {

    private static final int X_COOR = 1;
    private static final int Y_COOR = 2;

    private Node root;
    private int size;

    public KdTree() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size;
    }

    public void insert(Point2D p) {
        Node currentNode = root;
        Node newNode = new Node(p);
        
        while (currentNode != null) {
            if (currentNode.point.equals(newNode.point)) {
                return;
            }

            if (currentNode.type == X_COOR) {
                
                if (currentNode.point.x() < newNode.point.x()) {
                    // go to the left
                    if(currentNode.left == null) {
                        // add new node to the left, increment size
                        newNode.type = Y_COOR;
                        currentNode.left = newNode;
                        size++;
                        return;
                    }
                    currentNode = currentNode.left;
                } else {
                    // go to the right
                    if(currentNode.right == null) {
                        // add new node to the right, increment size
                        newNode.type = Y_COOR;
                        currentNode.right = newNode;
                        size++;
                        return;
                    }
                    currentNode = currentNode.right;
                }
            }

            if (currentNode.type == Y_COOR) {
                
                if (currentNode.point.y() < newNode.point.y()) {
                    // go to the left
                    if(currentNode.left == null) {
                        // add new node to the left, increment size
                        newNode.type = X_COOR;
                        currentNode.left = newNode;
                        size++;
                        return;
                    }
                    currentNode = currentNode.left;
                } else {
                    // go to the right
                    if(currentNode.right == null) {
                        // add new node to the right, increment size
                        newNode.type = X_COOR;
                        currentNode.right = newNode;
                        size++;
                        return;
                    }
                    currentNode = currentNode.right;
                }
            }
        }
        
        currentNode = newNode;
        size++;
    }

    public boolean contains(Point2D p) {
        Node currentNode = root;

        while (currentNode != null) {

            if (currentNode.point.equals(p)) {
                return true;
            }

            if (currentNode.type == X_COOR) {
                currentNode = currentNode.point.x() < p.x()
                        ? currentNode.left : currentNode.right;
            }

            if (currentNode.type == Y_COOR) {
                currentNode = currentNode.point.y() < p.y()
                        ? currentNode.left : currentNode.right;
            }
        }
        return false;
    }

    public void draw() {

    }

    public Iterable<Point2D> range(RectHV rect) {

        return null;
    }

    public Point2D nearest(Point2D p) {

        return null;
    }

    private static class Node {

        private Point2D point;
        private Node left;
        private Node right;
        private int type;

        public Node(Point2D point) {
            this.point = point;
            left = null;
            right = null;
            type = X_COOR;
        }

        public Node(Point2D point, Node left, Node right, int type) {
            this.point = point;
            this.left = left;
            this.right = right;
            this.type = type;
        }
    }

//    public static void main(String[] args) {
//
//    }
}
