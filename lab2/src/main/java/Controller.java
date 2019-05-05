import java.util.Scanner;

class Controller {

    private AudioSaver audioSaver = new AudioSaver(10);
    private Scanner scanner = new Scanner(System.in);

    private void askWaitYes(String text) {
        View.print(text);
        while (true) {
            String answer = scanner.next();
            if (answer.equals("Y"))
                return;
        }
    }

    private void wait(int waitSec) {
        try {
            Thread.sleep(waitSec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String printMenu() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("  ====== MENU ======  \n");
        stringBuilder.append("1    - recover new audio.\n");
        stringBuilder.append("2    - play saved audio.\n");
        stringBuilder.append("3    - delete saved audio.\n");
        stringBuilder.append("4    - set max saved audio.\n");
        stringBuilder.append("else - end of program.\n");

        return stringBuilder.toString();
    }

    private void recover() {
        AudioRecover audioRecover = new AudioRecover();
        Thread audioRecoverThread = new Thread(audioRecover);
        audioRecover.setRecovering(true);
        askWaitYes("Start recover?[Y/N]");
        audioRecoverThread.start();
        wait(2000);
        askWaitYes("End recover?[Y/N]");
        audioRecover.setRecovering(false);
        Audio audio = new Audio(audioRecover.getBytesOfRecoverAudio());
        audioRecover.clear();
        audioRecoverThread.stop();
        wait(500);
        View.print("Enter name of recover audio for save:");
        while (true){
            String name = scanner.next();
            if (audioSaver.addAudio(name, audio))
                break;
        }
    }

    private void play() {
        View.print("Enter name of recover audio for play:");
        while (true) {
            String name = scanner.next();
            Audio audio = audioSaver.getAudio(name);
            if (audio != null) {
                audio.play();
                break;
            } else {
                View.print("Try again.");
            }
        }
    }

    private void deleteAudio() {
        audioSaver.removeAudio(View.printAnswer("Enter audio name for delete."));
    }

    private void setMaxAudio() {
        audioSaver.setSize(View.printAnswerInt("Enter new size of save audio.(Mast be bigger then real size.)"));
    }

    private boolean menu(String text) {
        if (text.equals("1")) {
            recover();
        } else if (text.equals("2")) {
            play();
        } else if (text.equals("3")) {
            deleteAudio();
        } else if (text.equals("4")) {
            setMaxAudio();
        } else {
            return false;
        }
        return true;
    }

    void run() {
        while (menu(View.printAnswer(printMenu()))) {}
    }
}
