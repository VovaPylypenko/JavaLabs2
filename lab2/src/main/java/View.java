import java.util.Scanner;

class View {

    static void print(String text) {
        System.out.println(text);
    }

    static String printAnswer(String text) {
        print(text);
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }

    static int printAnswerInt(String text) {
        print(text);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
