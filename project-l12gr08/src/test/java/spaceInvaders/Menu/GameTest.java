package spaceInvaders.Menu;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import spaceInvaders.Game;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

import spaceInvaders.Position;
import spaceInvaders.TerminalBuilder;
import spaceInvaders.elements.Boss;
import spaceInvaders.elements.Enemy;
import spaceInvaders.elements.*;
import spaceInvaders.view.State;
import spaceInvaders.Arena;


import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

public class GameTest {
    private Game game;
    private Boss boss;
    private Arena arena;
    private Spaceship spaceship;
    private State state;
    private TerminalBuilder terminalBuilder;
    TerminalScreen screen;
    TextGraphics graphics;

    @BeforeEach
    public void setup() {
        screen = mock(TerminalScreen.class);
        graphics = mock(TextGraphics.class);

        game = Game.getInstance();
        terminalBuilder = game.getTerminalBuilder();

        state = mock(State.class);
        arena = new Arena(50,30);
        boss = new Boss(10,10);
    }
    
    @Test
    public void getTerminalCreatorTest() {
        assertEquals(terminalBuilder, game.getTerminalBuilder());
        assertNotNull(game.getTerminalBuilder());
    }
    @Test
    public void getInstanceNullTest() {
        Assertions.assertNotNull(Game.getInstance());
    }
    @Test
    public void setGetGameStateTest() {
        game.setState(state);
        assertEquals(state, game.getState());
        assertNotNull(game.getState());
    }
    @Test
    public void setGetGameStateNotEqualsTest() {
        assertNotEquals(state, game.getState());
    }
     @Test
    public void setGetScreenTest() {
        game.setScreen(screen);
        assertEquals(screen, game.getTerminalBuilder().getScreen());
         assertNotNull(game.getTerminalBuilder().getScreen());
    }
    @Test
    public void setGetScreenNotEqualsTest() {
        assertNotEquals(screen,game.getTerminalBuilder().getScreen());
    }
    @Test
    public void firstInstaceTest() {
        assertEquals(game, Game.getInstance());
        assertNotNull(Game.getInstance());
    }
    @Test
    public void gameTest() {
        try {
            TerminalScreen screen = (TerminalScreen) Mockito.mock(TerminalScreen.class);
            TextGraphics graphics = (TextGraphics) Mockito.mock(TextGraphics.class);
            Mockito.when(screen.newTextGraphics()).thenReturn(graphics);
            Mockito.when(graphics.putString(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt(), ArgumentMatchers.anyString())).thenReturn(graphics);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void getScoreTest() {
        Assertions.assertEquals(0, game.getArena().getScore());
        Assertions.assertNotEquals(150, game.getArena().getScore());
        Assertions.assertNotNull(game.getArena().getScore());
    }
    @Test
    public void SoundsTest() {
        Laser laser = new Laser(10, 10);
        Enemy enemy = new Enemy(10,10);
        game.getArena().laserHit();
        Assertions.assertNotNull(laser);
    }
    @Test
    public void testRemoveEnemy() {
        List<Enemy> enemies = new ArrayList<>();
        List<Laser> lasers = new ArrayList<>();
        lasers.add(new Laser(11,11));
        lasers.add(new Laser(11,10));
        enemies.add(new Enemy(11, 11));
        arena.setEnemies(enemies);
        arena.setLasers(lasers);
        for (int i = 0; i < lasers.size(); i++){
            arena.laserHit();
        }
        Assertions.assertEquals(0, enemies.size());
    }

    @Test
    public void removeLiveSpaceShip(){
        Spaceship spaceship = new Spaceship(12,12);
        List<Bullet> bullets = new ArrayList<>();
        bullets.add(new Bullet(12,12));
        bullets.add(new Bullet(12,11));
        arena.setBullets(bullets);
        arena.setSpaceship(spaceship);
        for (int i = 0; i<bullets.size(); i++){
            arena.bulletHit();
        }
        Assertions.assertEquals(2, arena.getLives());
    }
    @Test
    public void removeLiveBoss(){
        List<Enemy> enemies = new ArrayList<>();
        List<Laser>lasers = new ArrayList<>();
        List<Boss> bigBoss = new ArrayList<>();
        lasers.add(new Laser(15,15));
        lasers.add(new Laser (15,14));
        bigBoss.add(new Boss(15,15));
        arena.setLasers(lasers);
        arena.setBigBoss(bigBoss);
        arena.setEnemies(enemies);
        for (int i = 0; i< lasers.size();i++){
            arena.laserHit();
        }
        Assertions.assertEquals(9,arena.getLivesBoss());
        Assertions.assertEquals(1,lasers.size());
    }
    
    @Test
    public void notHittingWallTest() {
        Assertions.assertEquals(game.getArena().notHittingWall(new Position(0,0)), false);
        Assertions.assertEquals(game.getArena().notHittingWall(new Position(0,5)), false);
        Assertions.assertEquals(game.getArena().notHittingWall(new Position(1,7)), true);
        Assertions.assertEquals(game.getArena().notHittingWall(new Position(10,9)), true);
    }

    @Test
    public void canSpaceshipMove(){
        Position pos = new Position(0, 0);
        game.getArena().moveSpaceship(pos);

        Assertions.assertTrue(game.getArena().getSpaceship().getPosition() != pos);

        pos = new Position(5, 5);
        game.getArena().moveSpaceship(pos);

        Assertions.assertTrue(game.getArena().getSpaceship().getPosition() == pos);
    }


    @Test
    public void drawLivesBoss() {
        arena.livesBossDraw(graphics);
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(2, 3), "BossEnemy Lives:");
        Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(19, 3), Integer.toString(arena.getLivesBoss()));
    }

