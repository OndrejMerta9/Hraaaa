package logic;

import java.awt.*;

public class Bullet extends Entity {
    int speed=10;
    public Bullet(int x, int y, int width, int height){
        super(x, y, width, height);
    }
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
