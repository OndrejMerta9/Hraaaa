package logic;

import java.awt.*;

public class Apple extends Entity {
    double velocityY = 1;
    public Apple(int x, int y, int width, int height, String file){
        super(x, y, width, height);
        loader = new ImageLoader(file);
    }
    public void update(){
        velocityY += 0.1;
        y += (int) velocityY;
    }

}
