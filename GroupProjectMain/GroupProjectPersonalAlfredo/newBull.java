package GroupProjectPersonalAlfredo;

import comp127graphics.CanvasWindow;
import comp127graphics.Ellipse;
import comp127graphics.GraphicsObject;
import comp127graphics.events.MouseButtonEvent;

import static java.lang.Math.sin;
import static java.lang.StrictMath.atan2;
import static java.lang.StrictMath.cos;

/**
 * Creates a new bullet with an intended direction, speed, and position
 */
public class newBull {

    public static double radius = 2.5;
    public Ellipse bullShape;
    public CanvasWindow canvas;
    public Firer firer;
    private double posX;
    private double posY;
    public double mousePosX;
    public double mousePosY;
    private double xVel;
    private double yVel;
    private double speed = 10;
    private newBullHandle bulletHandler;
    public double angleRadians;


    /**
     * Constructs a new bullet and sets up the parameters for it.
     * @param canvas The canvas on which to display
     * @param event The click used to shoot
     * @param firer The shooter
     * @param bulletHandler The handler (list) for bullets
     */
    public newBull(CanvasWindow canvas, MouseButtonEvent event, Firer firer, newBullHandle bulletHandler) {
        this.canvas = canvas;
        this.firer = firer;
        posX = firer.getCenter().getX();
        posY = firer.getCenter().getY();
        mousePosX = event.getPosition().getX();
        mousePosY = event.getPosition().getY();
        this.bulletHandler = bulletHandler;


        bullShape = new Ellipse(0, 0, 0, 0);
        bullShape.setCenter(firer.getCenter());
        bullShape.setWidthAndHeight(radius*2, radius*2);
//        canvas.add(bullShape); //This causes the ball to be revealed offcenter of the firer; remove and ball appears in direction of; high speed could make this look real bad
        bulletHandler.getBulletList().add(this);
//        findVelocities(posX, posY, speed);
        findVelocities(speed);

    }

    /**
     *
     */
    public void move() { //Add the remove function as an if, not an else
        if(posX > 0 && posX < canvas.getWidth() &&
            posY > 0 && posY < canvas.getHeight()) {
                posX += xVel;
                posY += yVel;
                bullShape.setCenter(posX, posY);

        }
//        else {
//            this.removeFromCanvas();
//            bulletHandler.removeFromList(this);
//        }


    }


    /**
     * Adds the bullet's shape to the canvas.
     */
    public void addToCanvas() {
        canvas.add(bullShape);
    }

    /**
     * Removes the bullet's shape from the canvas.
     */
    public void removeFromCanvas() {
        canvas.remove(bullShape);
    }

    /**
     * Returns the x position of the center of the bullet.
     */
    public double getPosX() {
        return posX;
    }

    /**
     * Returns the y position of the center of the bullet.
     */
    public double getPosY() {
        return posY;
    }

    /**
     * Finds the separate velocities of the bullet's axes based off of mouse position.
     * @param speed the bullet's speed variable
     */
    private void findVelocities(double speed) {
        double angleRadians = atan2(mousePosY-posY, mousePosX-posX);
        this.xVel = cos(angleRadians) * speed;
        this.yVel = sin(angleRadians) * speed;
    }


//    /**
//     * Finds the separate velocities of the bullet's axes based off of mouse position.
//     * @param speed the bullet's speed variable
//     */
//    private void findVelocities(double speed, Firer firer) {
//        if(firer.equals(firer)) { //Player
//            angleRadians = atan2(mousePosY - posY, mousePosX - posX);
//        } else {
////            double angleRadians = atan2(playerPosY - posY, playerPosX - posX)
//        }
//        this.xVel = cos(angleRadians) * speed;
//        this.yVel = sin(angleRadians) * speed;
//    }


    /**
     * Returns any object the bullet intersects with, and null otherwise; the firer also returns null
     * @return GraphicsObject intersecting with the bullet
     */
    private GraphicsObject getIntersect() { //this may not work for enemies (also change graphics obj?)
        if(canvas.getElementAt(posX, posY).equals(firer)) {
            return canvas.getElementAt(posX, posY);
        }
        return null;
    }

}
