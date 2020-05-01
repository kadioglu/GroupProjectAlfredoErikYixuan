package GroupProjectPersonalErik;

import comp127graphics.CanvasWindow;
import comp127graphics.events.Key;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The game of Breakout.
 *
 */
public class mainfunction {

    private static final int CANVAS_WIDTH = 800;
    private static final int CANVAS_HEIGHT = 800;

    private CanvasWindow canvas;
//    private List<block> blockList;
    private List<bullet> bulletList;
    private List<bullet> deletalist;
    private Random rand = new Random();
    private player player;
    private int roomnumber;
    private int nummounster1;
    private int Nummounster2;
    private counter counter1;

    private int firecountdown=0;

    private String otheraction;
    private Boolean otheractionstatus;

    private String actiononX;
    private String actiononY;
//    private Boolean Wdis;
//    private Boolean Adis;
//    private Boolean Sdis;
//    private Boolean Ddis;

    /**main function*/
    public static void main(String[] args) {
        new mainfunction();
    }
    /**breakoutgame constructor*/

//    public void initialize(){
//        canvas = new CanvasWindow("Breakout!", CANVAS_WIDTH, CANVAS_HEIGHT);
////        blockList = new ArrayList<>();
//        bulletList = new ArrayList<>();
////        ball1 = new ball(150, 250, 10, Color.black, 5.0, 5.0);
////        canvas.add(ball1.getshape());
//        removedBlock=null;
//        player= new player(0,400,20,20);
//        actiononX="null";
//        actiononY="null";
//        firecountdown=10;
//        counter1=new counter(15);
////        Wdis=false;
////        Adis= false;
////        Sdis=false;
////        Ddis=false;
//
////        CheckBlock();
////        clearblock();
//        canvas.add(player.getShape());
//        canvas.draw();
//    }
    public mainfunction(){
        canvas = new CanvasWindow("Breakout!", CANVAS_WIDTH, CANVAS_HEIGHT);
//        blockList = new ArrayList<>();
        bulletList = new ArrayList<>();
//        ball1 = new ball(150, 250, 10, Color.black, 5.0, 5.0);
//        canvas.add(ball1.getshape());
        player= new player(0,400,20,20);
        actiononX="null";
        actiononY="null";
        firecountdown=0;
        counter1=new counter(50);
//        Wdis=false;
//        Adis= false;
//        Sdis=false;
//        Ddis=false;

//        CheckBlock();
//        clearblock();
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
            if(bulletList!=null){
            for(bullet i: bulletList){
                i.move();
                i.collidecheck();

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
//    private void bounderycontrol(){
//        if(player.getY() >= 0){
//            Wdis=true;
//        }
//        if(player.getY() >= 0){
//
//        }
//    }

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

//    private void updateCanvas() {
//        if (!bu.getBulletList().isEmpty()) {
////            while (animating) {
//            for (newBull bullet : bulletHandler.getBulletList()) {
////                    bullet.removeFromCanvas();
//                bullet.move();
//                bullet.addToCanvas();
//                canvas.pause(30);
//            }
////            }
//        }
//    }

//    /**this function create block*/
//    private void populateblock() {
//        for (int i = 0; i < 100; i++) {
//            int y = 20 + 23 * (i / 10);
//            int x = 12 + 58 * (i % 10);
//            block newblock = new block(x, y, 55, 20);
//            newblock.setcolor(Color.getHSBColor(rand.nextFloat(), rand.nextFloat() * 0.5f + 0.1f, 1));
//            blockList.add(newblock);
//            canvas.add(newblock.getshape());
//        }
//    }
    /**this function run though all the block in the blocklist and check whether it is hit by the ball*/
//    private void CheckBlock() {
//        for (block i : blockList) {
//            brickChecker(ball1.getX(),ball1.getY(),i );
//        }
//        clearblock();
//    }
    /**this function clear the block that was marked as block that should be moved*/
//    private void clearblock(){
//        for (block i : blockList){
//            if (!i.getstatus()){
//                removedBlock=i;
//            }
//        }
//        if(removedBlock!=null){
//            blockList.remove(removedBlock);
//            canvas.remove(removedBlock.getshape());
//            removedBlock=null;}
//    }
    /**this function checks the collision between block, moveblock and ball it change the velosity of the block
     * and mark the block that was hit as removedblovk*/
//    private void brickChecker(double ballx, double bally,block theblock){
//        GraphicsObject b3 = new Ellipse((ballx+10), bally,0.1,1.5);
//        if (b3.getBounds().intersects(theblock.getshape().getBounds())) {
//            theblock.setStatus();
//            ball1.writeVX(-5);
//        }
//
//        GraphicsObject b6 = new Ellipse(ballx, (bally+10),3,0.1);
//        if (b6.getBounds().intersects(theblock.getshape().getBounds())) {
//            theblock.setStatus();
//            ball1.writeY(-5);
//        }
//        else if (b6.getBounds().intersects(moveBlock1.getBounds())) {
//            ball1.writeY(-5);
//        }
//
//        GraphicsObject b9 = new Ellipse((ballx-10), bally,0.1,1.5);
//        if (b9.getBounds().intersects(theblock.getshape().getBounds())) {
//            theblock.setStatus();
//            ball1.writeVX(5);
//        }
//
//        GraphicsObject b12 = new Ellipse(ballx, (bally-10),3,0.1);
//        if (b12.getBounds().intersects(theblock.getshape().getBounds())) {
//            theblock.setStatus();
//            ball1.writeY(5);
//        }
//    }
    private void changelocation(){
        canvas.removeAll();

    }
    private void clearscreen() {
        roomnumber++;
        canvas.removeAll();
        if (roomnumber%5!=0){
            nummounster1=roomnumber+(4-rand.nextInt(5));
        }
    }


}

