package GroupProjectPersonalErik;

import comp127graphics.Ellipse;

import java.awt.*;

import static java.lang.Math.sin;
import static java.lang.StrictMath.atan2;
import static java.lang.StrictMath.cos;

/**
 * Creates a new bullet with an intended direction, speed, and position
 */
@SuppressWarnings("SameParameterValue")
public class bullet {

    private Ellipse shape;
    private double XVelosity;
    private double YVelosity;
    private int damage;
    private boolean status;


    /**
     * Creates a new bullet with an intended direction, speed, and position and sets up a number of parameters
     * @param x The x position of the center of the player
     * @param y The y position of the center of the player
     * @param radius The radius of the bullet
     * @param color The color of the bullet
     * @param damage The damage of the bullet
     * @param speed The speed modifier of the bullet
     * @param endX The goal x position of the bullet (mouse position x)
     * @param endY the goal y position of the bullet (mouse position y)
     */
    public bullet(double x, double y, double radius, Color color, int damage, double speed, double endX, double endY) {
        shape = new Ellipse(x, y, radius * 2, radius * 2);
        shape.setFillColor(color);
        this.damage=1;
        double angleRadians = atan2(endY-y, endX-x);
        this.XVelosity = cos(angleRadians) * speed;
        this.YVelosity = sin(angleRadians) * speed;
        this.status=true;
    }

    /**
     * Manages the movement of the ball based on x & y velocities
     */
    public void move(){
        collidecheck();
        shape.moveBy(XVelosity,YVelosity);
    }

    /**
     * Sets the bullet's status to false (deletes it) when it moves a little
     * beyond all the walls of the canvas
     */
    public void collidecheck() {
        if (this.getX() == -100) {
            this.status=false;
        }
        if (this.getX() == 900) {
            this.status=false;
        }
        if (this.getY() == -100) {
            this.status=false;
        }
        if (this.getY() == 900) {
            this.status=false;
        }
    }

    /**
     * Gets the boolean status of the bullet's coordinates (whether or not it is still on the canvas)
     */
    public boolean getstatus(){
        return status;
    }

    /**
     * Gets the bullet shape
     */
    public Ellipse getshape(){
        return shape;
    }

    /**
     * Gets the x coordinate of the bullet
     */
    public double getX(){
        return shape.getX();
    }

    /**
     * Gets the y coordinate of the bullet
     */
    public double getY(){
        return shape.getY();
    }

    /**
     * Sets the boolean status of the bullet (whether or not it is still on the canvas)
     * @param status Boolean for whether it is active or not
     */
    public void setStatus(boolean status) {
        this.status = status;
    }
}




