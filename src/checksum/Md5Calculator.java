package checksum;

import checksum.observer.Observable;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class Md5Calculator extends ChecksumCalculator {
    @Override
    public String calculate(InputStream input) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        DigestInputStream dis = new DigestInputStream(input, md);

        try {
            int bytes = 0;
            while (dis.read() != -1) {
                bytes++;
                if (bytes % 1024 == 0) {
                    notifyObservers(bytes);
                }
            }
            notifyObservers(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        byte[] digest = md.digest();

        String output = String.format("%032X", new BigInteger(1, digest));
        return output.toLowerCase();
    }
}
