package edu.project4.types;

import java.util.concurrent.ThreadLocalRandom;

public record Rect(double x, double y, double width, double height) {
    public boolean isContain(Point p) {
        return (p.x() > x && p.x() < (width) && p.y() > y && p.y() < (height));
    }

    public Point getRandom() {
        return new Point(
            ThreadLocalRandom.current().nextDouble(x, width),
            ThreadLocalRandom.current().nextDouble(y, height)
        );
    }
}
