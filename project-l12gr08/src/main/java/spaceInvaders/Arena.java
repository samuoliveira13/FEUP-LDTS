
package spaceInvaders;
import spaceInvaders.elements.*;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Random;


public class Arena {
    private int height;
    private int width;
    private int score;
    private int lives = 3;
    private int moveDir = 2;
    private int livesBoss = 10;
    private int FPS;
    private Spaceship spaceship;
    private List<Bullet> bullets;
    private List<Laser> lasers;
    private List<Walls> edgeswalls;
    private List<Protection> protections;
    private List<Enemy> enemies;
    private List<Boss> bigboss;
    Thread enemyThread;

    private boolean isRunning = true;
    private MusicPlayer musicPlayer;

    public Arena(int width, int height) {
        this.height = height;
        this.width = width;
        this.spaceship = new Spaceship(24, 24);
        this.bullets = createBullets();
        this.lasers = createLasers();
        this.edgeswalls = createEdgesWalls();
        this.protections = createProtections();
        this.enemies = createEnemies();
        this.bigboss = createBoss();
        startEnemiesThread();
        this.musicPlayer = new MusicPlayer();
    }

    public void laserShotPlayer() {
        musicPlayer.laserShoot();
    }

    public void laserHitPlayer() {
        musicPlayer.laserHits();
    }
    public void playerHit() {
        musicPlayer.playerHit();
    }

    public void playerExplosion() {
        musicPlayer.playerDeath();
    }

    public void ufoSpawn() {
        musicPlayer.ufo();
    }

