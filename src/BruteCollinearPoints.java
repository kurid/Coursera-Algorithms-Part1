import java.util.ArrayList;
import java.util.List;

public class BruteCollinearPoints {

    private Point[] points;

    private List<LineSegment> lineSegments;

    private LineSegment[] lineSegmentsResult;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new NullPointerException();
        this.points = points;
        lineSegments = new ArrayList<>();
        checkDuplicated();
        bruteForce();
        lineSegmentsResult = lineSegments.toArray(new LineSegment[lineSegments.size()]);

    }

    private void bruteForce() {
        for (int p = 0; p < points.length - 3; p++) {
            for (int q = 0; q < points.length - 2; q++) {
                for (int r = 0; r < points.length - 1; r++) {
                    for (int s = 0; s < points.length; s++) {
                        double pqSlope = points[p].slopeTo(points[q]);
                        double prSlope = points[p].slopeTo(points[r]);
                        double psSlope = points[p].slopeTo(points[s]);
                        if(pqSlope == prSlope && pqSlope == psSlope){
                            lineSegments.add(new LineSegment(points[p], points[s]));
                        }
                    }
                }
            }
        }
    }

    private void checkDuplicated() {
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0) {
                    throw new IllegalStateException();
                }
            }
        }
    }


    // the number of line segments
    public int numberOfSegments() {
        return lineSegments.size();
    }

    public LineSegment[] segments() {
        return lineSegmentsResult;
    }
}
