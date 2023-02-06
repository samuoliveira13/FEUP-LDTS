
package spaceInvaders.Menu.Menu;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import spaceInvaders.Game;
import spaceInvaders.TerminalBuilder;
import spaceInvaders.view.State;
import spaceInvaders.view.menus.instructionsMenu;
import spaceInvaders.view.menus.initialMenu;
import java.io.IOException;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class InstructionMenuTest {

    TextGraphics board = Mockito.mock(TextGraphics.class);
    Game game;
    TerminalScreen screen;
    TextGraphics graphics;
    instructionsMenu state;

    @BeforeEach
    public void setup() {

        screen = Mockito.mock(TerminalScreen.class);
        graphics = Mockito.mock(TextGraphics.class);

        game = Game.getInstance();
        game.setScreen(screen);
        game.setGraphics(graphics);

        state = new instructionsMenu(game, game.getTerminalBuilder());
        game.setState(state);
    }

    @Test
    public void instructionsGoingBackTest() throws IOException {

        KeyStroke keyStrokeMock1 = Mockito.mock(KeyStroke.class);
        Mockito.when(keyStrokeMock1.getKeyType()).thenReturn(KeyType.Backspace);
        Mockito.when(screen.readInput()).thenReturn(keyStrokeMock1);

        assertTrue(game.getState() instanceof instructionsMenu);

        state.getBackToMenu();

        assertTrue(game.getState() instanceof initialMenu);
    }

    @Test
    public void drawTest() throws IOException {
        state.draw();
        game.getArena();
        Mockito.verify(screen, Mockito.times(1)).clear();
        Mockito.verify(screen, Mockito.times(1)).refresh();


        Mockito.verify(graphics, Mockito.times(1)).setBackgroundColor(new TextColor.RGB(0,0,0));
        Mockito.verify(graphics, Mockito.times(2)).setForegroundColor(new TextColor.RGB(40,187,80));

        Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(state.getWidth() / 2 - 12, 5), "I N S T R U C T I O N S", SGR.BOLD);
        Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(state.getWidth() / 2 - 11, 9), "LEFT ARROW - MOVE LEFT", SGR.BOLD);
        Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(state.getWidth() / 2 - 12, 12), "RIGHT ARROW - MOVE RIGHT", SGR.BOLD);
        Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(state.getWidth() / 2 - 6, 15), "ENTER - SHOOT", SGR.BOLD);
        Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(state.getWidth() / 2 - 8, 19), "Q - QUIT THE GAME", SGR.BOLD);
        Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(state.getWidth() / 2 - 10, state.getHeight() - 3), "BACKSPACE - GO BACK", SGR.BOLD);
    }
}


