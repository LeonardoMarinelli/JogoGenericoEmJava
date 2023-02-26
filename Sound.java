package model;

import java.io.File;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

public class Sound {
    Clip clip;
    File musicas[] = new File[30];
    
    public Sound() {
       
        musicas[0] = new File(getClass().getResource("MusicBackground.wav").getPath());
        musicas[1] = new File(getClass().getResource("ponto.wav").getPath());
        musicas[2] = new File(getClass().getResource("gameOver.wav").getPath());
        musicas[3] = new File(getClass().getResource("powerUp.wav").getPath());
        musicas[4] = new File(getClass().getResource("Undertale.wav").getPath());
    }
    
    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(musicas[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            
        }
    }
    
    public void play() {
        clip.start();
    }
    
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    
    public void stop() {
        clip.stop();
    }
}