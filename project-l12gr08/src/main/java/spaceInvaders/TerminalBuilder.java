package spaceInvaders;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;


public class TerminalBuilder {
    private Screen screen;
    private int width;
    private int height;
    private TextGraphics graphics;

    public TerminalBuilder(int width, int height) {
        try {
            this.width = width;
            this.height = height;

            DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();

            defaultTerminalFactory.setForceAWTOverSwing(true);

            Terminal terminal = defaultTerminalFactory.setInitialTerminalSize(new TerminalSize(this.width, this.height)).createTerminal();

            ((AWTTerminalFrame)terminal).addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    e.getWindow().dispose();
                    System.exit(0);
                }
            });

            this.screen = new TerminalScreen(terminal);
            this.graphics = this.screen.newTextGraphics();
            this.screen.setCursorPosition(null);   // we don't need a cursor
            this.screen.startScreen();             // screens must be started
            this.screen.doResizeIfNecessary();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public TextGraphics getGraphics() {
        return graphics;
    }

    public void setGraphics(TextGraphics graphics){
        this.graphics = graphics;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}