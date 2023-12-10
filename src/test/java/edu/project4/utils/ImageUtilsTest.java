package edu.project4.utils;

import edu.project4.types.FractalImage;
import edu.project4.types.ImageFormat;
import java.nio.file.Path;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.assertj.core.api.Assertions.assertThat;

class ImageUtilsTest {
    @Test
    @DisplayName("Тестирование FormatImageSaver#save")
    public void save_shouldSaveImage(@TempDir Path tempDir) {
        FractalImage image = FractalImage.create(10, 10);
        ImageUtils.save(image, tempDir, "test", ImageFormat.PNG);
        assertThat(Path.of(tempDir.toString(), "test.png")).exists();
    }

}
