package com.bjsda;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class GameUtil {
    public static final int FRAME_WIDTH=500;
    public static final int FRAME_HIGH =500;
    private GameUtil(){}
    public static Image getImage(String path){
        Image image = null;
        URL url = GameUtil.class.getClassLoader().getResource(path);
        try {
            image = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

}
