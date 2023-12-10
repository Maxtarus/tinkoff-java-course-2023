package edu.project4.utils;

import edu.project4.types.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PointUtilsTest {

    @Test
    @DisplayName("Тестирование метода rotate")
    public void rotatePoint_shouldReturnCorrectPoint() {
        Point point = new Point(0, 5);
        double angle = Math.PI / 2;
        Point expected = new Point(5, 0);
        Point actual = PointUtils.rotate(point, angle);
        assertTrue((int) expected.x() == (int) actual.x()
        && (int) expected.y() == (int) actual.y());
    }

}
