package checksum.file;

import checksum.visitor.Visitor;

import java.io.File;
import java.nio.file.Path;

public class Directory extends AbstractFile {
    public Directory(Path path) {
        this.path = path;
    }

    private long getDirectorySize(File directory) {
        long length = 0;

        for (File file : directory.listFiles()) {
            if (file.isDirectory()) {
                length += getDirectorySize(file);
            } else if (file.isFile()) {
                length += file.length();
            }
        }
        return length;
    }

    @Override
    public long getSize() {
        File directory = new File(path.toString());

        return getDirectorySize(directory);
    }

    @Override
    public void accept(Visitor v) {
        v.visitDirectory(this);
    }
}
