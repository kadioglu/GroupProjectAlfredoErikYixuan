package GroupProjectPersonalErik;

import comp127graphics.Rectangle;

import java.awt.*;

/**
 * The class for making the player's character
 */
public class player extends characters{
    private double XVelosity;
    private double YVelosity;
    private boolean status;
    private Rectangle shape;
    private double size;
    private int bulletlimit;
    private int bulletcount;

    /**
     * Constructs the player as a black rectangle of certain
     * width and height as some coordinates.
     * @param x The beginning x position of the player
     * @param y The beginning y position of the player
     * @param width The width of the player
     * @param height The height of the player
     */
    player(int x, int y, int width, int height) {
        shape=new Rectangle(x, y, width, height);
        shape.setFillColor(Color.black);
        shape.setStrokeColor(Color.black);
        size=width;
        bulletlimit=10;
        bulletcount=10;
        setHealth(3);
    }

    /**
     * Sets the player's velocity to zero if they try to leave
     * the canvas size so that they remain on the screen.
     */
    public void checkbounder(){
        if (this.getX() == 0) {
            this.writeVX(0);
            this.status=false;
        }
        if (this.getX() == 800-size) {
            this.writeVX(0);
            this.status=false;
        }
        if (this.getY() == 0) {
            this.writeVY(0);
            this.status=false;
        }
        if (this.getY() == 800-size) {
            this.writeVY(0);
            this.status=false;
        }
    }

    /**
     * Subtracts one from the player's current bulletcount
     */
    public void fire(){
        bulletcount=bulletcount-1;
    }

    /**
     * Resets bullet count to the max (to bulletlimit)
     */
    public void reload(){
        bulletcount=bulletlimit;
    }

    /**
     * Returns the player's current bulletcount
     * @return The player's bulletcount
     */
    public int getBulletcount(){
        return this.bulletcount;
    }

    /**
     * Increases the player's bullet limit by n
     * @param n The value to increase the bullet limit by
     */
    public void bulletlimitincrease(int n){
        this.bulletlimit=bulletlimit+n;
    }

    /**
     * Returns the player's graphics object shape
     * @return The player's shape
     */
    public Rectangle getShape(){
        return this.shape;
    }

    /**
     *
     */
    @Override
    protected void buildGraphics() {

    }

    /**
     * Returns the player's width and height
     * @return The player's size
     */
    public double getSize(){
        return this.size;
    }

    /**
     * Returns the player's center x coordinate
     * @return The player's x coordinate plus one half their width
     */
    public double getcenterX(){
        return shape.getX()+size/2;
    }

    /**
     * Returns the player's center y coordinate
     * @return The player's y coordinate plus one half their width
     */
    public double getcenterY(){
        return shape.getY()+size/2;
    }

    /**
     * Moves the bullet by the x velocity and the y velocity
     */
    public void move() {
        shape.moveBy(XVelosity, YVelosity);
    }

    /**
     * Returns the player's x coordinate
     * @return the player's x coordinate
     */
    public double getX(){
        return shape.getX();
    }

    /**
     * Returns the player's y coordinate
     * @return the player's y coordinate
     */
    public double getY(){
        return shape.getY();
    }

    /**
     * Inverts the player's x velocity
     */
    public void changeVX(){
        this.XVelosity=0-this.XVelosity;
    }

    /**
     * Inverts the player's y velocity
     */
    public void changeVY(){
        this.YVelosity=0-this.YVelosity;
    }

    /**
     * Sets the new x velocity to the parameter
     * @param i The new x velocity
     */
    public void writeVX(double i){
        this.XVelosity=i;
    }

    /**
     * Sets the new y velocity to the parameter
     * @param i The new y velocity
     */
    public void writeVY(double i){
        this.YVelosity=i;
    }
}

