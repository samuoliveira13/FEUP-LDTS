package spaceInvaders.Menu;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import spaceInvaders.Position;
import spaceInvaders.elements.*;

public class ElementsTest {
    Element enemy;
    Element protection;
    Element spaceShip;
    Element walls;
    Boss bigboss;
    Bullet bullet;
    Laser laser;
    Position position;

    TextGraphics tg;

    TextColor tc;
    TextColor tc1;
    TextColor tc2;
    TextColor tc3;
    TextColor tc4;
    TextColor tc5;
    TextColor tc6;

    @BeforeEach
    public void setUp() {
        enemy = new Enemy(13, 14);
        protection = new Protection(15,20);
        spaceShip = new Spaceship(10, 10);
        walls = new Walls(12, 12);

        bullet = new Bullet(13,13);
        laser = new Laser(15, 16);
        position = new Position(10, 10);
        bigboss = new Boss(17,17);

        tc = TextColor.Factory.fromString("#3CFBE7");
        tc1 = TextColor.Factory.fromString("#FFFF00");
        tc2 = TextColor.Factory.fromString("#FFFFFF");
        tc3 = TextColor.Factory.fromString("#62C761");
        tc4 = TextColor.Factory.fromString("#FF0000");
        tc5 = TextColor.Factory.fromString("#25FFDE");
        tc6 = TextColor.Factory.fromString("#FF0000");

        tg = Mockito.mock(TextGraphics.class);
    }

    @Test
    public void bossDrawTest() {
        bigboss.draw(tg);
        Mockito.verify(tg, Mockito.times(1)).setForegroundColor(tc6);
    }
    @Test
    public void enemyDrawTest() {
        enemy.draw(tg);
        Mockito.verify(tg, Mockito.times(1)).setForegroundColor(tc3);
    }
    @Test
    public void enemyMoveLeftTest() {
        Position setposition = new Position(enemy.getPosition().getX(), enemy.getPosition().getY());
        enemy.moveLeft();
        Assertions.assertEquals(12,setposition.getX() - 1);
        Assertions.assertNotEquals(10,setposition.getX());
        Assertions.assertNotNull(setposition.getX());
    }
    @Test
    public void enemyMoveRightTest() {
        Position setposition = new Position(enemy.getPosition().getX(), enemy.getPosition().getY());
        enemy.moveRight();
        Assertions.assertEquals(14,setposition.getX() + 1);
        Assertions.assertNotEquals(10,setposition.getY());
        Assertions.assertNotNull(setposition.getY());
    }

    @Test
    public void protectionDrawTest() {
        protection.draw(tg);
        Mockito.verify(tg, Mockito.times(1)).setForegroundColor(tc1);
    }

    @Test
    public void spaceshipDrawTest() {
        spaceShip.draw(tg);
        Mockito.verify(tg, Mockito.times(1)).setForegroundColor(tc);
    }
    @Test
    public void spaceshipGetPositionTest() {
        Assertions.assertEquals(position, spaceShip.getPosition());
        Assertions.assertNotNull(spaceShip.getPosition());
    }
    @Test
    public void spaceshipSetPositionSTest() {
        Position setposition = new Position(10, 12);
        spaceShip.setPosition(setposition);
        Assertions.assertEquals(setposition, spaceShip.getPosition());
        Assertions.assertNotNull(spaceShip.getPosition());
        Assertions.assertNotEquals(setposition.getX() + 1,spaceShip.getPosition().getX());
        Assertions.assertNotEquals(setposition.getY() + 1,spaceShip.getPosition().getY());
    }
    @Test
    public void spaceshipMoveLeftTest() {
        Spaceship spaceship = new Spaceship(10,10);
        spaceship.moveLeft();
        Assertions.assertEquals(10, spaceship.getPosition().getX());
        Assertions.assertNotEquals(9, spaceship.getPosition().getX());
    }
    @Test
    public void spaceshipMoveRightTest() {
        Spaceship spaceship = new Spaceship(10,10);
        spaceship.moveRight();
        Assertions.assertEquals(10, spaceship.getPosition().getX());
        Assertions.assertNotEquals(11, spaceship.getPosition().getX());
    }


    @Test
    public void wallsDrawTest() {
        walls.draw(tg);
        Mockito.verify(tg, Mockito.times(1)).setForegroundColor(tc2);
    }


    @Test
    public void bulletDrawTest(){
        bullet.draw(tg);
        Mockito.verify(tg, Mockito.times(1)).setForegroundColor(tc5);
    }
    @Test
    public void bulletMoveDownTest() throws InterruptedException {
        Bullet bullet = new Bullet(10,10);
        bullet.moveDown();
        Assertions.assertEquals(11, bullet.getPosition().getY());
        Assertions.assertNotNull(bullet.getPosition().getY());
    }


    @Test
    public void laserDrawTest() {
        laser.draw(tg);

        Mockito.verify(tg, Mockito.times(1)).setForegroundColor(tc4);
    }
    @Test
    public void laserMoveUpTest() {
        Laser laser = new Laser(10,10);
        laser.moveUp();
        Assertions.assertEquals(9,laser.getPosition().getY());
        Assertions.assertNotNull(laser.getPosition().getY());
    }


}