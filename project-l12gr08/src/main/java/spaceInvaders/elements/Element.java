package spaceInvaders.elements;
import spaceInvaders.Position;
import com.googlecode.lanterna.graphics.TextGraphics;


public abstract class Element {
    public Position position;

    public Element(int x, int y){
        this.position = new Position(x, y);
    }

    public Position getPosition(){
        return position;
    }

    public Position setPosition(Position position){
        this.position = position;
        return position;
    }

    public abstract void draw(TextGraphics graphics);

    public Position moveLeft() {
        return new Position(position.getX() - 1, position.getY());
    }

    public Position moveRight() {
        return new Position(position.getX() + 1, position.getY());
    }

    public void moveUp(){
        getPosition().setY(getPosition().getY() - 1);
    }

    public void moveDown(){
        getPosition().setY(getPosition().getY() + 1);
    }
}


