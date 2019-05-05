import java.io.FileReader;
import java.util.Scanner;

public class Controller {

    Scanner scanner = new Scanner(System.in);
    FileFinger fileFinger = new FileFinger();

    private void printMenu() {
        System.out.println("==========================");
        System.out.println("1 - enter directory.");
        System.out.println("2 - start find for in C file.");
        System.out.println("3 - print menu.");
        System.out.println("empty - end program.");
        System.out.println("==========================");
    }

    private String scanner(String txt) {
        System.out.println(txt + " input:");
        return scanner.next();
    }
    // "C:\\Users\\Admin\\Downloads\\"

    public void start() {
        printMenu();
        String inData = scanner("DO");
        String directory = "";
        while (!inData.isEmpty()) {
            switch (inData) {
                case "1": {
                    directory = scanner("Directory");
                    break;
                }
                case "2": {
                    fileFinger.doing(directory);
                    break;
                }
                case "3": {
                    printMenu();
                    fileFinger.stopping();
                    break;
                }
                case "0": {
                    return;
                }
            }
            inData = scanner("DO");
        }
        fileFinger.stopping();
    }
}
