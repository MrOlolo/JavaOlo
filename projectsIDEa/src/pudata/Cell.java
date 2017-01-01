package pudata;

/**
 * Created by Ololo on 22.10.2016.
 */
import java.awt.Graphics;
import java.awt.Image;
import java.util.HashMap;
import javax.swing.ImageIcon;

public class Cell implements Render {
/*   enum CellType:
* TABLE - wall, you cant step here
* FLOOR - free, you can step
* TV - exit, end of level
 */
    enum CellType {TABLE,FLOOR,TV}

    final public static int Size = 50;

    private static HashMap<String,Image> images = new HashMap<String,Image>();

    public String image;

    public int posX;

    public int posY;

    public boolean canStep;

    public Cell(String image, int posX, int posY, boolean canStep) {
        this.image = image;
        this.posX = posX;
        this.posY = posY;
        this.canStep = canStep;
    }

    public static  Cell CellByType(CellType cellType) {
        if(cellType == CellType.TABLE) {
            return new Cell( "/8bit_table.png", 0, 0, false);
        }
        if(cellType == CellType.FLOOR) {
            return new Cell( "/8bitfloor1.jpg", 0, 0, true);
        }
        if(cellType == CellType.TV) {
            return new Cell( "/8bit_tabletv.png", 0, 0, true);
        }
        return null;

    }
    @Override
    public void render(Graphics g) {
        g.drawImage(getImage(image), posX, posY, Size, Size, null);
    }


    public static Image getImage(String name) {
        if(images.get(name) == null) {
            images.put(name, new ImageIcon(Cell.class.getResource(name)).getImage());
        }
        return images.get(name);
    }


}
