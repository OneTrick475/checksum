package checksum.file;

import checksum.Md5Calculator;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Md5CalculatorTest {
    @Test
    public void testCalculate() {
        InputStream input = new ByteArrayInputStream("idk".getBytes());

        Md5Calculator md5Calculator = new Md5Calculator();

        assertEquals(md5Calculator.calculate(input), "d67077351ca6ebbf3b3baa770a4fbb58");
    }
}
