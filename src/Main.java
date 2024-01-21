import checksum.Md5Calculator;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Md5Calculator md = new Md5Calculator();

        InputStream inputStream = new ByteArrayInputStream("abc".getBytes());

        System.out.println(md.calculate(inputStream));
    }
}