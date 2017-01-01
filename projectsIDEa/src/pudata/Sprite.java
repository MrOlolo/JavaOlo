package pudata;

/**
 * Created by Ololo on 22.10.2016.
 */
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Image;

public class Sprite implements Render {
    private int width ;
    private int height;
    private Image imageSrc;
    private int posX;
    private int posY;

    Sprite(String path,int posX,int posY,int width,int height){
        this.posX=posX;
        this.posY=posY;
        this.width=width;
        this.height=height;
        imageSrc = new ImageIcon(getClass().getResource(path)).getImage();
    }
    @Override
    public void render(Graphics g) {
        g.drawImage(imageSrc, posX, posY, width, height, null);
    }
}