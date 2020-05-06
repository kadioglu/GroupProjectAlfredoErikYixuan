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

    /**
     * Main function which runs the game
     */
    public static void main(String[] args) {
        new mainfunction();
    }

    /**
     * The canvas & screen for the game, animating a list of enemies chasing after the player
     * and the bullets the player fires, generates rooms with a semi-random number of enemies
     * at random positions, and uses the player's inputs to allow the player to move.
     */
    public mainfunction(){
        roomnumber=1;
        nummounster1 = rand.nextInt(2)+roomnumber/2;
        canvas = new CanvasWindow("Goose Project!", CANVAS_WIDTH, CANVAS_HEIGHT);
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
//            System.out.println(player.getBulletcount());
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

            enemyMoves();
            checkNewRoom();
            if(bulletList!=null) {
                checkBullet();
            }
        });
    }

    /**
     * Accepts the button the player is pushing and uses that to increase
     * or decrease the x/v velocities based on the axis and direction.
     * @param key Takes the key the player is pressing
     */
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


    // I think that's what this does; I am unsure
    /**
     *
     * @param key Takes another key the player may be pressing
     */
    private void ControlManager2(Key key){
        if(key== Key.W){
            if(actiononY.equals("w")){
                actiononY="null";
//                System.out.println("   W   ");
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
            if(actiononX.equals("d")){
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

    /**
     *
     */
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
//            System.out.print("w");
        }
            else player.writeVY(0);
        }
        if(actiononX.equals("a")){
            if(player.getX() >= 0){
                player.writeVX(-7);
//                System.out.print("a");
            }
            else player.writeVX(0);
        }
        if(actiononY.equals("s")){
            if(player.getY() <= 800-player.getSize()){
                player.writeVY(7);
//                System.out.print("s");
            }
            else player.writeVY(0);
        }
        if(actiononX.equals("d")){
            if(player.getX() <= 800-player.getSize()){
                player.writeVX(7);
//                System.out.print("d");
            }
            else player.writeVX(0);
        }
    }

    /**
     * TODO: ?
     * @param i
     */
    private void CactiononX(String i){
        this.actiononX=i;
    }

    /**
     * TODO: ?
     * @param i
     */
    private void CactiononY(String i){
        this.actiononY=i;
    }

    /**
     * Creates a new bullet based on where the player and the mouse cursor's coordinates
     * @param X The mouse cursor's X coordinate
     * @param Y The mouse cursor's Y coordinate
     */
    private void createbullet(double X, double Y){
        bullet bullet1=new bullet(player.getX(), player.getY(), 5, Color.BLUE, 2, 20, X,Y);
        bulletList.add(bullet1);
        canvas.add(bullet1.getshape());
    }


    private void refreshcountdown(int i){
        this.firecountdown=i;
    }

    /**
     * Removes all objects from the canvas (to switch rooms)
     */
    private void changelocation(){
        canvas.removeAll();
    }

    /**
     * Generates a new room and calls spawnMonsters() to generate new enemies
     */
    private void newRoom() {
        roomnumber++;
        spawnMonsters();
        if (roomnumber%5!=0){
            nummounster1=roomnumber+(4-rand.nextInt(5));
        }
    }

    /**
     * Generates the new enemies for a room,
     */
    private  void spawnMonsters(){
        for(int i = 0; i<=nummounster1;i++){
            spiky = new Spiky();
            canvas.add(spiky.getGraphics());
            spiky.moveBy(spiky.getxOffset(),spiky.getyOffset());  // Make sure this occurs at some distance from the player
            enemyList.add(spiky);
        }
    }

    /**
     * Moves the enemies toward the player's coordinates;
     * if they intersect, hurt the player;
     * if the player's health goes below 1,end the game
     */
    private void enemyMoves() {
        for(characters s:enemyList){
            s.setGoal(new Point2D.Double(
                 player.getcenterX(),
                 player.getcenterY()
            ));
            s.moveTowardsGoal();
            if (s.intersects(player.getShape())){
                player.takeDamage(1);
                if(player.getHealth()<1){
                    System.exit(0);  // Add a game over screen with a restart button?
                }
            }
        }
    }

    /**
     * Checks that there are no more enemies, then calls newRoom()
     */
    private void checkNewRoom() {
        if(enemyList.isEmpty()){
            newRoom();
        }
    }

    /**
     * TODO: ?
     */
//          ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
//          ┃ Error is here: trying to delete the same bullet multiple times ┃
//          ┡━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┹────────────────┐
//          │ Either: make enemies unable to stack                                            │
//          │ Or: make sure when they intersect only the first spiky removes a bullet         │
//          │ Or: make the bullet call the remove function; thus it deletes itself and target │
//          └─────────────────────────────────────────────────────────────────────────────────┘
    private void checkBullet() {
        for(bullet i: bulletList) {
            i.move();
            i.collidecheck();
            for (characters s:enemyList) {
                if(s.intersects(i.getshape())) {
                    s.takeDamage(1);
                    bulletList = bulletList.stream()
                            .filter(a->!a.equals(i))
                            .collect(Collectors.toList());
                    canvas.remove(i.getshape());

                    if(s.getHealth()<1) {
                        canvas.remove(s.getGraphics());
                        enemyList = enemyList.stream()
                                .filter(b -> !b.equals(s))
                                .collect(Collectors.toList());
                    }
                }
            }

            if(!i.getstatus()) {
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
    // Might need to refactor this more


}
