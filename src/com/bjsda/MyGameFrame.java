package com.bjsda;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

import static com.bjsda.GameUtil.*;

public class MyGameFrame extends Frame {
    Image bg = GameUtil.getImage("Image/bg.jpg");
    Image planeImg = GameUtil.getImage("Image/plane.png");


    int x=200,y=200;
    Plane plane = new Plane(planeImg,300,300,7);
    Shell shells[] = new Shell[100];
    Explode explode;
    Date startTime = new Date();
    Date endTime;
    int time;
    public void WinWou(){
        this.setTitle("小飞机打怪兽");//标题
        this.setVisible(true);//窗口默认不可见，需要让它可见（true）
        this.setSize(FRAME_WIDTH,FRAME_HIGH);//宽高
        this.setLocation(300,300);//窗口的位置
        //窗口监听
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        //启动窗口绘制线程
        new PaintThread().start();
        this.addKeyListener(new KeyMonitor());
        for (int i =0;i<shells.length;i++){
            shells[i] = new Shell();
        }
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(bg,0,0,FRAME_WIDTH,FRAME_HIGH,null);
        //g.drawImage(planeImg,20,20,FRAME_WIDTH,FRAME_HIGH,null);
        plane.drawSelf(g);
        for (int i = 0;i<shells.length;i++){
            shells[i].drawSelf(g);
            boolean peng = shells[i].getRect().intersects(plane.getRect());
            if (peng){

                //System.out.println("飞机死了");

                plane.live=false;
                endTime = new Date();
                time =(int) ((endTime.getTime()-startTime.getTime())/1000);
                if (explode==null){
                    explode = new Explode(plane.x, plane.y);
                }
                explode.draw(g);
            }

        }
        if (!plane.live){

            printlnss(g,"你死了！ "+time+"秒真男人",40, 100, 200, Color.red);
        }
    }

    public void printlnss(Graphics g,String str,int size,int x,int y,Color color){
        Font oldfont = g.getFont();
        Color oldcolor = g.getColor();
        Font font = new Font("宋体",Font.BOLD,size);
        g.setColor(color);
        g.setFont(font);
        g.drawString(str, x, y);

        g.setColor(oldcolor);
        g.setFont(oldfont);
    }
    class KeyMonitor extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
//            System.out.println("按下"+e.getKeyCode());
            plane.addDirection(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
//            System.out.println("抬起"+e.getKeyCode());
            plane.minusDirection(e);
        }
    }

    class PaintThread extends Thread{
        @Override
        public void run() {
            while (true){
                repaint();
                try {
                    Thread.sleep(40);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        MyGameFrame frame = new MyGameFrame();
        frame.WinWou();
    }
}
