/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tasks;

import java.util.ArrayList;
import static java.util.Arrays.sort;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author milos
 */
public class FastCollinearPoints {

    private LineSegment[] segments;

    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new NullPointerException();
        }
        
        List<LineSegment> segmentsList = new ArrayList<>();
        for (int p = 0; p < points.length - 1; p++) {
            if (points[p] == null) {
                throw new NullPointerException();
            }

            List<Point> slopedPoints = new ArrayList<>();
            sort(points, p + 1, points.length, points[p].slopeOrder());
            Point root = points[p];
            double slope = root.slopeTo(points[p + 1]);
            slopedPoints.add(points[p + 1]);
            for (int i = p + 2; i < points.length; i++) {
                if (root.slopeTo(points[i]) == slope) {
                    if (slopedPoints.isEmpty()) {
                        slopedPoints.add(points[i - 1]);
                    }
                    slopedPoints.add(points[i]);
                } else {
                    if (slopedPoints.size() >= 3) {
                        slopedPoints.add(root);
                        Collections.sort(slopedPoints);
                        addLineSegment(segmentsList, new LineSegment(slopedPoints.get(0), slopedPoints.get(slopedPoints.size() - 1)));
                    }
                    slopedPoints = new ArrayList<>();
                }
                slope = root.slopeTo(points[i]);
            }
            if (slopedPoints.size() >= 3) {
                slopedPoints.add(root);
                Collections.sort(slopedPoints);
                addLineSegment(segmentsList, new LineSegment(slopedPoints.get(0), slopedPoints.get(slopedPoints.size() - 1)));
            }
        }
        segments = segmentsList.toArray(new LineSegment[segmentsList.size()]);
    }

    public int numberOfSegments() {
        return segments.length;
    }

    public LineSegment[] segments() {
        return segments;
    }

    private void addLineSegment(List<LineSegment> segments, LineSegment newSegment) {
        segments.add(newSegment);
    }

    private void displayOrder(Point point, Point[] points) {
        System.out.print(point.toString() + " => ");
        for (Point p : points) {
            System.out.print("(" + point.slopeTo(p) + ") " + p.toString() + "; ");
        }
        System.out.println("\n");
    }

    private void displayList(Point p, List<Point> points) {
        System.out.println(p.toString());
        for (Point point : points) {
            System.out.print(point.toString() + " ");
        }
        System.out.println("");;
    }
}
