package Audio;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 *  * CLASS `Audio`
 *  * Represents a tool for loading, playing, and adjusting volume of an audio file.
 */
public class Audio {

    private AudioInputStream audio;
    private Clip clip;
    private boolean loaded;

    public Audio(String file) {
        this.loaded = false;
        loadAudio(file);
    }

    /**
     * Loads the audio file from the specified path and prepares it for playback.
     * @param file Path to the audio file
     */
    public void loadAudio(String file){
        try {
            InputStream is = getClass().getResourceAsStream(file);
            //File audiFile = new File(file);
            if(is == null){
                System.out.println("File do not exist");;
                return;
            }
            this.audio = AudioSystem.getAudioInputStream(new BufferedInputStream(is));
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

    /**
     * Plays the loaded audio in a continuous loop.
     */
    public void loop(){
        if(loaded && clip != null){
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    /**
     * Sets the volume of the playing sound.
     * @param volume Volume in decibels (dB)
     */
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
