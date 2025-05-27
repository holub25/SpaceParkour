import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Audio {

    private AudioInputStream audio;
    private Clip clip;
    private boolean loaded;

    public Audio(String file) {
        this.loaded = false;
        loadAudio(file);
    }
    public void loadAudio(String file){
        try {
            File audiFile = new File(file);
            if(!audiFile.exists()){
                System.out.println("File do not exist");;
                return;
            }
            this.audio = AudioSystem.getAudioInputStream(audiFile);
            this.clip = AudioSystem.getClip();
            clip.open(audio);
            loaded = true;

        } catch (UnsupportedAudioFileException e) {
            System.out.println("ERROR1");
        } catch (IOException e) {
            System.out.println("ERROR2");
        }catch (LineUnavailableException e) {
            System.out.println("ERROR3");
        }catch (Exception e){
            System.out.println("ERROR4");
        }
    }
    public void loop(){
        if(loaded && clip != null){
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }
    public void setVolume(int volume){
        if(loaded && clip != null){
            try {
                FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                control.setValue(volume);
            }catch (IllegalArgumentException e){
                System.out.println("ERROR5");
            }
        }

    }

}
