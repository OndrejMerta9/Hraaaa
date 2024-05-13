package logic;

import java.awt.*;

public class Player extends Entity {
    int speed = 5;



    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Player(int x, int y, int width, int height,String file){
        super(x,y,width,height);
        loader = new ImageLoader(file);

    }
    public int centerX(){
        return x + width / 2;
    }
    public int centerY(){
        return y + height / 2;
    }
}
