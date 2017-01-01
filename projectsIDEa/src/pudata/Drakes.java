package pudata;

/**
 * Created by Ololo on 21.10.2016.
 */
public class Drakes extends Player {
    private FoundWay fway;
    private GamePlace forCheck;
    public Drakes(GamePlace forCheck) {
        this.forCheck=forCheck;
        fway=new FoundWay(this.forCheck);
        speed=5;
        right = "/Draker.png";
        left = "/Drakel.png";
        up = "/Drakeu.png";
        down = "/Draked.png";
        playerPic(-1,0);
    }

    public boolean drakeDir(int x1,int y1,int x2,int y2) {
        //System.out.println("здесь?");
        fway.findingWay(x1,y1,x2,y2);
        if(fway.way.size()>0){
            if(fway.way.get(fway.way.size()-1).x>x1){
                dX=1;
                dY=0;
               // System.out.println("1111111");
            }   if (fway.way.get(fway.way.size()-1).x<x1){
                dX=-1;
                dY=0;
               // System.out.println("22222222");
            }  if (fway.way.get(fway.way.size()-1).y>y1){
                dY=1;
                dX=0;
               // System.out.println("3333333");
            } if(fway.way.get(fway.way.size()-1).y<y1){
                dY=-1;
                dX=0;
               // System.out.println("4444444");
            }
            //System.out.println("sye");
            playerPic(dX,dY);
            return true;
        }
        else {
            dX=0;
            dY=0;
           // System.out.println("bye");
            return false;
        }
    }
}
