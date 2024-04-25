import java.awt.*;

public class Velo {
    int x,y,width = 20,height = 20;
    ImageLoader loader;
    double velocityY = 1;
    Velo (int x,int y){
        this.x = x;
        this.y = y;
        loader = new ImageLoader("velo.png");
    }
    public void draw(Graphics g){
        g.drawImage(loader.image, x, y, width, height, null );
    }
    public void update(){
        velocityY += 0.1;
        y += (int) velocityY;
    }

}
