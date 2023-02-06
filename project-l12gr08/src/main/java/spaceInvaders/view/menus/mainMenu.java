package spaceInvaders.view.menus;
import spaceInvaders.Game;
import spaceInvaders.TerminalBuilder;
import spaceInvaders.view.State;
import com.googlecode.lanterna.input.KeyStroke;
import java.io.IOException;
import spaceInvaders.Arena;


public class mainMenu extends State{
    public mainMenu(Game game, TerminalBuilder terminal) {
        super(game, terminal);
    }

    @Override
    public void draw() {
        game.getArena().draw(graphics);
    }

    @Override
    public void execute() throws IOException {
        KeyStroke key = screen.pollInput();
        game.getArena().processKey(key, terminalwindow.getScreen());
        goToNextState();
    }
}

