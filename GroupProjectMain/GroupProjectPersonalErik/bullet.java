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
    private int demage;
    private boolean looselife;
    private boolean status;


    /**ball constructor*/
    public bullet(double x, double y, double radius, Color color, int demage, double speed, double endX, double endY) {
        shape = new Ellipse(x, y, radius * 2, radius * 2);
        shape.setFillColor(color);
        this.endX=endX;
        this.endY=endY;
        this.demage=demage;
        this.looselife=false;
        double angleRadians = atan2(endY-y, endX-x);
        this.XVelosity = cos(angleRadians) * speed;
        this.YVelosity = sin(angleRadians) * speed;
//        this.XVelosity=speed*Math.sin(Math.atan((endY-y)/(endX-x)));
//        this.YVelosity=speed*Math.cos(Math.atan((endY-y)/(endX-x)));
        this.status=true;
    }
    /**this function manage the movement of the ball based on velosity*/
    public void move(){
        collidecheck();
        shape.moveBy(XVelosity,YVelosity);
    }
    /**this function bounce the ball back when it hit the wall, and it reduce the life of the player when hit the bottom*/
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
    public boolean getstatus(){
        return status;
    }
    public Ellipse getshape(){
        return shape;
    }
    /**this function get coordinate x*/
    public double getX(){
        return shape.getX();
    }
    /**this function get coordinate y*/
    public double getY(){
        return shape.getY();
    }
    /**this function change velosity x*/
    public void changeVX(){
        this.XVelosity=0-this.XVelosity;
    }
    /**this function change velosity y*/
    public void changeVY(){
        this.YVelosity=0-this.YVelosity;
    }
    /**this function rewrite velosity x*/
    public void writeVX(double i){
        this.XVelosity=i;
    }
    /**this function rewrite velosity y*/
    public void writeY(double i){
        this.YVelosity=i;
    }
}




