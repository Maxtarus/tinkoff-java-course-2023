package edu.project3.receiver.path;

import edu.project3.receiver.Receiver;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.nio.file.Path;
import java.util.List;
import static edu.project3.parser.pathparser.PathParser.getPaths;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PathLogReceiverTest {
    private final Path wrongPath = Path.of("src/main/resources/project3/log55.txt");
    @Test
    @DisplayName("Path receive logs test")
    public void receive_shouldReturnListOfStringsFromPathsToFiles() {
        Receiver receiver = new PathLogReceiver(getPaths("src/main/resources/project3/**"));
        assertThat(receiver.receive().size()).isEqualTo(171);
    }

    @Test
    @DisplayName("Path receive logs with wrong path test")
    public void receive_shouldThrowException_whenPathIsWrong() {
        Receiver receiver = new PathLogReceiver(List.of(wrongPath));
        assertThatThrownBy(receiver::receive).isInstanceOf(RuntimeException.class);
    }
}
