import logic.Apple;
import logic.Bullet;
import logic.Enemy;
import logic.ImageLoader;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    Draw draw;
    public static void main(String[] args) {
        new GameFrame();
    }
    GameFrame(){
        setTitle("DEFEND THE CASTLE");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        draw = new Draw();
        add(draw);
        setBackground(Color.CYAN);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

class Draw extends JPanel implements Runnable{
    int windowWidth = 800, windowHeight = 600;
    GameLogic logic;
    ImageLoader Background, titleScreen;


    Draw(){
        setPreferredSize(new Dimension(windowWidth, windowHeight));
        logic = new GameLogic(windowWidth, windowHeight);
        Thread thread = new Thread(this);
        thread.start();
        setFocusable(true);
        requestFocus();
        addKeyListener(logic);
        titleScreen = new ImageLoader("menu.png");
        Background = new ImageLoader("Hrad.png");
    }
    public void paintComponent(Graphics g){
        g.fillRect(0,0,windowWidth,windowHeight);
        if(!logic.gameActive){
            g.drawImage(titleScreen.getImage(), 0,0,windowWidth,windowHeight,null);

        }
        if(logic.gameActive) {
            g.drawImage(Background.getImage(), 0, 0, windowWidth, windowHeight, null);
            for (Enemy enemy : logic.enemies) {
             g.drawImage(enemy.getLoader().getImage(), enemy.getX(), enemy.getY(),enemy.getWidth(),enemy.getHeight(),null);
            }
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.setColor(Color.white);
            g.drawString(String.valueOf(logic.killCount), 400, 100);

            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.setColor(Color.white);
            g.drawString(String.valueOf(logic.castleHealth), 50, 100);
            for (Bullet bullet : logic.bullets) {
                g.setColor(Color.RED);
                g.fillOval(bullet.getX(),bullet.getY(),bullet.getWidth(),bullet.getHeight());
            }
            for (Apple apple : logic.apples) {
                g.drawImage(apple.getLoader().getImage(),apple.getX(),apple.getY(),apple.getWidth(),apple.getHeight(), null );
            }
            g.drawImage(logic.player.getLoader().getImage(),logic.player.getX(),logic.player.getY(),logic.player.getWidth(),logic.player.getHeight(), null);


        }
    }

    @Override
    public void run() {
        while (true){
            logic.update();
            if(logic.gameActive){
                logic.updatePlayer();
                logic.updateEnemy();
                logic.collisions();
                logic.remove();
                logic.updateBullet();
                for (Apple apple : logic.apples){
                    apple.update();
                }
            }
            repaint();


            try {
                Thread.sleep(1000/60);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
