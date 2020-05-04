package GroupProjectPersonalErik;

import comp127graphics.CanvasWindow;
import comp127graphics.events.Key;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * This is the Game Main function
 *
 */
public class mainfunction {

    private static final int CANVAS_WIDTH = 800;
    private static final int CANVAS_HEIGHT = 800;

    private final CanvasWindow canvas;
//    private List<block> blockList;
    private List<characters> enemyList;
    private List<bullet> bulletList;
    private List<bullet> deletalist;
    private final Random rand = new Random();
    private final player player;
    private int roomnumber;
    private int nummounster1;
    private int Nummounster2;
    private counter counter1;

    private int firecountdown=0;

    private String otheraction;

    private String actiononX;
    private String actiononY;


    private characters spiky;

    /**main function*/
    public static void main(String[] args) {
        new mainfunction();
    }

    public mainfunction(){
        roomnumber=1;
        nummounster1 = rand.nextInt(2)+roomnumber/2;
        canvas = new CanvasWindow("Breakout!", CANVAS_WIDTH, CANVAS_HEIGHT);
        bulletList = new ArrayList<>();
        enemyList = new ArrayList<>();
        player= new player(0,400,20,20);
        actiononX="null";
        actiononY="null";
        firecountdown=0;
        counter1=new counter(50);
        spawnMonsters();
        canvas.add(player.getShape());
        canvas.draw();
        canvas.onMouseDown(event -> {
            if(player.getBulletcount()>0){
            createbullet(event.getPosition().getX(), event.getPosition().getY());}
            player.fire();
            System.out.println(player.getBulletcount());
            //            counter1.refreshtime();
        });
        canvas.onKeyDown(event -> {
            ControlManager(event.getKey());
        });
        canvas.onKeyUp(event -> {
            ControlManager2(event.getKey());
        });
        canvas.animate(() -> {
            counter1.countdown();
            centralControler();
            player.move();
            player.checkbounder();
            for(characters s:enemyList){
            s.setGoal(new Point2D.Double(
                 player.getcenterX(),
                 player.getcenterY()
            ));
            s.moveTowardsGoal();
            if (s.intersects(player.getShape())){
                player.takeDamage(1);
                if(player.getHealth()<1){
                    System.exit(0);
                }
            }
            }
            if(enemyList.isEmpty()){
                newRoom();
            }
            if(bulletList!=null){
            for(bullet i: bulletList){
                i.move();
                i.collidecheck();
                for (characters s:enemyList){
                    if(s.intersects(i.getshape())){
                        s.takeDamage(1);

                        bulletList = bulletList.stream()
                                .filter(a->!a.equals(i))
                                .collect(Collectors.toList());

                        if(s.getHealth()<1){
                            canvas.remove(s.getGraphics());
                            enemyList = enemyList.stream()
                                    .filter(b -> !b.equals(s))
                                    .collect(Collectors.toList());
                        }
                    }
                }

            if(!i.getstatus()){
                deletalist.add(i);
            }
                }
            if(deletalist!=null){
            for (bullet b: deletalist) {
                bulletList.remove(b);
                deletalist = null;
            }
            }
            }
        }
        );
        };
//    }
    private void ControlManager(Key key){
        if(key== Key.W){
            CactiononY("w");
//            player.writeVY(-7);
//            System.out.print("w");
        }
        if(key== Key.A){
            CactiononX("a");
//            player.writeVX(-7);
//            System.out.print("a");
        }
        if(key== Key.S){
            CactiononY("s");
//            player.writeVY(7);
//            System.out.print("s");
        }
        if(key== Key.D){
            CactiononX("d");
//            player.writeVX(7);
//            System.out.print("d");
        }
        if(key==Key.R){
            otheraction="r";
            }
    }

    private void ControlManager2(Key key){
        if(key== Key.W){
            if(actiononY.equals("w")){
                actiononY="null";
                System.out.println("   W   ");
            }
//            player.writeVY(0);
//            System.out.print("Wo");
        }
        if(key== Key.A){
            if(actiononX.equals("a")){
                actiononX="null";
            }
//            player.writeVX(0);
//            System.out.print("Ao");
        }
        if(key== Key.S){
            if(actiononY.equals("s")){
                actiononY="null";
            }
//            player.writeVY(0);
//            System.out.print("So");
        }
        if(key== Key.D){
            if(actiononX .equals("d")){
                actiononX="null";
            }
//            player.writeVX(0);
//            System.out.print("Do");
        }
        if(key==Key.R){
            if(otheraction.equals("r")){
                player.reload();
                otheraction="null";
            }
        }

    }

    private void centralControler(){
        if(actiononY.equals("null")){
            player.writeVY(0);
        }
        if(actiononX.equals("null")){
            player.writeVX(0);
        }
        if(actiononY.equals("w")){
            if(player.getY() >= 0){
            player.writeVY(-7);
            System.out.print("w");
        }
            else player.writeVY(0);
        }
        if(actiononX.equals("a")){
            if(player.getX() >= 0){
                player.writeVX(-7);
                System.out.print("a");
            }
            else player.writeVX(0);
        }
        if(actiononY.equals("s")){
            if(player.getY() <= 800-player.getSize()){
                player.writeVY(7);
                System.out.print("s");
            }
            else player.writeVY(0);
        }
        if(actiononX.equals("d")){
            if(player.getX() <= 800-player.getSize()){
                player.writeVX(7);
                System.out.print("d");
            }
            else player.writeVX(0);
        }
    }

    private void CactiononX(String i){
        this.actiononX=i;
    }
    private void CactiononY(String i){
        this.actiononY=i;
    }

    private void createbullet(double X, double Y){
        bullet bullet1=new bullet(player.getX(), player.getY(), 5, Color.BLUE, 2, 20, X,Y);
        bulletList.add(bullet1);
        canvas.add(bullet1.getshape());
    }

    private void refreshcountdown(int i){
        this.firecountdown=i;
    }

    private void changelocation(){
        canvas.removeAll();

    }
    private void newRoom() {
        roomnumber++;
        spawnMonsters();
        if (roomnumber%5!=0){
            nummounster1=roomnumber+(4-rand.nextInt(5));
        }
    }
    private  void spawnMonsters(){
        for(int i = 0; i<=nummounster1;i++){
            spiky = new Spiky();
            canvas.add(spiky.getGraphics());
            spiky.moveBy(spiky.getxOffset(),spiky.getyOffset());
            enemyList.add(spiky);
        }
    }

}

