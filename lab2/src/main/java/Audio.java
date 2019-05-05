
class Audio {

    private byte[] audio;

    Audio(byte[] audio) {
        this.audio = audio;
    }

    void play() {
        AudioPlayer.play(audio);
    }
}
