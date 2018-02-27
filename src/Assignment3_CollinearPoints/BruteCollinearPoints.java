package Assignment3_CollinearPoints;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {

    private List<LineSegment> lineSegments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new NullPointerException();
        lineSegments = new ArrayList<>();
        checkDuplicated(points);
        bruteForce(points);
    }

    private void bruteForce(Point[] points) {
        for (int p = 0; p < points.length - 3; p++) {
            for (int q = p + 1; q < points.length - 2; q++) {
                for (int r = q + 1; r < points.length - 1; r++) {
                    for (int s = r + 1; s < points.length; s++) {
                        double pqSlope = points[p].slopeTo(points[q]);
                        double prSlope = points[p].slopeTo(points[r]);
                        double psSlope = points[p].slopeTo(points[s]);
                        points[p].slopeOrder();
                        if (pqSlope == prSlope && pqSlope == psSlope) {
                            Point[] sortedPoints = new Point[4];
                            sortedPoints[0] = points[q];
                            sortedPoints[1] = points[r];
                            sortedPoints[2] = points[p];
                            sortedPoints[3] = points[s];
                            Arrays.sort(sortedPoints);
                            lineSegments.add(new LineSegment(sortedPoints[0], sortedPoints[3]));
                        }
                    }
                }
            }
        }
    }

    private void checkDuplicated(Point[] points) {
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException();
                }
            }
        }
    }


    // the number of line segments
    public int numberOfSegments() {
        return lineSegments.size();
    }

    public LineSegment[] segments() {
        return lineSegments.toArray(new LineSegment[lineSegments.size()]);
    }
}
