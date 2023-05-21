package RTreesF3.Algorithms;

import RTreesF3.Entities.Point;

import java.util.List;

public class PointDistance {

    public static double distanceBetweenPoints(Point point1, Point point2) {
        return Math.sqrt(Math.pow(point2.x - point1.x, 2) + Math.pow(point2.y - point1.y, 2));
    }

    public static Point[] getFarthestPoints(List<Point> points) {
        double distance;
        double maxDistance = 0;
        Point[] farthestPoints = new Point[2];

        // Calculate the distances between all the points.
        for (int i = 0; i < points.size() - 1; i++) {
            for (int j = i + 1; j < points.size(); j++) {
                distance = distanceBetweenPoints(points.get(i), points.get(j));

                if (distance > maxDistance) {
                    maxDistance = distance;

                    farthestPoints[0] = points.get(i);
                    farthestPoints[1] = points.get(j);
                }
            }
        }

        return farthestPoints;
    }
}
