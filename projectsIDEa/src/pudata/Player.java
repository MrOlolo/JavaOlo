package pudata;

import javax.swing.*;
import java.awt.*;

/**
 * Created by mrkos_000 on 22.12.2016.
 */
public class Player implements Render {
    private int width = 30;
    private int height = 35;
    private Image imageSrc;
    protected int speed = 7;

    protected int dX = 0;
    protected int dY = 0;
    protected int posX = 0;
    protected int posY = 0;

    protected String right;
    protected String left;
    protected String up;
    protected String down;


    public Player(){}
    public int getSpeed(){
        return speed;
    }

    public int getHeight(){
        return height;
    }

    public int getWidth(){
        return width;
    }

    public void playerPic(int dx,int dy){
        int h,w;
        h = (height > width) ?  height:width;
        w = (height > width) ?  width:height;
        if (dx==1||(dx==0&&dy==0)){
            imageSrc = new ImageIcon(getClass().getResource(right)).getImage();
            height=h;
            width=w;
        } else if(dx==-1) {
            imageSrc = new ImageIcon(getClass().getResource(left)).getImage();
            height=h;
            width=w;
        } else if(dy==-1) {
            imageSrc = new ImageIcon(getClass().getResource(up)).getImage();
            height=w;
            width=h;
        } else if(dy==1) {
            imageSrc = new ImageIcon(getClass().getResource(down)).getImage();
            height=w;
            width=h;
        }
    }

    @Override
    public void render(Graphics g) {

        g.drawImage(imageSrc, posX, posY, width, height, null);
    }
}

