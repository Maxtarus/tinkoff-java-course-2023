package edu.project4.utils;

import edu.project4.types.Point;
import lombok.experimental.UtilityClass;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

@UtilityClass
public class PointUtils {
    public static Point rotate(Point point, double radians) {
        return new Point(
            point.x() * cos(radians) + point.y() * sin(radians),
            -point.x() * sin(radians) + point.y() * cos(radians)
        );
    }
}
