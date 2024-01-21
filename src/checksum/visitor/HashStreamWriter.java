package checksum.visitor;

import checksum.ChecksumCalculator;
import checksum.file.Directory;
import checksum.file.RegFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.file.Files;

public class HashStreamWriter implements Visitor {
    private ChecksumCalculator calculator;
    private Writer writer;

    public HashStreamWriter(ChecksumCalculator calculator, Writer writer) {
        this.calculator = calculator;
        this.writer = writer;
    }

    @Override
    public void visitFile(RegFile file) {
        try (InputStream is = Files.newInputStream(file.getPath())) {
            writer.write(calculator.calculate(is));

            writer.write(" ");

            writer.write(file.getPath().toString());

            writer.write(System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void visitDirectory(Directory directory) {
        File dir = new File(directory.getPath().toString());

        for (File file : dir.listFiles()) {
            if (file.isFile()) {
                visitFile(new RegFile(file.toPath()));
            } else {
                visitDirectory(new Directory(file.toPath()));
            }
        }
    }
}
