package pudata;

/**
 * Created by Ololo on 22.10.2016.
 */

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Game implements Runnable {
    private boolean run;
    private int lvl=1;
    private final int delay = 70;
    private Canvas canvas;
    private GameView view;
    private GamePlace map;
    private User user;
    private Drakes[] drake;

    public void game(){
        canvas = new Canvas();
        view=new GameView(canvas);
        map = new GamePlace(lvl);
        view.drawMap(map);
        user = new User();
        drake=new Drakes[3];
        //for(int i=0;i<3;i++){
         //   drake[i] = new Drakes(map);
           // view.setObjForRender(drake[i]);
       // }
        //drawMap();
        canvas.setFocusable(true);
        canvas.addKeyListener(keyListener);
    }

    public void loadEditMap(){
        stop();
        lvl=0;
        map = new GamePlace();
        view.drawMap(map);
        view.updateView();
    }

    public void start() {
        if (run) {
            return;
        }
        canvas.cleanRenders();
        run = true;
        //canvas = new Canvas();
       // map = new GamePlace();
        //user = new User();
        if(lvl==0){
            lvl=1;
        } else {
            map = new GamePlace(lvl);
        }
        view.drawMap(map);
        //user = new User();
        user.dX=0;
        user.dY=0;
        view.setObjForRender(user);
        for(int i=0;i<3;i++){
            //drake[i].dX=0;
           // drake[i].dY=0;
            drake[i] = new Drakes(map);
            view.setObjForRender(drake[i]);
        }
        user.posX = Cell.Size;
        user.posY = Cell.Size;
        drake[0].posX=10*Cell.Size;
        drake[0].posY=10*Cell.Size;
        drake[1].posX=10*Cell.Size;
        drake[1].posY=Cell.Size;
        drake[2].posX=Cell.Size;
        drake[2].posY=10*Cell.Size;
       // canvas = new Canvas();
       // map = new GamePlace();
      //  user = new User();

       // for(int i=0;i<3;i++){
     //       drake[i] = new Drakes();
      //  }
        System.out.println("GOGOGO");
        new Thread(this).start();
    }

    private void stop() {
        run = false;
    }

    private KeyAdapter keyListener = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                user.dY = 0;
                user.dX = -1;
                System.out.println("left");
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                user.dY = 0;
                user.dX = 1;
                System.out.println("right");
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                user.dX = 0;
                user.dY = -1;
                System.out.println("up");
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                user.dX = 0;
                user.dY = 1;
                System.out.println("down");
            }
            user.playerPic(user.dX, user.dY);
        }
//        @Override
//        public void keyReleased(KeyEvent e) {
//
//            if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_UP) {
//                user.dX = 0;
//            }
//        }
    };

    public Canvas getCanvas() {
        return canvas;
    }

    @Override
    public void run() {
        while (run) {
            try {
                TimeUnit.MILLISECONDS.sleep(delay);
            } catch (InterruptedException e) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, e);
            }
            update();
        }
    }

    private void update() {
       // canvas.addRender(user);
        if ((user.dX != 0 || user.dY != 0) && canMove(user)) {
           // System.out.println(user.dX + user.dY);
            user.posX += user.dX * user.getSpeed();
            user.posY += user.dY * user.getSpeed();
        }
        for(int i=0;i<3;i++) {
            //canvas.addRender(drake[i]);
            if (Math.abs((drake[i].posX -(user.posX)))<25 && (Math.abs(drake[i].posY -(user.posY))<25)) {
                gameOver();
                break;
            }
            /*if ((Math.round(drake[i].posX/Cell.Size) ==Math.round(user.posX/Cell.Size))
                    && (Math.round(drake[i].posY/Cell.Size) ==Math.round(user.posY/Cell.Size))) {
                gameOver();
                break;
            }*/
            if (user.dY!=0|| user.dX!=0){
            drake[i].drakeDir(Math.round((drake[i].posX)/ Cell.Size), Math.round((drake[i].posY)/ Cell.Size),
                    Math.round(user.posX / Cell.Size), Math.round(user.posY / Cell.Size));}
            if (canMove(drake[i])) {
            drake[i].posX += drake[i].dX * drake[i].getSpeed();
            drake[i].posY += drake[i].dY * drake[i].getSpeed();
            } else if(drake[i].dX!=0){
                drake[i].posY-=drake[i].getSpeed();
            } else if(drake[i].dY!=0){
                drake[i].posX-=drake[i].getSpeed();
            }
        }
        if((Math.ceil(user.posX/Cell.Size)==map.getExitX())&&(Math.ceil(user.posY/Cell.Size)==map.getExitY())){
            nextLevel();
        }
        view.updateView();
        //canvas.repaint();
    }

    private void gameOver() {
        stop();
        view.endGame();
        //Sprite img=new Sprite("/maxresdefault.jpg",0,0,600,600);
        lvl=1;
        System.out.println("OLO1");
       // view.setObjForRender(img);
       // canvas.repaint();
    }

    private void nextLevel() {
        stop();
        lvl+=1;
        view.nextLevel();
       // Sprite img=new Sprite("/nextlevel.jpg",0,0,600,600);
      //  view.setObjForRender(img);
       // canvas.addRender(img);
       // canvas.repaint();
        map = new GamePlace(lvl);
        System.out.println("LEVEL "+lvl);
        java.util.Timer timer2 = new java.util.Timer();
        TimerTask task = new TimerTask() {
            public void run()
            {
                start();
            }
        };
        timer2.schedule( task, 5000 );
    }

    private boolean canMove(int x, int y) {
        Cell cell =  Cell.CellByType( map.getCellType(x, y) );
        return (cell != null && cell.canStep);
    }

    private boolean canMove(Player player) {
        int left, right, up, down;
        boolean canStep = true;
        left = (int)((player.posX) / Cell.Size);
        right = (int) ((player.posX + player.getWidth() -1) / Cell.Size);
        up = (int)((player.posY + player.getSpeed() * player.dY) / Cell.Size);
        down = (int)((player.posY + player.getHeight()+5 + player.getSpeed() * player.dY -1) / Cell.Size);
        if (player.dY == -1 && !(canMove(left, up) && canMove(right, up))) {
            canStep = false;
        } else if (player.dY == 1 && !(canMove(left, down) && canMove(right, down))) {
            canStep = false;
        }
        left = (int)((player.posX + player.getSpeed() * player.dX) / Cell.Size);
        right = (int) ((player.posX + player.getWidth() +5+ player.getSpeed() * player.dX -1) / Cell.Size);
        up = (int) ((player.posY) / Cell.Size);
        down = (int) ((player.posY + player.getHeight()-1) / Cell.Size);
        if (player.dX == -1 && !(canMove(left, up) && canMove(left, down))) {
            canStep = false;
        } else if (player.dX == 1 && !(canMove(right, up) && canMove(right, down))) {
            canStep = false;
        }
        return canStep;
    }
}


