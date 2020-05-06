package GroupProjectPersonalErik;

import comp127graphics.Ellipse;

import java.awt.*;

import static java.lang.Math.sin;
import static java.lang.StrictMath.atan2;
import static java.lang.StrictMath.cos;

@SuppressWarnings("SameParameterValue")
public class bullet {

    private Ellipse shape;
    private double XVelosity;
    private double YVelosity;
    private double endX;
    private double endY;
    private int damage;
    private boolean looselife;
    private boolean status;


    /**
     * Creates a new bullet with an intended direction, speed, and position
     */
    public bullet(double x, double y, double radius, Color color, int damage, double speed, double endX, double endY) {
        shape = new Ellipse(x, y, radius * 2, radius * 2);
        shape.setFillColor(color);
        this.endX=endX;
        this.endY=endY;
        this.damage=1;
        this.looselife=false;
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
    // Should convert the hard values to variables 100 units beyond the canvas height & width
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
//    /**
//     * Multiplies the x velocity by -1
//     */
//    public void changeVX(){
//        this.XVelosity=0-this.XVelosity;
//    }
//    /**
//     * Multiplies the y velocity by -1
//     */
//    public void changeVY(){
//        this.YVelosity=0-this.YVelosity;
//    }
//    /**this function rewrite velosity x*/
//    public void writeVX(double i){
//        this.XVelosity=i;
//    }
//    /**this function rewrite velosity y*/
//    public void writeY(double i){
//        this.YVelosity=i;
//    }
    /**
     * Sets the boolean status of the bullet (whether or not it is still on the canvas)
     */
    public void setStatus(boolean status) {
        this.status = status;
    }
}




