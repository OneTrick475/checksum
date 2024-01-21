package checksum.file;

import checksum.visitor.Visitor;

import java.io.File;
import java.nio.file.Path;

public class RegFile extends AbstractFile {
    public RegFile(Path path) {
        this.path = path;
    }

    @Override
    public long getSize() {
        return new File(path.toString()).length();
    }

    @Override
    public void accept(Visitor v) {
        v.visitFile(this);
    }
}
