package checksum.visitor;

import checksum.ChecksumCalculator;
import checksum.file.Directory;
import checksum.file.RegFile;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

public class ReportWriter implements Visitor {
    private Writer writer;

    public ReportWriter(Writer writer) {
        this.writer = writer;
    }

    @Override
    public void visitFile(RegFile file) {
        try {
            writer.write(file.getPath().toString());

            writer.write(" ");

            writer.write((int)file.getSize());

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
