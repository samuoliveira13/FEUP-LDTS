import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {
    private Screen screen;
    private int x = 10;
    private int y = 10;

    public Game() {
        try {
            Terminal terminal = new DefaultTerminalFactory().createTerminal();

            this.screen = new TerminalScreen(terminal);

            screen.setCursorPosition(null); // we don't need a cursor
            screen.startScreen(); // screens must be started
            screen.doResizeIfNecessary();
            TerminalSize terminalSize = new TerminalSize(40, 20);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void draw() throws IOException {
        this.screen.clear();
        this.screen.setCharacter(x, y, TextCharacter.fromCharacter('X')[0]);
        this.screen.refresh();
    }

    public void run() throws IOException {
        boolean playing = true;
        while (playing) {
            draw();
            KeyStroke key = screen.readInput();
            processKey(key);
            switch (key.getKeyType()) {
                case ArrowUp:
                    y -= 1;
                    break;
                case ArrowDown:
                    y += 1;
                    break;
                case ArrowRight:
                    x += 1;
                    break;
                case ArrowLeft:
                    x -= 1;
                    break;
                case EOF:
                    playing = false;
                    break;
                case Character:
                    if (key.getCharacter() == 'q') {
                        this.screen.close();
                    }
                    break;
            }
        }
    }

    private void processKey(KeyStroke key) {
        System.out.println(key);
    }
}
