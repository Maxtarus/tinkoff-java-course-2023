package edu.project4.transformations;

import edu.project4.types.Point;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.tan;

public class TangentTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        return new Point(sin(point.x()) / cos(point.y()), tan(point.y()));
    }
}
