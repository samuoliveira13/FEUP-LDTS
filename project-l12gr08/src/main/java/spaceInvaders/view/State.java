package spaceInvaders.view;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import spaceInvaders.Game;
import spaceInvaders.TerminalBuilder;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import spaceInvaders.view.menus.*;

import java.io.IOException;

public abstract class State {
    protected Game game;
    protected TerminalBuilder terminalwindow;
    protected Screen screen;
    protected int width;
    protected int height;
    protected TextGraphics graphics;


    public State(Game game, TerminalBuilder terminal){
        this.game = game;
        this.terminalwindow = terminal;
        this.screen = terminal.getScreen();
        this.width = terminal.getWidth();
        this.height = terminal.getHeight();
        this.graphics = terminal.getGraphics();
    }

    public abstract void draw() throws IOException;


    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    public abstract void execute() throws IOException, InterruptedException;

    public void getToNextState() throws IOException, NullPointerException {
        KeyStroke key = screen.readInput();

        if(key.getCharacter() == 'i') game.setState(new instructionsMenu(game, terminalwindow));
        if(key.getCharacter() == 's') {
            game.getArena().resetLives();
            game.getArena().resetProtection();
            game.setState(new mainMenu(game, terminalwindow));
        }
        if (key.getCharacter() == 'q') {
            screen.close();
            System.exit(0);
        }
    }

    public void goToNextState() throws IOException, NullPointerException {
        if(game.getArena().getLives() == 0) game.setState(new gameOverMenu(game, terminalwindow));
        if(game.getArena().getLivesBoss() == 0) game.setState(new gameWonMenu(game, terminalwindow));
    }

    public void getBackToMenu() throws IOException, NullPointerException {

        KeyStroke key = screen.readInput();
        if (key.getKeyType() == KeyType.Backspace) game.setState(new initialMenu(game, terminalwindow));
        if (key.getCharacter() == 'q') {
            screen.close();
            System.exit(0);
        }
    }

}