    public void startEnemiesThread() throws ConcurrentModificationException, NullPointerException {
        enemyThread = new Thread(() -> {
            FPS = 15;
            int frameTime = 1000/FPS;
            while (isRunning) {
                long startTime = System.currentTimeMillis();
                if(enemies.size() != 0) {
                    moveEnemies();
                    if(bullets.size()<6) generateRandomNumber();
                    if(bullets.size()>0) bulletHit();
                }
                else {
                    moveBoss();
                    if (livesBoss > 3) {
                        if (bullets.size() < 5) generateRandomNumber();
                    }
                    else{
                        if (bullets.size() < 8) generateRandomNumber();
                    }
                    if(bullets.size()>0) bulletHit();

                }


                long sleepTime = calculateSleepTime(frameTime, getElapsedTime(startTime));
                if (sleepTime > 0) {
                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        enemyThread.start();
    }

    private long getElapsedTime(long startTime){
        return System.currentTimeMillis() - startTime;
    }

    private long calculateSleepTime(int frameTime,long elapsedTime){
        return frameTime - elapsedTime;
    }

    private List<Laser> createLasers() {
        return new ArrayList<>();
    }
    private List<Bullet> createBullets() {
        return new ArrayList<>();
    }

    private List<Walls> createEdgesWalls() {
        List<Walls> edgeswalls = new ArrayList<>();
        for (int c = 0; c < width; c++) {
            edgeswalls.add(new Walls(c, 0));
            edgeswalls.add(new Walls(c, height - 1));
        }
        for (int r = 1; r < height - 1; r++) {
            edgeswalls.add(new Walls(0, r));
            edgeswalls.add(new Walls(width - 1, r));
        }
        return edgeswalls;
    }
    public List<Protection> createProtections() {
        List<Protection> protections = new ArrayList<>();
        for (int i = 7; i < 12; i++) protections.add(new Protection(i, 20));
        for (int j = width / 2 - 7; j < width / 2 - 3; j++) protections.add(new Protection(j, 20));
        for (int k = width / 2 + 3; k < width / 2 + 7; k++) protections.add(new Protection(k, 20));
        for (int l = width - 12; l < width - 7; l++) protections.add(new Protection(l, 20));

        return protections;
    }

    private List<Enemy> createEnemies() {
        List<Enemy> enemies = new ArrayList<>();
        for (int c = 0; c < 5; c++) {
            for (int r = 0; r < 3; r++) {
                enemies.add(new Enemy(5 + c * 5, 5 + r * 3));
            }
        }
        return enemies;
    }
    public List<Boss> createBoss() {
        List<Boss> bigboss = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            bigboss.add(new Boss(22 + i * 6,6));
        }
        for (int i = 0; i < 5; i++) {
            bigboss.add(new Boss(23 + i, 7));
        }
        for (int i = 0; i < 3; i++) {
            bigboss.add(new Boss(24+ i, 8));
        }
        bigboss.add(new Boss(25,9));
        return bigboss;
    }
    public void generateRandomNumber() {
        Random a = new Random();
        Random b = new Random();
        if (enemies.size() != 0){
            int random = a.nextInt(enemies.size());

            bullets.add(new Bullet(enemies.get(random).getPosition().getX(), enemies.get(random).getPosition().getY() + 1));
        }
        if (enemies.size()==0 && bigboss.size() != 0){
            int random = b.nextInt(bigboss.size());

            bullets.add(new Bullet(bigboss.get(random).getPosition().getX(), bigboss.get(random).getPosition().getY()));
        }
        else return;


    }
    public void processKey(KeyStroke key, Screen screen) throws IOException {
        if (lasers.size() != 0){
            for (int i = 0; i < lasers.size() ; i++){
                lasers.get(i).moveUp();
                laserHit();
            }
        }

        if(key == null) return;
        if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
            screen.close();
            System.exit(0);
        }
        if (key.getKeyType() == KeyType.ArrowLeft) moveSpaceship(spaceship.moveLeft());
        if (key.getKeyType() == KeyType.ArrowRight) moveSpaceship(spaceship.moveRight());
        if (key.getKeyType() == KeyType.Enter) {
            lasers.add(new Laser(spaceship.getPosition().getX(),spaceship.getPosition().getY() - 1));
            laserShotPlayer();
        }
    }

    public void moveSpaceship(Position position) {
        if (notHittingWall(position))
            spaceship.setPosition(position);
    }

    public void moveEnemies(){
        int moveDir = canEnemiesMove();
        if (moveDir == 1 || moveDir == -1) moveEnemiesHorizontal(canEnemiesMove());

    }

    public void moveEnemiesHorizontal(int offset){
        for (Enemy enemy: enemies){
            enemy.setPosition(new Position(enemy.getPosition().getX() + offset, enemy.getPosition().getY()));
        }
    }

    public int canEnemiesMove(){
        Position leftEnemy = new Position(60,1);
        Position rightEnemy = new Position(0,1);
        for (Enemy enemy: enemies){
            if(enemy.getPosition().getX() > rightEnemy.getX()){
                rightEnemy.setX(enemy.getPosition().getX());
                rightEnemy.setY(enemy.getPosition().getY());
            }
            if (enemy.getPosition().getX() < leftEnemy.getX()){
                leftEnemy.setX(enemy.getPosition().getX());
                leftEnemy.setY(enemy.getPosition().getY());
            }
        }
        if(moveDir != -1 && notHittingWall(new Position(rightEnemy.getX()+1, rightEnemy.getY()))){
            moveDir = 1;
        }
        else if (moveDir != 1 && notHittingWall(new Position(leftEnemy.getX()-1, leftEnemy.getY()))){
            moveDir = -1;
        }
        else {
            moveDir = 2;
        }
        return moveDir;
    }

    public void moveBoss() {
        int moveDir = canBossMove();
        if (moveDir == 0) moveBossVertically();
        else moveBossHorizontal(moveDir);
    }
    public void moveBossHorizontal(int offset){
        for (Boss boss: bigboss){
            boss.setPosition(new Position(boss.getPosition().getX() + offset, boss.getPosition().getY()));
        }
    }
    public void moveBossVertically(){
        for (Boss boss: bigboss){
            boss.setPosition(new Position(boss.getPosition().getX(), boss.getPosition().getY() + 1));
        }
    }

    public int canBossMove(){
        Position leftboss = new Position(60,10);
        Position rightboss = new Position(1, 10);
        for (Boss boss: bigboss){
            if(boss.getPosition().getX() > rightboss.getX()){
                rightboss.setX(boss.getPosition().getX());
                rightboss.setY(boss.getPosition().getY());
            }
            if (boss.getPosition().getX() < leftboss.getX()){
                leftboss.setX(boss.getPosition().getX());
                leftboss.setY(boss.getPosition().getY());
            }
        }
        if(moveDir != -1 && notHittingWall(new Position(rightboss.getX()+1, rightboss.getY()))){
            moveDir = 1;
        }
        else if (moveDir != 1 && notHittingWall(new Position(leftboss.getX()-1, leftboss.getY()))){
            moveDir = -1;
        }
        else {
            moveDir = 0;
        }
        return moveDir;
    }

    public boolean notHittingWall(Position position) {
        for (Walls wall : edgeswalls)
            if (wall.getPosition().equals(position))
                return false;
        return true;
    }

    public void laserHit(){
        for (int i = 0; i < lasers.size() ; i++){
            for (Enemy enemy: enemies) {
                if (lasers.get(i).getPosition().equals(enemy.getPosition())) {
                    laserHitPlayer();
                    lasers.remove(i);
                    enemies.remove(enemy);
                    score += 10;
                    if (score == 150) ufoSpawn();
                    return;
                }
            }
            for (Walls walls: edgeswalls){
                if (lasers.get(i).getPosition().equals(walls.getPosition())){
                    lasers.remove(i);
                    return;
                }
            }
            for (Protection protection : protections) {
                if (lasers.get(i).getPosition().equals(protection.getPosition())) {
                    lasers.remove(i);
                    return;
                }
            }
            if (enemies.size() == 0) {
                for (Boss boss: bigboss){
                    if (lasers.get(i).getPosition().equals(boss.getPosition())){
                        laserHitPlayer();
                        lasers.remove(i);
                        livesBoss -= 1;
                        if(livesBoss == 0){
                            bigboss.remove(boss);
                            score += 100;
                        }
                        return;
                    }
                }

            }
        }
    }

    public void bulletHit() {
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).moveDown();
            for (int j = 0; j < protections.size(); j++) {
                if (bullets.get(i).getPosition().equals(protections.get(j).getPosition())) {
                    protections.remove(j);
                    bullets.remove(i);
                    return;
                }
            }
            if (bullets.get(i).getPosition().getY() == height - 1) {
                bullets.remove(i);
                return;
            }
            if (bullets.get(i).getPosition().equals(spaceship.getPosition())) {
                bullets.remove(i);
                lives--;
                playerHit();
                if (lives == 0) playerExplosion();
                return;
            }
        }
    }


