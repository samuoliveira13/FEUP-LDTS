package spaceInvaders;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

public class MusicPlayer {
    private Clip backgroundMusic;
    private Clip laserShootSound;
    private Clip laserHitSound;
    private Clip playerHitSound;
    private Clip playerDeathSound;
    private Clip ufoSound;


    public MusicPlayer() {
        this.laserShootSound = laserShootSound();
        this.laserHitSound = laserHitSound();
        this.playerHitSound = playerHitSound();
        this.playerDeathSound = playerDeathSound();
        this.backgroundMusic = loadMusic();
        this.ufoSound = ufoSound();
    }

    public Clip loadMusic() throws NullPointerException{
        try {
            AudioInputStream in = AudioSystem.getAudioInputStream(new File("src/main/java/Music/background.wav"));
            backgroundMusic = AudioSystem.getClip();
            backgroundMusic.open(in);
            FloatControl gainControl = (FloatControl)backgroundMusic.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-25.0f);
            return backgroundMusic;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Clip laserShootSound() throws NullPointerException{
        try {
            AudioInputStream in2;
            in2 = AudioSystem.getAudioInputStream(new File("src/main/java/Music/shoot.wav"));
            laserShootSound = AudioSystem.getClip();
            laserShootSound.open(in2);
            FloatControl gainControl = (FloatControl)laserShootSound.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-25.0f);
            return laserShootSound;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Clip laserHitSound() throws NullPointerException{
        try {
            AudioInputStream in3;
            in3 = AudioSystem.getAudioInputStream(new File("src/main/java/Music/invaderkilled.wav"));
            laserHitSound = AudioSystem.getClip();
            laserHitSound.open(in3);
            FloatControl gainControl = (FloatControl)laserHitSound.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-25.0f);
            return laserHitSound;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Clip playerHitSound() throws NullPointerException{
        try {
            AudioInputStream in3;
            in3 = AudioSystem.getAudioInputStream(new File("src/main/java/Music/hit.wav"));
            playerHitSound = AudioSystem.getClip();
            playerHitSound.open(in3);
            FloatControl gainControl = (FloatControl)playerHitSound.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-15.0f);
            return playerHitSound;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Clip playerDeathSound() throws NullPointerException{
        try {
            AudioInputStream in3;
            in3 = AudioSystem.getAudioInputStream(new File("src/main/java/Music/explosion.wav"));
            playerDeathSound = AudioSystem.getClip();
            playerDeathSound.open(in3);
            FloatControl gainControl = (FloatControl)playerDeathSound.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-25.0f);
            return playerDeathSound;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Clip ufoSound() throws NullPointerException{
        try {
            AudioInputStream in4;
            in4 = AudioSystem.getAudioInputStream(new File("src/main/java/Music/ufo_lowpitch.wav"));
            ufoSound = AudioSystem.getClip();
            ufoSound.open(in4);
            FloatControl gainControl = (FloatControl)ufoSound.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-25.0f);
            return ufoSound;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void startMusic() {
        backgroundMusic.setMicrosecondPosition(0);
        backgroundMusic.start();
        backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void laserShoot() {
        laserShootSound.setMicrosecondPosition(0);
        laserShootSound.start();
        laserShootSound.loop(0);
    }

    public void laserHits() {
        laserHitSound.setMicrosecondPosition(0);
        laserHitSound.start();
        laserHitSound.loop(0);
    }

    public void playerHit() {
        playerHitSound.setMicrosecondPosition(0);
        playerHitSound.start();
        playerHitSound.loop(0);
    }

    public void playerDeath() {
        playerDeathSound.setMicrosecondPosition(0);
        playerDeathSound.start();
        playerDeathSound.loop(0);
    }
    public void ufo() {
        ufoSound.setMicrosecondPosition(0);
        ufoSound.start();
        ufoSound.loop(0);
    }
}