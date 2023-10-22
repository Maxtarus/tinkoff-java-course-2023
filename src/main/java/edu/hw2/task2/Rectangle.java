package edu.hw2.task2;

public class Rectangle {
    private final int width;
    private final int height;

    public Rectangle(int width, int height) {
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException("Ширина и высота прямоугольника должны быть больше нуля!");
        }

        this.width = width;
        this.height = height;
    }

    public Rectangle() {
        this(0, 0);
    }

    public Rectangle setWidth(int width) {
        return new Rectangle(width, this.height);
    }

    public final Rectangle setHeight(int height) {
        return new Rectangle(this.width, height);
    }

    public double area() {
        return width * height;
    }
}
