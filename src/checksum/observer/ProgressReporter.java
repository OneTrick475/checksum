package checksum.observer;

import checksum.ChecksumCalculator;
import checksum.visitor.HashStreamWriter;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ProgressReporter implements Observer {
    private String currentPath = "(nothing)";
    private Integer bytesRead = new Integer(0);

    private int totalBytes;

    private long prevTime = System.currentTimeMillis();

    private int bytes;

    private long estimate = 0;

    public ProgressReporter(int totalBytes) {
        this.totalBytes = totalBytes;
    }

    public void update(Observable sender, Object message) {

        if (sender instanceof ChecksumCalculator) {
            // There is additional progress on the current file.
            // Assuming an Integer was been passed as a message.
            bytesRead = (Integer) message;
            bytes += bytesRead;

            estimate = (long)((double)totalBytes
                    / ((double)bytesRead / (double)(System.currentTimeMillis() - (double)prevTime)));

            prevTime = System.currentTimeMillis();
        } else if (sender instanceof HashStreamWriter) {
            // A new file was found. Output a LF character to start a new line.
            System.out.print('\n');

            // Assuming a String was been passed
            currentPath = (String) message;
            bytesRead = new Integer(0);
        } else {
            throw new IllegalArgumentException("Unexpected message");
        }

        refreshDisplay();
    }

    private void refreshDisplay() {
        System.out.print(
                "\rProcessing " +
                        currentPath +
                        "... " +
                        bytesRead.intValue() +
                        " byte(s) read  " +
                        " estimated: " +
                        estimate +
                        " milliseconds remaining"
        );
    }
}
