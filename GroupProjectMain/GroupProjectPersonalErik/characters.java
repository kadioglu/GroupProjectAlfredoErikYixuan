package GroupProjectPersonalErik;

import comp127graphics.GraphicsGroup;
import comp127graphics.GraphicsObject;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public abstract class characters {

    private GraphicsGroup graphics;
    private double speed;
    private Point.Double goal;
    private int health;
    private int damage;
    private boolean tookDamage;
    private int xOffset;
    private int yOffset;

    public characters() {
        graphics = new GraphicsGroup(0, 0);
        this.health = 3;
        buildGraphics();
    }

    public GraphicsGroup getGraphics() {
        return graphics;
    }

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

    public void takeDamage(int d){
        if(!this.tookDamage) {
            this.health = this.health - d;
            tookDamage();

        }

    }

    protected abstract void buildGraphics();

    public double getSize() {
        return Math.hypot(getGraphics().getWidth(), getGraphics().getHeight());
    }

    public double getSpeed() {
        return this.speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public Point.Double getGoal() {
        return goal;
    }

    public void setGoal(Point.Double goal) {
        this.goal = goal;
//        System.out.println(goal.getX());
    }


    public void moveTowardsGoal() {
        if(!graphics.getBounds().intersects(goal.getX(),goal.getY(),1,1)){
        double dx = this.goal.getX() - getGraphics().getX();
        double dy = this.goal.getY() - getGraphics().getY();
        double dist = Math.hypot(dx, dy);
        moveBy(
                dx * getSpeed() / dist,
                dy * getSpeed() / dist);}
    }

    public void moveBy(double dx, double dy) {
        graphics.moveBy(dx, dy);

    }

    public comp127graphics.Point getPosition(){
        return graphics.getPosition();
    }


    public boolean intersects(GraphicsObject GO) {
        return this.graphics.getBounds().intersects(GO.getBounds());
    }

    public int getxOffset(){
        return this.xOffset;
    }
    public void setxOffset(int i){
        this.xOffset = i;
    }

    public int getyOffset() {
        return this.yOffset;
    }
    public void setyOffset(int i){
        this.yOffset = i;
    }

    public void setHealth(int i){
        this.health = i;
    }

    public int getHealth() {
        return health;
    }
}

