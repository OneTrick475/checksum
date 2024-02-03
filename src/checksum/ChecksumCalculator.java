package checksum;

import checksum.observer.Observable;

import java.io.InputStream;

public abstract class ChecksumCalculator extends Observable {
    public abstract String calculate(InputStream input);

}
