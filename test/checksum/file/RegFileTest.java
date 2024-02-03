package checksum.file;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegFileTest {
    @Test
    public void testGetSize() {
        RegFile file = new RegFile(Path.of(System.getProperty("user.dir" ) + "\\testOneFile\\what.txt"));

        assertEquals(file.getSize(), 68);
    }
}
