package spaceInvaders;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import spaceInvaders.view.menus.initialMenu;
import spaceInvaders.view.State;

import java.io.IOException;

public class Game {
    private static Game firstInstance = null;

    private State state;
    private Screen screen;
    private Arena arena;
    private TerminalBuilder window;
    private MusicPlayer musicPlayer;

    public Game(){
        this.arena = new Arena(50, 30);
        this.window = new TerminalBuilder(50, 30);
        this.state = new initialMenu(this, this.window);
        this.screen = window.getScreen();
        this.musicPlayer = new MusicPlayer();
        startMusic();
    }

    public static Game getInstance(){
        if(firstInstance == null){
            firstInstance = new Game();
        }
        return firstInstance;
    }

    private void draw() throws IOException {
        screen.clear();
        state.draw();
        screen.refresh();
    }

    public void run() throws IOException, InterruptedException {

        int FPS = 30;
        int frameTime = 1000/FPS;

        while(true){
            long startTime = System.currentTimeMillis();

            draw();

            state.execute();


            long sleepTime = calculateSleepTime(frameTime, getElapsedTime(startTime));
            if(sleepTime > 0) Thread.sleep(sleepTime);

        }
    }
    public void startMusic(){
        musicPlayer.startMusic();
    }

    public long getElapsedTime(long startTime) {
        return System.currentTimeMillis() - startTime;
    }
    public long calculateSleepTime(int frameTime, long elapsedTime){
        return frameTime-elapsedTime;
    }
    public TerminalBuilder getTerminalBuilder(){
        return this.window;
    }

    public Arena getArena(){
        return this.arena;
    }

    public void setState(State state) {
        this.state = state;
    }
    public State getState() {
        return this.state;
    }

    public void setScreen(Screen screen){
        this.screen = screen;
        this.window.setScreen(screen);
    }

    public void setGraphics(TextGraphics graphics){
        this.window.setGraphics(graphics);
    }

}