// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import checksum.Md5Calculator;
import checksum.file.Directory;
import checksum.observer.ProgressReporter;
import checksum.visitor.HashStreamWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        HashStreamWriter hashStreamWriter = new HashStreamWriter(new Md5Calculator(), new FileWriter("idk.txt"));
        ProgressReporter progressReporter = new ProgressReporter((int) new Directory(
                Path.of(System.getProperty("user.dir") + "\\testOneFile")).getSize());

        hashStreamWriter.subscribe(progressReporter);

        hashStreamWriter.visitDirectory(new Directory(Path.of(System.getProperty("user.dir" ) + "\\testWithDirectories")));
    }
}