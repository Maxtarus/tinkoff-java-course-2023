package edu.project4.utils;

import edu.project4.types.FractalImage;
import edu.project4.types.ImageFormat;
import edu.project4.types.Pixel;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;
import javax.imageio.ImageIO;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ImageUtils {

    @SneakyThrows
    public static void save(FractalImage image, Path filename, String name, ImageFormat format) {
        BufferedImage bufferedImage =
            new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        File outputfile = new File(Path.of(filename.toString(), name + "." + format.getType()).toString());

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Pixel pixel = image.pixel(x, y);
                Color color = new Color(pixel.getR(), pixel.getG(), pixel.getB());
                bufferedImage.setRGB(x, y, color.getRGB());
            }
        }

        ImageIO.write(bufferedImage, format.getType(), outputfile);
    }

}
