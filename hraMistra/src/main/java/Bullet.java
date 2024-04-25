import java.awt.*;

public class Bullet {
    int y,x,width=10, height=10, speed=10;
    Bullet(int x,int y){
        this.x = x;
        this.y = y;
    }
    public void draw(Graphics g){
        g.setColor(Color.RED);
        g.fillOval(x,y,width,height);
    }
    public  Rectangle getRect(){
        return  new Rectangle(x,y,width,height);
    }
}
