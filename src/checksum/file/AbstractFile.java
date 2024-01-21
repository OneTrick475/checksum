package checksum.file;

import checksum.visitor.Visitor;

import java.nio.file.Path;

public abstract class AbstractFile {
    protected Path path;

    public Path getPath() {
        return path;
    }

    public abstract long getSize();

    public abstract void accept(Visitor v);
}
