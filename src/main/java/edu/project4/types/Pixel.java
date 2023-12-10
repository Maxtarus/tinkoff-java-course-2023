package edu.project4.types;

import java.awt.Color;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pixel {
    int r;
    int g;
    int b;
    int hitCount;
    double correctionValue;

    public synchronized void updatePixel(Color color) {
        setColor(color);
        hitCount++;
    }

    private synchronized void setColor(Color color) {
        if (hitCount == 0) {
            this.r = color.getRed();
            this.g = color.getGreen();
            this.b = color.getBlue();
        } else {
            this.r = (r + color.getRed()) / 2;
            this.g = (g + color.getGreen()) / 2;
            this.b = (b + color.getBlue()) / 2;
        }
    }
}
