package spaceInvaders.Menu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import spaceInvaders.MusicPlayer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

public class MusicPlayerTest {
    Clip backgroundMusic;
    Clip laserShootSound;
    Clip laserHitSound;
    Clip playerHitSound;
    Clip playerDeathSound;
    Clip ufoSound;
    MusicPlayer musicPlayer;

    @Test
    public void laserShootTest() {
        musicPlayer = new MusicPlayer();
        musicPlayer.laserShoot();
        Assertions.assertNotNull(musicPlayer);
    }
    @Test
    public void playerDeathTest() {
        musicPlayer = new MusicPlayer();
        musicPlayer.playerDeath();
        Assertions.assertNotNull(musicPlayer);
    }
    @Test
    public void ufoTest() {
        musicPlayer = new MusicPlayer();
        musicPlayer.ufo();
        Assertions.assertNotNull(musicPlayer);
    }
}
