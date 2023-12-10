package edu.project4.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class FractalImage {
    Pixel[] data;
    @Getter
    int width;
    @Getter
    int height;

    public static FractalImage create(int width, int height) {
        Pixel[] data = new Pixel[width * height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                data[width * y + x] = new Pixel(0, 0, 0, 0, 0);
            }
        }
        return new FractalImage(data, width, height);
    }

    public boolean isContain(int x, int y) {
        return (x <= width && x >= 0 && y <= height && y >= 0);
    }

    public Pixel pixel(int x, int y) {
        if (isContain(x, y)) {
            return data[width * y + x];
        }
        throw new RuntimeException("Введены неверные координаты");
    }
}
