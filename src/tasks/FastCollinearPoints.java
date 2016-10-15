/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tasks;

import java.util.ArrayList;
import java.util.Arrays;
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

        for (int p = 0; p < points.length; p++) {

            if (points[p] == null) {
                throw new NullPointerException();
            }

            List<Point> slopedPoints = new ArrayList<>();
            Point[] temp = Arrays.copyOf(points, points.length);
//            Point[] temp = Arrays.copyOfRange(points, p, points.length);
            sort(temp, points[p].slopeOrder());
            Point root = temp[0];
            double slope = root.slopeTo(temp[1]);
            slopedPoints.add(temp[1]);
            for (int i = 2; i < temp.length; i++) {
                if (root.slopeTo(temp[i]) == slope) {
                    if (slopedPoints.isEmpty()) {
                        slopedPoints.add(temp[i - 1]);
                    }
                    slopedPoints.add(temp[i]);
                } else {
                    if (slopedPoints.size() >= 3) {
                        Collections.sort(slopedPoints, root.slopeOrder());
                        addLineSegment(segmentsList, new LineSegment(root, slopedPoints.get(slopedPoints.size() - 1)));
                        slopedPoints = new ArrayList<>();
                    } else {
                        slopedPoints = new ArrayList<>();
                    }
                }
                slope = root.slopeTo(temp[i]);
            }

            if (slopedPoints.size() >= 3) {
                Collections.sort(slopedPoints, root.slopeOrder());
                addLineSegment(segmentsList, new LineSegment(root, slopedPoints.get(slopedPoints.size() - 1)));
                slopedPoints = new ArrayList<>();
            } else {
                slopedPoints = new ArrayList<>();
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
}
