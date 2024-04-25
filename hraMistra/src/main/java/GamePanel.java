import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    int windowWidth = 800, windowHeight = 600;
    boolean playerUp, playerDown, playerLeft, playerRight;
    Player player;
    boolean gameActive;
    Random random;
    ImageLoader titleScreen;
    ArrayList<Enemy> enemies, enemiesToRemove;
    int enemySpawnTimer, enemySpawnInterval = 120;
    int killCount, castleHealth = 10, shootInterval = 30, shootTimer;
    ArrayList<Bullet> bullets, bulletsToRemove;
    boolean shoot;
    ArrayList<Velo> velos, velosToRemove;
    ImageLoader Background;

    GamePanel(){
        setPreferredSize(new Dimension(windowWidth, windowHeight));
        player = new Player(windowWidth /2,windowHeight - 60 );
        enemies = new ArrayList<>();
        enemiesToRemove = new ArrayList<>();
        random = new Random();
        bullets = new ArrayList<>();
        bulletsToRemove = new ArrayList<>();
        velos = new ArrayList<>();
        velosToRemove = new ArrayList<>();
        titleScreen = new ImageLoader("sejmiHonzu.png");
        Background = new ImageLoader("Hrad.png");

        setFocusable(true);
        requestFocus();
        addKeyListener(this);
        Timer timer = new Timer(1000/120, this);
        timer.start();
    }

    public void paintComponent(Graphics g){
        g.fillRect(0,0,windowWidth,windowHeight);
        if(!gameActive){
            g.drawImage(titleScreen.image, 0,0,windowWidth,windowHeight,null);

        }
        if(gameActive) {
            g.drawImage(Background.image, 0, 0, windowWidth, windowHeight, null);
            for (Enemy enemy : enemies) {
                enemy.draw(g);
            }
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.setColor(Color.white);
            g.drawString(String.valueOf(killCount), 400, 100);

            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.setColor(Color.white);
            g.drawString(String.valueOf(castleHealth), 50, 100);
            for (Bullet bullet : bullets) {
                bullet.draw(g);
            }
            for (Velo velo : velos) {
                velo.draw(g);
            }
            player.draw(g);




        }


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(gameActive){
            updatePlayer();
            updateEnemy();
            collisions();
            remove();
            updateBullet();
            for (Velo velo : velos){
                velo.update();
            }
        }
        repaint();
    }

    public void collisions(){
        for (Enemy enemy:enemies){
            if(player.getRect().intersects(enemy.getRect())){
                handleEnemyPlayerCollision(enemy);
                addVelo(enemy.x,enemy.y);
            }
            for(Bullet bullet:bullets){
                if (enemy.getRect().intersects(bullet.getRect()) ){
                    enemiesToRemove.add(enemy);
                    bulletsToRemove.add(bullet);
                    killCount += 1;
                    addVelo(enemy.x,enemy.y);
                }
            }
            if(enemy.y > windowHeight){
                enemiesToRemove.add(enemy);
                castleHealth -= 1;
            }


        }
    }
    public void handleEnemyPlayerCollision(Enemy enemy){
        enemiesToRemove.add(enemy);
    }
    public void updatePlayer(){
        if(playerLeft){
            player.x -= player.speed;
        }
        if(playerRight){
            player.x += player.speed;
        }

        if(player.x >= windowWidth - player.width){
            player.x = windowWidth - player.width;
        }
        if(player.y >= windowHeight - player.height) {
            player.y = windowHeight - player.height;
        }
        if(player.y <= 0){
            player.y = 0;
        }
        if(player.x <= 0){
            player.x = 0;
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
        enemies.add(new Enemy(random.nextInt(0, windowWidth),-100));
    }
    public void remove(){
        enemies.removeAll(enemiesToRemove);
        bullets.removeAll(bulletsToRemove);
        velos.removeAll(velosToRemove);
    }
    public void updateBullet(){
        shootTimer++;
        if(shoot && shootTimer >= shootInterval){
            addBullet();
            shootTimer = 0;
        }

        for (Bullet bullet : bullets){
            bullet.y -= bullet.speed;
        }

    }
    public void addBullet(){
        bullets.add(new Bullet(player.centerX(), player.centerY()));

    }
    public void addVelo(int x,int y) {
        velos.add(new Velo(x, y));
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
            if (keys == KeyEvent.VK_SPACE){
                shoot = true;
            }
        }

        if(!gameActive){
            if(keys == KeyEvent.VK_ENTER){
                gameActive = true;
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



