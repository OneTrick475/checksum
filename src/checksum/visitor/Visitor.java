package checksum.visitor;

import checksum.file.Directory;
import checksum.file.RegFile;

public interface Visitor {
    void visitFile(RegFile file);

    void visitDirectory(Directory directory);
}
