package pudata;

/**
 * Created by Ololo on 22.10.2016.
 */
import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;


public class Canvas extends JPanel {

    private Image bufer = null;

    private ArrayList<Render> renders = new ArrayList <Render> ();

    public void addRender(Render render) {

        renders.add(render);
    }

    public void removeRender(Render delRender){
        renders.remove(delRender);
    }

    public void cleanRenders() {
        renders.clear();
    }

    public void paint(Graphics g) {
        g.clearRect(0, 0, getWidth(), getHeight());
        g.fillRect(0, 0, getWidth(), getHeight());
        for(Render render : renders) {
            render.render(g);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paint(g);

        if(bufer == null) {
            bufer = createImage(getWidth(), getHeight());
        }
        paint(bufer.getGraphics());
        g.drawImage(bufer, 0, 0, null);
    }
}
