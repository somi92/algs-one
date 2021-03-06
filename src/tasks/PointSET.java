/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tasks;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author milos
 */
public class PointSET {

    private SET<Point2D> points;
    private int size;

    public PointSET() {
        points = new SET<Point2D>();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return points.size();
    }

    public void insert(Point2D p) {
        if (p == null) {
            throw new NullPointerException();
        }
        if (!points.contains(p)) {
            points.add(p);
        }
    }

    public boolean contains(Point2D p) {
        if (p == null) {
            throw new NullPointerException();
        }
        return points.contains(p);
    }

    public void draw() {
        for (Point2D point : points) {
            point.draw();
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new NullPointerException();
        }
        List<Point2D> pointsInRect = new ArrayList<>();
        for (Point2D point : points) {
            if (rect.contains(point)) {
                pointsInRect.add(point);
            }
        }
        return pointsInRect;
    }

    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new NullPointerException();
        }
        Point2D nearestPoint = null;
        for (Point2D point : points) {

            if (nearestPoint == null) {
                nearestPoint = point;
            } else {
                if (p.distanceSquaredTo(point) < p.distanceSquaredTo(nearestPoint)) {
                    nearestPoint = point;
                }
            }
        }
        return nearestPoint;
    }

//    public static void main(String[] args) {
//
//    }
}
