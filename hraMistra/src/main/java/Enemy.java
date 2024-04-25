import java.awt.*;
import java.util.Random;

public class Enemy {
    ImageLoader loader;
    Player player;
    int x,y,width = 30,height = 50, speed = 1;
    Enemy(int x, int y){
        this.y = y;
        this.x = x;
        loader = new ImageLoader("gonza.png");

    }
    public void move(){
        y += 1;
    }
    public void draw(Graphics g){
        g.setColor(Color.black);
        g.drawImage(loader.image,x,y,width,height,null);
    }
    public  Rectangle getRect(){
        return  new Rectangle(x,y,width,height);
    }


}
