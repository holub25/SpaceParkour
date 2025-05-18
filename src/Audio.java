import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Audio {

    private AudioInputStream audio;
    private Clip clip;

    public Audio(String file) {
        loadAudio(file);
    }
    public void loadAudio(String file){
        try {
            this.audio = AudioSystem.getAudioInputStream(new File(file));
            this.clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();

        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }
    public void setVolume(int volume){
        FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        control.setValue(volume);
    }

    public Clip getClip() {
        return clip;
    }
}
