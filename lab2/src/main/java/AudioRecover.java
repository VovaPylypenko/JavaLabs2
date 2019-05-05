import java.io.ByteArrayOutputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;


public class AudioRecover implements Runnable {

    private final AudioFormat format = new AudioFormat(8000.0f, 16, 1, true, true);
    private final DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
    private TargetDataLine microphone;

    private ByteArrayOutputStream out = new ByteArrayOutputStream();
    private boolean recovering;

    private void printAndWait(String text, int waitSec) {
        System.out.println(text);
        try {
            Thread.sleep(waitSec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void recover() {

        int bytesRead = 0;
        printAndWait("3..", 300);
        printAndWait("2..", 300);
        printAndWait("1..", 300);

        try {
            while (recovering) {
                byte[] data = new byte[microphone.getBufferSize() / 5];
                int numBytesRead = microphone.read(data, 0, 1024);
                bytesRead = bytesRead + numBytesRead;
                out.write(data, 0, numBytesRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Stopped recover!");
    }

    byte[] getBytesOfRecoverAudio() {
        return out.toByteArray();
    }

    AudioRecover setRecovering(boolean recovering) {
        this.recovering = recovering;
        return this;
    }

    public void run() {
        recovering = true;

        try {
            microphone = (TargetDataLine) AudioSystem.getLine(info);
            microphone.open(format);
            microphone.start();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        recover();
    }

    void clear() {
        out.reset();
        microphone.close();
    }
}
