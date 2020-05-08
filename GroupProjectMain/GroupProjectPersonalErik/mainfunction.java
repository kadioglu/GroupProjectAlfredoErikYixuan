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
 * The main game class to run the game and shoot rapidly approaching enemies.
 */
public class mainfunction {

    private static final int CANVAS_WIDTH = 800;
    private static final int CANVAS_HEIGHT = 800;

    private final CanvasWindow canvas;
    private List<characters> enemyList;
    private List<bullet> bulletList;
    private List<bullet> deletalist;
    private final Random rand = new Random();
    private final player player;
    private int roomnumber;
    private int nummounster1;
    private int Nummounster2;

    private String otheraction;

    private String actiononX;
    private String actiononY;

    private characters spiky;

    /**
     * The main function which runs the game
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
        spawnMonsters();
        canvas.add(player.getShape());
        canvas.draw();
        canvas.onMouseDown(event -> {
            if(player.getBulletcount()>0){
            createbullet(event.getPosition().getX(), event.getPosition().getY());}
            player.fire();
        });
        canvas.onKeyDown(event -> {
            ControlManager(event.getKey());
        });
        canvas.onKeyUp(event -> {
            ControlManager2(event.getKey());
        });
        canvas.animate(() -> {
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
        }
        if(key== Key.A){
            CactiononX("a");
        }
        if(key== Key.S){
            CactiononY("s");
        }
        if(key== Key.D){
            CactiononX("d");
        }
        if(key==Key.R){
            otheraction="r";
            }
    }

    /**
     * Interprets another keypress the player is pushing so that multiple actions
     * can occur simultaneously, like diagonal travel or moving while reloading
     * @param key Takes another key the player may be pressing
     */
    private void ControlManager2(Key key){
        if(key== Key.W){
            if(actiononY.equals("w")){
                actiononY="null";
            }
        }
        if(key== Key.A){
            if(actiononX.equals("a")){
                actiononX="null";
            }
        }
        if(key== Key.S){
            if(actiononY.equals("s")){
                actiononY="null";
            }
        }
        if(key== Key.D){
            if(actiononX.equals("d")){
                actiononX="null";
            }
        }
        if(key==Key.R){
            if(otheraction.equals("r")){
                player.reload();
                otheraction="null";
            }
        }
    }

    /**
     * Interprets all the key presses and executes them;
     * this is responsible for actually moving the player character.
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
        }
            else player.writeVY(0);
        }
        if(actiononX.equals("a")){
            if(player.getX() >= 0){
                player.writeVX(-7);
            }
            else player.writeVX(0);
        }
        if(actiononY.equals("s")){
            if(player.getY() <= 800-player.getSize()){
                player.writeVY(7);
            }
            else player.writeVY(0);
        }
        if(actiononX.equals("d")){
            if(player.getX() <= 800-player.getSize()){
                player.writeVX(7);
            }
            else player.writeVX(0);
        }
    }

    /**
     * Interprets which buttons are pressed and set the variable actionX to it.
     * @param i The string for the key being pressed
     */
    private void CactiononX(String i){
        this.actiononX=i;
    }

    /**
     * Interprets which buttons are pressed and set the variable actionY to it.
     * @param i The string for the key being pressed
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
        bullet bullet1=new bullet(player.getcenterX(), player.getcenterY(), 5, Color.BLUE, 2, 20, X,Y);
        bulletList.add(bullet1);
        canvas.add(bullet1.getshape());
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
     * Generates the new enemies for a room, and randomly distributes them in it
     */
    private  void spawnMonsters(){
        for(int i = 0; i<=nummounster1;i++){
            spiky = new Spiky();
            canvas.add(spiky.getGraphics());
            spiky.moveBy(spiky.getxOffset(),spiky.getyOffset());
            enemyList.add(spiky);
        }
    }

    /**
     * Moves the enemies toward the player's coordinates;
     * if they intersect, hurt the player;
     * if the player's health goes below 1, end the game
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
     * Moves each bullet and checks if it hits the boundaries of the screen;
     * then, for each enemy, sees if it intersects. If it does, remove the bullet
     * and reduce the enemy's health by 1——if an enemy's health is below 1,
     * remove it from the canvas and the enemy list.
     */
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
                break;
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
}
