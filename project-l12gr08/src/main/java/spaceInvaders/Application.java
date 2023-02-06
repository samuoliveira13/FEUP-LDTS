package spaceInvaders;
import java.io.IOException;

public class Application {
        public static void main(String[] args) throws IOException, InterruptedException, NullPointerException {
            Game.getInstance().run();
        }
}

