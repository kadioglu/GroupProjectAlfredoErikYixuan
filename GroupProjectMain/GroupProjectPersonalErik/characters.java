package GroupProjectPersonalErik;

import comp127graphics.GraphicsGroup;
import comp127graphics.GraphicsObject;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * An abstract class to represent characters (the player and enemies)
 */
public abstract class characters {

    private GraphicsGroup graphics;
    private double speed;
    private Point.Double goal;
    private int health;
    private int damage;
    private boolean tookDamage;
    private int xOffset;
    private int yOffset;

    /**
     * Constructs a character to generate graphics and have a health of three
     */
    public characters() {
        graphics = new GraphicsGroup(0, 0);
        this.health = 3;
        buildGraphics();
    }

    /**
     * Returns the character's graphics
     * @return The graphical representation of the character
     */
    public GraphicsGroup getGraphics() {
        return graphics;
    }

    /**
     * After a character takes damage, prevents character from
     * being harmed again for a short period of time
     */
    public void tookDamage(){
        Timer timer = new Timer();
        class NextTask extends TimerTask {
            @Override
            public void run() {
                tookDamage = false;
                timer.cancel(); // Terminate the thread
            }
        }
        this.tookDamage = true;
         long delay = 300;
         timer.schedule(new NextTask(),delay);
    }

    /**
     * Makes the character reduce health by the damage
     * @param d The damage of the attack being suffered
     */
    public void takeDamage(int d){
        if(!this.tookDamage) {
            this.health = this.health - d;
            tookDamage();

        }
    }

    /**
     * Calls the buildGraphics function in the subclasses
     */
    protected abstract void buildGraphics();

    /**
     * Gets the size of the character
     * @return The hypotenuse of the character's height and width
     */
    public double getSize() {
        return Math.hypot(getGraphics().getWidth(), getGraphics().getHeight());
    }

    /**
     * Gets the speed of the character
     * @return Speed of the character
     */
    public double getSpeed() {
        return this.speed;
    }

    /**
     * Sets the speed of the character
     * @param speed The character's new speed
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    /**
     * Gets the character's goal location
     * @return The position the character is making their way towards
     */
    public Point.Double getGoal() {
        return goal;
    }

    /**
     * Set's the character's goal coordinates
     * @param goal The character's new position to make their way towards
     */
    public void setGoal(Point.Double goal) {
        this.goal = goal;
    }


    /**
     * Moves the character towards their goal based on their current coordinates
     * and their goal's coordinates, making a straight line
     */
    public void moveTowardsGoal() {
        if(!graphics.getBounds().intersects(goal.getX(),goal.getY(),1,1)){
        double dx = this.goal.getX() - getGraphics().getX();
        double dy = this.goal.getY() - getGraphics().getY();
        double dist = Math.hypot(dx, dy);
        moveBy(
                dx * getSpeed() / dist,
                dy * getSpeed() / dist);}
    }

    /**
     * Moves the character by an x distance and a y distance
     * @param dx The x distance to be covered
     * @param dy The y distance to be covered
     */
    public void moveBy(double dx, double dy) {
        graphics.moveBy(dx, dy);
    }

    /**
     * Gets the coordinates of a character
     * @return The coordinates of a character
     */
    public comp127graphics.Point getPosition(){
        return graphics.getPosition();
    }

    /**
     * Checks if a character intersects another object
     * @param GO The other object
     * @return The __)(
     */
    public boolean intersects(GraphicsObject GO) {
        return this.graphics.getBounds().intersects(GO.getBounds());
    }

    /**
     * Gets the spawning x position of a character
     * @return The x value offset
     */
    public int getxOffset(){
        return this.xOffset;
    }

    /**
     * Sets the spawning x position of a character
     * @param i The new x position for the character
     */
    public void setxOffset(int i){
        this.xOffset = i;
    }

    /**
     * Gets the spawning y position of a character
     * @return The y value offset
     */
    public int getyOffset() {
        return this.yOffset;
    }

    /**
     * Sets the spawning y position of a character
     * @param i The new y position for the character
     */
    public void setyOffset(int i){
        this.yOffset = i;
    }

    /**
     * Sets the health of the character
     * @param i The new health of the character
     */
    public void setHealth(int i){
        this.health = i;
    }

    /**
     * Gets the character's health
     * @return The int value of the character's health
     */
    public int getHealth() {
        return health;
    }
}

