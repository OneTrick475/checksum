package checksum.visitor;

import checksum.ChecksumCalculator;

import java.io.File;
import java.io.Writer;
import java.util.ArrayDeque;
import java.util.Queue;

public class HashStreamMemento {
    private HashStreamWriter hashStreamWriter;

    private final boolean isScanning;

    private final Queue<File> files;

    public HashStreamMemento(HashStreamWriter hashStreamWriter, boolean isScanning, Queue<File> files) {
        this.hashStreamWriter = hashStreamWriter;
        this.isScanning = isScanning;
        this.files = files;
    }

    public void restore() {
        hashStreamWriter.setState(isScanning, files);
    }
}
