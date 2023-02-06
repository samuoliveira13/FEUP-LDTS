package spaceInvaders.Menu.Menu;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import spaceInvaders.Game;
import spaceInvaders.view.menus.initialMenu;
import spaceInvaders.view.menus.gameOverMenu;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameOverMenuTest {
    TextGraphics board = Mockito.mock(TextGraphics.class);
    Game game;
    TerminalScreen screen;
    TextGraphics graphics;
    gameOverMenu state;

    @BeforeEach
    public void setup() {

        screen = Mockito.mock(TerminalScreen.class);
        graphics = Mockito.mock(TextGraphics.class);

        game = Game.getInstance();
        game.setScreen(screen);
        game.setGraphics(graphics);

        state = new gameOverMenu(game, game.getTerminalBuilder());
        game.setState(state);
    }

    @Test
    public void getBackToMenuTest() throws IOException, NullPointerException {
        KeyStroke keyStrokeMock1 = Mockito.mock(KeyStroke.class);
        Mockito.when(keyStrokeMock1.getKeyType()).thenReturn(KeyType.Backspace);
        Mockito.when(screen.readInput()).thenReturn(keyStrokeMock1);
        assertTrue(game.getState() instanceof gameOverMenu);

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
        Mockito.verify(graphics, Mockito.times(3)).setForegroundColor(new TextColor.RGB(40,187,80));

        Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(state.getWidth() / 2 - 5, 8), "GAME OVER", SGR.BOLD);
        Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(state.getWidth() / 2 - 11, 14), "BETTER LUCK NEXT TIME!", SGR.BOLD);
        Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(state.getWidth() / 2 - 9, 20), "Q - QUIT THE GAME", SGR.BOLD);
    }
}
