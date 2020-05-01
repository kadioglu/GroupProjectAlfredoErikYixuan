package GroupProjectPersonalErik;

import comp127graphics.GraphicsGroup;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public abstract class characters {

    private final GraphicsGroup graphics;
    private double speed;
    private Point.Double goal;
    private int health;
    private int damage;
    private boolean tookDamage;

    public characters() {
        graphics = new GraphicsGroup(0, 0);
        buildGraphics();
    }

    public GraphicsGroup getGraphics() {
        return graphics;
    }

    public int getHealth(){
        return health;
    }

    public void setHealth(int i){
        this.health = i;
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
         long delay = 3;
         timer.schedule(new NextTask(),delay);


    }

    public void takeDamage(characters charact){
        if(this.tookDamage = false) {
            this.setHealth(this.getHealth() - damage);
            this.tookDamage();
            if (this.health <= 0) {
                charact = null;
            }

        }

    }

    protected abstract void buildGraphics();

    public double getSize() {
        return Math.hypot(getGraphics().getWidth(), getGraphics().getHeight());
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public Point.Double getGoal() {
        return goal;
    }

    public void setGoal(Point.Double goal) {
        this.goal = goal;
    }


    public void moveTowardsGoal(double dt) {
        double dx = goal.getX() - getGraphics().getX(),
                dy = goal.getY() - getGraphics().getY(),
                dist = Math.hypot(dx, dy);
        moveBy(
                dx * getSpeed() / dist,
                dy * getSpeed() / dist,
                dt);
    }

    public void moveBy(double dx, double dy, double dt) {
        graphics.moveBy(dx*dt, dy*dt);

    }

    public comp127graphics.Point getPosition(){
        return graphics.getPosition();
    }


}

