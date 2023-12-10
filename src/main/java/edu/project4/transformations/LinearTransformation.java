package edu.project4.transformations;

import edu.project4.types.Point;

public class LinearTransformation  implements Transformation {
    @Override
    public Point apply(Point point) {
        return point;
    }
}
