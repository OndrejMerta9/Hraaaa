package logic;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageLoader {
    BufferedImage image;

    public BufferedImage getImage() {
        return image;
    }

    public ImageLoader(String file){
        try {
            image = ImageIO.read(new File("src/main/resources/"+file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
