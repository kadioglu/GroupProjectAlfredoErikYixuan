package GroupProjectPersonalErik;

import comp127graphics.Rectangle;

import java.awt.*;

public class player extends characters{
    private double XVelosity;
    private double YVelosity;
    private boolean status;
    private Rectangle shape;
    private double size;
    private int bulletlimit;
    private int bulletcount;

    /**
this is the constructor of the paddle which is the move block
     */
    player(int x, int y, int width, int hight) {
        shape=new Rectangle(x, y, width, hight);
        shape.setFillColor(Color.black);
        shape.setStrokeColor(Color.black);
        size=width;
        bulletlimit=10;
        bulletcount=10;
        setHealth(3);


    }

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
    public void fire(){
        bulletcount=bulletcount-1;
    }

    public void reload(){
        bulletcount=bulletlimit;
    }

    public int getBulletcount(){
        return this.bulletcount;
    }

    public void bulletlimitincrease(int n){
        this.bulletlimit=bulletlimit+n;
    }

    public Rectangle getShape(){
        return this.shape;
    }


    @Override
    protected void buildGraphics() {

    }

    public double getSize(){
        return this.size;
    }

    public double getcenterX(){
        return shape.getX()+size/2;
    }

    public double getcenterY(){
        return shape.getY()+size/2;
    }

    public void move() {
        shape.moveBy(XVelosity, YVelosity);
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
    public void writeVY(double i){
        this.YVelosity=i;
    }
}