    @Test
    public void drawLives() {
        arena.livesDraw(graphics);
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(2, 2), "Lives:");
        Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(9, 2), Integer.toString(arena.getLives()));
    }

    @Test
    public void drawScore() {
        arena.scoreDraw(graphics);
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(2, 1), "Score:");
        Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(9, 1), Integer.toString(arena.getScore()));
    }

    @Test
    public void drawTest(){
        arena.draw(graphics);
        Mockito.verify(graphics, Mockito.times(1)).setBackgroundColor(TextColor.Factory.fromString("#000000"));
        Mockito.verify(graphics, Mockito.times(1)).fillRectangle(new TerminalPosition(0, 0), new TerminalSize(arena.getWidth(), arena.getHeight()), ' ');
        List<Laser>lasers = new ArrayList<>();
        lasers.add(new Laser(10,9));
        arena.setLasers(lasers);
    }

    @Test
    public void arenaTest() {
        Assertions.assertNotEquals(arena, game.getArena());
        Assertions.assertNotNull(game.getArena());
    }

    @Test
    public void arenaGetLivesTest() {
        Assertions.assertNotNull(game.getArena().getLives());
        Assertions.assertEquals(3, game.getArena().getLives());
        Assertions.assertNotEquals(0, game.getArena().getLives());
    }

    @Test
    public void arenaResetProtectionsTest() {
        List<Protection> protections = game.getArena().createProtections();
        game.getArena().resetProtection();
        Assertions.assertNotNull(protections);
    }
    @Test
    public void arenaGetLivesBossTest() {
        Assertions.assertEquals(10, game.getArena().getLivesBoss());
        Assertions.assertNotEquals(5, game.getArena().getLivesBoss());
        Assertions.assertNotNull(game.getArena().getLivesBoss());
    }

    @Test
    public void arenaResetLivesTest() {
        int lives = game.getArena().getLives();
        game.getArena().resetLives();
        Assertions.assertEquals(3, lives);
    }
    @Test
    public void arenaSetLivesTest() {
        game.getArena().setLives(3);
        Assertions.assertEquals(3,game.getArena().getLives());
        Assertions.assertNotNull(game.getArena().getLives());
    }
    @Test
    public void elaspedTimeNotEqualsTest() {
        Assertions.assertNotEquals(0, game.getElapsedTime(10));
        Assertions.assertNotNull( game.getElapsedTime(1));
    }

    @Test
    public void calculateSleepTimeTest() {
        Assertions.assertEquals(100, game.calculateSleepTime(110, 10));
        Assertions.assertNotEquals(0, game.calculateSleepTime(110, 10));
        Assertions.assertNotNull(game.calculateSleepTime(110, 10));
    }

    @Test
    public void moveBossVerticallyTest() {
        Boss bigBoss = new Boss(15,15);
        game.getArena().moveBossVertically();
        Assertions.assertEquals(16, bigBoss.getPosition().getY() + 1);
        Assertions.assertNotEquals(15, bigBoss.getPosition().getY() + 1);
        Assertions.assertNotNull(bigBoss.getPosition().getY());
    }
}
