import java.util.HashMap;

class AudioSaver {

    private int maxSize;
    private HashMap<String, Audio> audioSaver;

    AudioSaver(int maxSize) {
        this.maxSize = maxSize;
        audioSaver = new HashMap<String, Audio>();
    }

    AudioSaver setSize(int size) {
        if (audioSaver.size() < size)
            this.maxSize = size;
        else
            View.print("New max size is less than real size. Delete audio and try again.");
        return this;
    }

    boolean addAudio(String name, Audio audio) {
        if (audioSaver.containsKey(name))
            if (!View.printAnswer("Audio with this name already present. Reset?[Y/N]").equals("Y"))
                return false;
        if (audioSaver.size() + 1 > maxSize) {
            View.print("Used all audio slot. (" + maxSize + ")!");
            return true;
        }
        audioSaver.put(name, audio);
        return true;
    }

    void removeAudio(String name) {
        if (!audioSaver.containsKey(name))
            View.print("Audio with this name is absent.");
        else
            audioSaver.remove(name);
    }

    Audio getAudio(String name) {
        if (audioSaver.containsKey(name))
            return audioSaver.get(name);
        return null;
    }
}
