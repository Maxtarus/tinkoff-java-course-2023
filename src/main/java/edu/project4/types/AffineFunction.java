package edu.project4.types;

import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;
import lombok.Getter;
import static java.lang.Math.pow;

@Getter
public class AffineFunction {
    private double a;
    private double b;
    private double c;
    private double d;
    private double e;
    private double f;
    private final Color rgb;
    private final static int COLORS_BOUND = 256;

    public AffineFunction() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        do {
            this.a = random.nextDouble(-1.0, 1.0);
            this.b = random.nextDouble(-1.0, 1.0);
            this.c = random.nextDouble(-1.0, 1.0);
            this.d = random.nextDouble(-1.0, 1.0);
            this.e = random.nextDouble(-1.0, 1.0);
            this.f = random.nextDouble(-1.0, 1.0);
        } while (!areCoefficientsCorrect());
        this.rgb = new Color(random.nextInt(COLORS_BOUND), random.nextInt(COLORS_BOUND), random.nextInt(COLORS_BOUND));
    }

    public Point transform(Point point) {
        return new Point(
            a * point.x() + b * point.y() + c,
            d * point.x() + e * point.y() + f
        );
    }

    private boolean areCoefficientsCorrect() {
        return ((pow(a, 2) + pow(d, 2)) < 1)
            && ((pow(b, 2) + pow(e, 2)) < 1)
            && ((pow(a, 2) + pow(b, 2) + pow(d, 2) + pow(e, 2)) < (1 + pow(a * e - b * d, 2)));
    }

}
