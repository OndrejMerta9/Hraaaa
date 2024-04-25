import java.awt.*;

public class Player {
    ImageLoader imageLoader;
    int x,y,width = 30,height = 50, speed = 5;
    Player(int x, int y){
        this.x = x;
        this.y = y;
        imageLoader = new ImageLoader("Merta.png");

    }
    public void draw(Graphics g){
        g.setColor(Color.blue);
        g.drawImage(imageLoader.image, x,y,width,height,null);
    }
    public int centerX(){
        return x + width / 2;
    }
    public int centerY(){
        return y + height / 2;
    }

    public  Rectangle getRect(){
        return  new Rectangle(x,y,width,height);
    }

}
