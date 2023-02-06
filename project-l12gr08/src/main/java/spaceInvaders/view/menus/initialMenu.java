package spaceInvaders.view.menus;

import spaceInvaders.view.State;
import spaceInvaders.Game;
import spaceInvaders.TerminalBuilder;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import java.io.IOException;

public class initialMenu extends State{
    public initialMenu(Game game, TerminalBuilder terminal) {
        super(game, terminal);
    }

    @Override
    public void draw() {
        try {
            this.screen.clear();

            graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
            graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width * 2, height * 2), ' ');

            graphics.setForegroundColor(TextColor.Factory.fromString("#28BB50"));
            graphics.putString(new TerminalPosition(width / 2 - 13 , 8), "S P A C E  I N V A D E R S", SGR.BOLD);

            graphics.setForegroundColor(TextColor.Factory.fromString("#28BB50"));
            graphics.putString(new TerminalPosition(width / 2 - 16, 15), "PRESS I TO READ THE INSTRUCTIONS", SGR.BOLD);
            graphics.putString(new TerminalPosition(width / 2 - 13, 18), "PRESS S TO START THE GAME", SGR.BOLD);
            graphics.putString(new TerminalPosition(width / 2 - 12, 21), "PRESS Q TO QUIT THE GAME", SGR.BOLD);
            this.screen.refresh();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void execute() throws IOException {
        getToNextState();
    }


}