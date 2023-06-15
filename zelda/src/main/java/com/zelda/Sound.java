package com.zelda;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
    
    Clip clip;
    URL url[] = new URL[30];

    public Sound(){
        url[0] = getClass().getResource("/res/sounds/Adventure.wav");
        url[1] = getClass().getResource("/res/sounds/coin.wav");
        url[2] = getClass().getResource("/res/sounds/powerup.wav");
        url[3] = getClass().getResource("/res/sounds/unlock.wav");
        url[4] = getClass().getResource("/res/sounds/fanfare.wav");
        url[5] = getClass().getResource("/res/sounds/blocked.wav");
    }

    public void setFile(int soundIndex){
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(url[soundIndex]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play(){
        clip.start();
    }

    public void stop(){
        clip.stop();
    }

    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}
