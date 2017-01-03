import java.util.Arrays;

public class FastCollinearPoints {

    private Point[] points;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new NullPointerException();
        this.points = points;
        if (hasNullPointerInArray()) throw new NullPointerException();
        Arrays.sort(points);
    }

    private boolean hasNullPointerInArray() {
        for (Point point : points)
            if (point == null) return true;
        return false;
    }


    // the number of line segments
    public int numberOfSegments() {
        return 0;
    }

    // the line segments
    public LineSegment[] segments() {
        return null;
    }
}
