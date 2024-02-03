package checksum.file;

import checksum.Md5Calculator;
import checksum.visitor.HashStreamWriter;
import org.junit.jupiter.api.Test;

import java.io.StringWriter;
import java.io.Writer;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HashStreamWriterTest {
    @Test
    public void testVisitFile() {
        RegFile file = new RegFile(Path.of(System.getProperty("user.dir" ) + "\\testOneFile\\what.txt"));

        Writer writer = new StringWriter();

        HashStreamWriter hashStreamWriter = new HashStreamWriter(new Md5Calculator(), writer);

        hashStreamWriter.visitFile(file);

        assertEquals(writer.toString(), "02d163fad940850b70dfafaef30d7ef8 " +
                System.getProperty("user.dir" ) + "\\testOneFile\\what.txt" + System.lineSeparator());
    }

    @Test
    public void testVisitDirectory() {
        Directory directory = new Directory(Path.of(System.getProperty("user.dir" ) + "\\testWithDirectories"));

        Writer writer = new StringWriter();

        HashStreamWriter hashStreamWriter = new HashStreamWriter(new Md5Calculator(), writer);

        hashStreamWriter.visitDirectory(directory);

        List actual = Arrays.stream(writer.toString().split(System.lineSeparator())).toList();

        List expected = List.of("04b354432a04bc74b144c2ef084d2739 " +
                        System.getProperty("user.dir" ) +
                        "\\testWithDirectories\\nestedTestFolder\\nestedTestFolderLvl2\\smth.txt" ,
                "212bc61cab2141b50a4eb8e88a88ea8d " + System.getProperty("user.dir" ) +
                        "\\testWithDirectories\\nestedTestFolder\\abc1.txt" ,
                "d41d8cd98f00b204e9800998ecf8427e " +  System.getProperty("user.dir" ) +
                        "\\testWithDirectories\\nestedTestFolder\\idk.bmp" ,
                "d41d8cd98f00b204e9800998ecf8427e " +  System.getProperty("user.dir" ) +
                        "\\testWithDirectories\\nestedTestFolder\\smth.docx" ,
                "3f30af6eda98373b6406585d2c86ee28 " + System.getProperty("user.dir" ) +
                        "\\testWithDirectories\\nestedTestFolder\\Source.cpp",
                "0a040ec34abbfb7f3030345244a913c9 " + System.getProperty("user.dir" ) +
                        "\\testWithDirectories\\abc.txt");

        assertTrue(actual.containsAll(expected) && expected.containsAll(actual));
    }
}