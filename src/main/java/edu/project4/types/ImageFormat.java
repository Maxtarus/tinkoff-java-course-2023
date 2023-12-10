package edu.project4.types;

public enum ImageFormat {
    JPEG, BMP, PNG;

    public String getType() {
        return switch (this) {
            case JPEG -> "jpeg";
            case BMP -> "bmp";
            case PNG -> "png";
        };
    }
}
