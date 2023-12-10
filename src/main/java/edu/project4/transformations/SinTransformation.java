package edu.project4.transformations;

import edu.project4.types.Point;
import static java.lang.Math.sin;

public class SinTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        return new Point(sin(point.x()), sin(point.y()));
    }
}
