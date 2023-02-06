package spaceInvaders.Menu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import spaceInvaders.Position;


public class PositionTest {
    Position position = new Position(10, 15);

    @Test
    public void getXTest() {
        Assertions.assertEquals(10, position.getX());
        Assertions.assertNotEquals(8, position.getX());
        Assertions.assertNotNull(position.getX());
    }

    @Test
    public void getYTest() {
        Assertions.assertEquals(15, position.getY());
        Assertions.assertNotEquals(10, position.getY());
        Assertions.assertNotNull(position.getY());
    }

    @Test
    public void setXTest() {
        position.setX(12);
        Assertions.assertEquals(12, position.getX());
        Assertions.assertNotEquals(10, position.getX());
        Assertions.assertNotNull(position.getX());
    }

    @Test
    public void setYTest() {
        position.setY(14);
        Assertions.assertEquals(14, position.getY());
        Assertions.assertNotEquals(15, position.getY());
        Assertions.assertNotNull(position.getY());
    }

    @Test
    public void equalsTest() {
        Object o = null;
        Assertions.assertNotEquals(position, o);
        Assertions.assertNotNull(position);
        Assertions.assertNull(o);
    }
    @Test
    public void differentClassTest() {
        String o = "null";
        Assertions.assertNotEquals(position, o);
        Assertions.assertNotNull(o);
    }
}