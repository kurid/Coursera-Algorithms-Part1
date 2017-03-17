import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FastCollinearPoints {

    private final ArrayList<LineSegment> lineSegments;


    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new NullPointerException();
        lineSegments = new ArrayList<>();
        checkDuplicated(points);
        findLineSegments(points);
    }

    private void findLineSegments(Point[] points) {
        Arrays.sort(points);
        for (int i = 0; i < points.length; i++) {
            Point origin = points[i];

            Point others[] = Arrays.copyOf(points, points.length);
            Arrays.sort(others, origin.slopeOrder());

            Double currSlope = origin.slopeTo(others[0]);
            List<Point> collinearPoints = new ArrayList<>();
            collinearPoints.add(others[0]);
            for (int k = 1; k < others.length; k++) {
                Point point = others[k];
                double slopeTo = point.slopeTo(origin);
                if (currSlope != slopeTo) {
                    checkAndAdd(origin, collinearPoints);
                    currSlope = slopeTo;
                    collinearPoints = new ArrayList<>();
                    collinearPoints.add(others[k]);
                } else {
                    collinearPoints.add(others[k]);
                }
            }
            checkAndAdd(origin, collinearPoints);
        }
    }

    private void checkAndAdd(Point origin, List<Point> collinearPoints) {
        if (collinearPoints.size() >= 3) {
            Collections.sort(collinearPoints);
            if (collinearPoints.get(0).compareTo(origin) != -1) {
                printPoints(origin, collinearPoints);
                lineSegments.add(new LineSegment(origin, collinearPoints.get(collinearPoints.size() - 1)));
            }
        }
    }

    private void printPoints(Point origin, List<Point> collinearPoints) {
        System.out.println("=========================");
        System.out.print(origin +  " -- >");
        for(Point point : collinearPoints) {
            System.out.print(point +  " -- >");
        }
        System.out.println();
    }

    // the line segments
    private void checkDuplicated(Point[] points) {
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i] == null || points[j] == null)
                    throw new NullPointerException();

                if (points[i].compareTo(points[j]) == 0)
                    throw new IllegalArgumentException();
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
