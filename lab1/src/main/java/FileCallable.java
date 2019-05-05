import javafx.util.Pair;

import java.io.*;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class FileCallable implements Callable<Pair<String, Integer>> {

    private File file = null;

    public File getFile() {
        return file;
    }

    public FileCallable setFile(File file) {
        this.file = file;
        return this;
    }

    private int CountForInFile() throws IOException {
        if (file == null) {
            System.out.println("Before you mast set directory with file!");
            return 0;
        }
        if (file.isDirectory()) {
            System.out.println("It mast be not directory!");
            return 0;
        }
        int count = 0;
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            count += (line + "\0").split("for\\(").length - 1;
        }
        reader.close();
        return count;
    }

    @Override
    public Pair<String, Integer> call() throws Exception {
        //Scanner scanner = new Scanner(file);

        return new Pair<String, Integer>(file.getPath(), CountForInFile());
    }
}
