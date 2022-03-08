package com.bjsda;

import java.awt.*;

//炮弹
public class Shell extends MyGameOb{
    double degree;

    @Override
    public void drawSelf(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.yellow);
        g.fillOval(x,y,width,height);
        x+=speed*Math.cos(degree);
        y+=speed*Math.sin(degree);

        if (y>GameUtil.FRAME_HIGH-10||y<30){
            degree =-degree;
        }
        if (x>GameUtil.FRAME_WIDTH-10||x<0){
            degree -=Math.PI-degree;
        }
        g.setColor(c);
    }

    public Shell() {
        degree = Math.random()*Math.PI*2;
        x=100;
        y=100;
        width = 10;
        height = 10;
        speed = 8;
    }
}
