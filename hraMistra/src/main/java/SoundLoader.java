import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;

public class SoundLoader {
    Clip clip;
    SoundLoader(String file){
        File lol = new File(file);

        try{
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(lol));
        } catch (Exception e){
            e.printStackTrace();
        }

    }
    public void loop(){
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);

    }
    public void start() {
        if (clip.isRunning()) {
            clip.stop();
        }
        clip.setFramePosition(0);
        clip.start();
    }
}
