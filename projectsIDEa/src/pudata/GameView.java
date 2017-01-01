package pudata;

/**
 * Created by mrkos_000 on 22.12.2016.
 */
public class GameView {
    private Canvas canvas;
    private GamePlace map;
    //private User ps3;
  // private Drakes[] drake;
    Sprite screen;

    public GameView(Canvas canvas){
        this.canvas = canvas;
    }

    public void drawMap(GamePlace map){
        this.map =map;
        int mapW = this.map.getW();
        int mapH =this.map.getH();
        Cell cell;
        for (int i = 0; i < mapH; i++) {
            for (int j = 0; j < mapW; j++) {
                if (map.getCellType(j, i)!=null){
                    cell = Cell.CellByType(this.map.getCellType(j, i));
                    cell.posX = j * Cell.Size;
                    cell.posY = i * Cell.Size;
                    canvas.addRender(cell);
                }
            }
        }
    }


    public void setObjForRender(Render obj){
        canvas.addRender(obj);
       // canvas.removeRender(screen);
    }

    protected void updateView() {
        canvas.repaint();
    }

    public void endGame(){
        screen=new Sprite("/maxresdefault.jpg",0,0,canvas.getWidth(),canvas.getHeight());
        canvas.addRender(screen);
    }

    public void nextLevel(){
        screen=new Sprite("/nextlevel.jpg",0,0,canvas.getWidth(),canvas.getHeight());
        canvas.addRender(screen);
    }

}
