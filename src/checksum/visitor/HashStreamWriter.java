package checksum.visitor;

import checksum.ChecksumCalculator;
import checksum.file.Directory;
import checksum.file.RegFile;
import checksum.observer.Observable;
import checksum.observer.Observer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.file.Files;
import java.util.ArrayDeque;
import java.util.Queue;

public class HashStreamWriter extends Observable implements Visitor {
    private ChecksumCalculator calculator;
    private Writer writer;

    private boolean isScanning;

    private Queue<File> files = new ArrayDeque<>();

    public HashStreamWriter(ChecksumCalculator calculator, Writer writer) {
        this.calculator = calculator;
        this.writer = writer;
    }

    public HashStreamMemento createSnapshot(HashStreamWriter hashStreamWriter, boolean isScanning, Queue<File> files) {
        return new HashStreamMemento(this, isScanning, files);
    }

    public void setState(boolean isScanning, Queue<File> files) {
        this.isScanning = isScanning;
        this.files = files;
    }

    @Override
    public void visitFile(RegFile file) {
        try (InputStream is = Files.newInputStream(file.getPath())) {
            notifyObservers(file.getPath().toString());

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
        isScanning = true;

        File dir = new File(directory.getPath().toString());

        for (File file : dir.listFiles()) {
            files.add(file);
        }

        while (!files.isEmpty()) {
            File file = files.poll();

            if (file.isFile()) {
                visitFile(new RegFile(file.toPath()));
            } else {
                visitDirectory(new Directory(file.toPath()));
            }
        }

        isScanning = false;
    }

    @Override
    public void subscribe(Observer observer) {
        // Call the parent function to register
        // observer for our own object.
        super.subscribe(observer);

        // Attach observer to the calculator too.
        calculator.subscribe(observer);
    }

}
