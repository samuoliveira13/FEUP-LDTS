package spaceInvaders.view.menus;

import spaceInvaders.Game;
import spaceInvaders.TerminalBuilder;
import spaceInvaders.view.State;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import java.io.IOException;

public class instructionsMenu extends State {

    public instructionsMenu(Game game, TerminalBuilder terminal) {
        super(game, terminal);
    }

    @Override
    public void draw() {
        try {
            this.screen.clear();

            graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
            graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width * 2, height * 2), ' ');

            graphics.setForegroundColor(TextColor.Factory.fromString("#28BB50"));
            graphics.putString(new TerminalPosition(width / 2 - 12, 5), "I N S T R U C T I O N S", SGR.BOLD);

            graphics.setForegroundColor(TextColor.Factory.fromString("#28BB50"));
            graphics.putString(new TerminalPosition(width / 2 - 11, 9), "LEFT ARROW - MOVE LEFT", SGR.BOLD);
            graphics.putString(new TerminalPosition(width / 2 - 12, 12), "RIGHT ARROW - MOVE RIGHT", SGR.BOLD);
            graphics.putString(new TerminalPosition(width / 2 - 6, 15), "ENTER - SHOOT", SGR.BOLD);
            graphics.putString(new TerminalPosition(width / 2 - 8, 19), "Q - QUIT THE GAME", SGR.BOLD);

            graphics.putString(new TerminalPosition(width / 2 - 10, height - 3), "BACKSPACE - GO BACK", SGR.BOLD);

            this.screen.refresh();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void execute() throws IOException {
        getBackToMenu();
    }

}