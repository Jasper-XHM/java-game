package com.bjsda;

import java.awt.*;
//游戏物体的根类
public class MyGameOb {
    Image img;
    int x,y;
    int speed;
    int width,height;

    public void drawSelf(Graphics g){
        g.drawImage(img,x,y,width,height,null);
    }
    public Rectangle getRect(){
        return new Rectangle(x,y,width,height);
    }
    public MyGameOb(Image img, int x, int y, int speed, int width, int height) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.width = width;
        this.height = height;
    }

    public MyGameOb(Image img, int x, int y, int speed) {
        this(img);
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public MyGameOb(Image img, int x, int y) {
        this(img);
        this.x = x;
        this.y = y;
    }
    public MyGameOb(Image img) {
        this.img = img;
        if (this.img !=null){
            this.width = img.getWidth(null);
            this.height = img.getHeight(null);
        }
    }

    public MyGameOb() {
    }


}
