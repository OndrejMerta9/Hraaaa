package logic;


import java.awt.*;

public class Entity extends Cordinates {
    int width;
    ImageLoader loader;
    int height;

    public ImageLoader getLoader() {
        return loader;
    }

    Entity(int x, int y, int width, int height) {
        super(x, y);
        this.width = width;
        this.height = height;
    }
    public Rectangle getRect(){
        return  new Rectangle(x,y,width,height);
    }




    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
