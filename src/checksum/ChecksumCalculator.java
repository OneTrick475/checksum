package checksum;

import java.io.InputStream;

public interface ChecksumCalculator {
    public String calculate(InputStream input);

}
