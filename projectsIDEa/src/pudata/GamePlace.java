package pudata;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by Ololo on 21.10.2016.
 */

public class GamePlace {
    private int eX;
    private int eY;
    final static int cellCount=12;
    private Cell.CellType[][] map= new Cell.CellType[cellCount][cellCount];;

    public int getH() {

        return map.length;
    }

    public int getW() {

        return map[0].length;
    }

    private Cell.CellType[][] loadMap(String path) {
        try
        {
            int k;
            Scanner sc = new Scanner(new File(path));
            for (int i = 1; i < getH()-1; i++)
            {
                for (int j = 1; j < getW()-1; j++)
                {
                    k=sc.nextInt();
                    if (k==1){
                        map[i][j] = Cell.CellType.TABLE;
                    }else if (k==2){
                        map[i][j] = Cell.CellType.FLOOR;
                    }else if (k==3){
                        map[i][j]= Cell.CellType.TV;
                        eX=j;
                        eY=i;
                    }
                }
            }
            sc.close();
            System.out.println("Close");
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found!");
            return map;
        }
        catch (InputMismatchException e)
        {
            System.out.println("InputMismatchException");
            return map;
        }

        return map;
    }

    public void clean(){
        for (int i=0;i<getH(); i++){
            for (int j=0;j<getW();j++){
                if (i ==0||j==0||i==(getH()-1)||j==(getW()-1)){map[i][j]= Cell.CellType.TABLE;}
                else {map[i][j]= Cell.CellType.FLOOR;}
                //   if (i==5) map[i][j]=1;
            }
        }
    }

    public GamePlace() {
        clean();
        map = loadMap("editMap.txt");
        //String level_number="/level"+1;
        //System.out.println(level_number);
        //map[fy][fx]=3;
    }

    public GamePlace(int level){
        clean();
        String level_number="level"+level+".txt";
        System.out.println(level_number);
        map = loadMap(level_number);
    }

    public boolean changeCell(int x, int y, Cell.CellType type){
        if(x!=0&&x!=11&&y!=0&&y!=11){
            map[y][x]=type;
            return true;
        }
        return false;
    }

    //public int[][] getMap() {
      //  return map;
   // }

    public int getExitX(){
        return eX;
    }

    public int getExitY(){
        return eY;
    }
//изменить на перечисление
    public Cell.CellType getCellType(int x, int y) {

        return map[y][x];
    }

}