    public void scoreDraw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.putString(new TerminalPosition(2, 1), "Score:");
        graphics.putString(new TerminalPosition(9, 1), Integer.toString(score));
    }

    public void livesDraw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.putString(new TerminalPosition(2, 2), "Lives:");
        graphics.putString(new TerminalPosition(9, 2), Integer.toString(lives));
    }
    public void livesBossDraw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.putString(new TerminalPosition(2, 3), "BossEnemy Lives:");
        graphics.putString(new TerminalPosition(19, 3), Integer.toString(livesBoss));
    }


    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000")); //cor do background
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        spaceship.draw(graphics);
        for (Walls wall : edgeswalls)
            wall.draw(graphics);
        for (Protection protection : protections) {
            protection.draw(graphics);
        }
        for (Enemy enemy : enemies) {
            enemy.draw(graphics);
        }
        for (Laser laser: lasers){
            laser.draw(graphics);
        }
        for (Bullet bullet : bullets){
            bullet.draw(graphics);
        }
        if (enemies.size()==0){
            for (Boss boss: bigboss){
                boss.draw(graphics);
            }
            livesBossDraw(graphics);
        }

        scoreDraw(graphics);
        livesDraw(graphics);
    }
    
    public int getLives() {
        return lives;
    }
    public int getScore() {
        return score;
    }

    public int getLivesBoss() {
        return livesBoss;
    }
    public void setEnemies(List<Enemy>enemies){
        this.enemies = enemies;
    }
    public void setLasers (List<Laser>lasers){
        this.lasers = lasers;
    }
    public void setBullets(List<Bullet>bullets){
        this.bullets = bullets;
    }
    public void setSpaceship(Spaceship spaceship){
        this.spaceship = spaceship;
    }
    public Spaceship getSpaceship(){
        return spaceship;
    }
    public void setBigBoss(List<Boss> bigboss){
        this.bigboss = bigboss;
    }
    public void setLives(int vidas) {
        this.lives = vidas;
    }
    public int getHeight(){
        return height;
    }
    public int getWidth(){
        return width;
    }
    public void resetProtection() {
        this.protections = createProtections();
    }
    public void resetLives() {
        this.lives = 3;
    }


}
