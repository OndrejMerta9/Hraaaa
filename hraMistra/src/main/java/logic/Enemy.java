package logic;

import java.awt.*;

public class Enemy extends Entity {
    int speed = 1;
    public Enemy(int x, int y, int width, int height, String file){
        super(x, y, width, height);
        loader = new ImageLoader(file);

    }
    public void move(){
        y += 1;
    }


    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
