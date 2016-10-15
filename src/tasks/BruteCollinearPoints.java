/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author milos
 */
public class BruteCollinearPoints {

    private LineSegment[] segments;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new NullPointerException();
        }

        List<LineSegment> segmentsList = new ArrayList<>();

        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new NullPointerException();
            }
            for (int j = i + 1; j < points.length; j++) {
                double slopeIJ = points[i].slopeTo(points[j]);
                for (int k = j + 1; k < points.length; k++) {
                    double slopeIK = points[i].slopeTo(points[k]);
                    if (slopeIJ != slopeIK) {
                        continue;
                    }
                    for (int l = k + 1; l < points.length; l++) {
                        double slopeIL = points[i].slopeTo(points[l]);
                        if (points[i] == points[j] || points[i] == points[k] || points[i] == points[l]) {
                            throw new IllegalArgumentException();
                        }
                        if (slopeIJ == slopeIK && slopeIJ == slopeIL) {
                            Point[] slopedPoints = {points[i], points[j], points[k], points[l]};
                            Arrays.sort(slopedPoints);
                            LineSegment ls = new LineSegment(slopedPoints[0], slopedPoints[3]);
                            segmentsList.add(ls);
                        }
                    }
                }
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
}
