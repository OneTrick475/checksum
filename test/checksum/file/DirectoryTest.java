package checksum.file;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DirectoryTest {
    @Test
    public void testGetSize() {
        Directory file = new Directory(Path.of(System.getProperty("user.dir" ) + "\\testWithDirectories"));

        assertEquals(file.getSize(), 432);
    }
}
