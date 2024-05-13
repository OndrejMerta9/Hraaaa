import logic.Apple;
import logic.Bullet;
import logic.Enemy;
import logic.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class GameLogic implements KeyListener {
    int windowWidth, windowHeight;
    boolean playerUp, playerDown, playerLeft, playerRight;
    Player player;
    boolean gameActive;
    Random random;
    ArrayList<Enemy> enemies, enemiesToRemove;
    int enemySpawnTimer, enemySpawnInterval = 120;
    int killCount, castleHealth = 10, shootInterval = 30, shootTimer;
    ArrayList<Bullet> bullets, bulletsToRemove;
    boolean shoot;
    ArrayList<Apple> apples, applesToRemove;


    GameLogic(int windowWidth, int windowHeight){
        this.windowHeight = windowHeight;
        this.windowWidth = windowWidth;
        player = new Player(windowWidth /2,windowHeight - 60,30,50,"Merta.png" );
        enemies = new ArrayList<>();
        enemiesToRemove = new ArrayList<>();
        random = new Random();
        bullets = new ArrayList<>();
        bulletsToRemove = new ArrayList<>();
        apples = new ArrayList<>();
        applesToRemove = new ArrayList<>();

    }

    public void collisions(){
        for (Enemy enemy:enemies){
            if(player.getRect().intersects(enemy.getRect())){
                handleEnemyPlayerCollision(enemy);
                addApple(enemy.getX(),enemy.getY());
            }
            for(Bullet bullet:bullets){
                if (enemy.getRect().intersects(bullet.getRect()) ){
                    enemiesToRemove.add(enemy);
                    bulletsToRemove.add(bullet);
                    killCount += 1;
                    addApple(enemy.getX(),enemy.getY());
                }
            }
            if(enemy.getY() > windowHeight){
                enemiesToRemove.add(enemy);
                castleHealth -= 1;
            }


        }
        for (Apple apple :apples){
            if (apple.getRect().intersects(player.getRect())){
                applesToRemove.add(apple);
                castleHealth += 1;
            }
        }
    }
    public  void  update(){
        if(castleHealth <= 0){
            gameActive = false;
        }
    }
    public void handleEnemyPlayerCollision(Enemy enemy){
        enemiesToRemove.add(enemy);
    }
    public void updatePlayer(){
        if(playerLeft){
            player.setX(player.getX() - player.getSpeed());
        }
        if(playerRight){
            player.setX(player.getX() + player.getSpeed());
        }

        if(player.getX() >= windowWidth - player.getWidth()){
            player.setX(windowHeight - player.getWidth());
        }
        if(player.getY() >= windowHeight - player.getHeight()) {
            player.setY(windowHeight - player.getHeight());

        }
        if(player.getY() <= 0){
            player.setY(0);
        }
        if(player.getX() <= 0){
            player.setX(0);
        }

    }
    public void updateEnemy(){
        enemySpawnTimer++;
        if(enemySpawnTimer >= enemySpawnInterval){
            enemySpawnTimer = 0;
            addEnemy();
        }
        for(Enemy enemy : enemies){
            enemy.move();
        }
    }
    public void addEnemy(){
        enemies.add(new Enemy(random.nextInt(0, windowWidth),-100, 30,50, "gonza.png"));
    }
    public void remove(){
        enemies.removeAll(enemiesToRemove);
        bullets.removeAll(bulletsToRemove);
        apples.removeAll(applesToRemove);
    }
    public void updateBullet(){
        shootTimer++;
        if(shoot && shootTimer >= shootInterval){
            addBullet();
            shootTimer = 0;
        }

        for (Bullet bullet : bullets){
            bullet.setY(bullet.getY() - bullet.getSpeed());
        }

    }
    public void addBullet(){
        bullets.add(new Bullet(player.centerX(), player.centerY(),10,10));

    }
    public void addApple(int x,int y) {apples.add(new Apple(x,y ,20,20,"Jablko.png"));
    }
    public void reset(){
        castleHealth = 10;
        bullets.clear();
        apples.clear();
        enemies.clear();
        killCount = 0;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keys = e.getKeyCode();
        if(gameActive){
            if(keys == KeyEvent.VK_S){
                playerDown = true;
            }
            if(keys == KeyEvent.VK_W){
                playerUp = true;
            }
            if(keys == KeyEvent.VK_A){
                playerLeft = true;
            }
            if(keys == KeyEvent.VK_D){
                playerRight = true;
            }
            if (keys == KeyEvent.VK_ENTER){
                shoot = true;
            }
        }

        if(!gameActive){
            if(keys == KeyEvent.VK_SPACE){
                gameActive = true;
                reset();
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keys = e.getKeyCode();
        if(keys == KeyEvent.VK_S){
            playerDown = false;
        }
        if(keys == KeyEvent.VK_W){
            playerUp = false;
        }
        if(keys == KeyEvent.VK_A){
            playerLeft = false;
        }
        if(keys == KeyEvent.VK_D){
            playerRight = false;
        }
        if (keys == KeyEvent.VK_SPACE){
            shoot = false;
        }

        }

    }



