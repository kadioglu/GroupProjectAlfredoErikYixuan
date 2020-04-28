package GroupProjectPersonalErik.Canibalize;

import comp127graphics.CanvasWindow;
import comp127graphics.GraphicsGroup;
import comp127graphics.Point;
import comp127graphics.Rectangle;

import java.awt.*;
import java.awt.geom.Point2D;


/**
 * Created by shoop on 2/22/16.
 */
public class CritterTesterCanibalize {

    private CanvasWindow canvas;
    private CritterCanibalize testCritterCanibalize;

    public CritterTesterCanibalize() {
        canvas = new CanvasWindow("Critter Test", 260, 260);

        Rectangle targetBounds = new Rectangle(40, 40, 100, 100);
        targetBounds.setStrokeColor(new Color(0, 0, 0, 0.1f));
        targetBounds.setStrokeWidth(3);
        canvas.add(targetBounds);

    testCritterCanibalize = new BoxBotCanibalize();
//        testCritter = new RoundBug();  // try these too
       // testCritter = new Mario();

        showCritter();
        run();
    }

    private void showCritter() {
        GraphicsGroup graphics = testCritterCanibalize.getGraphics();
        graphics.setPosition(40.0 + testCritterCanibalize.getxOffset(), 40.0 + testCritterCanibalize.getyOffset());
        canvas.add(graphics);
    }

    @SuppressWarnings("InfiniteLoopStatement")
    private void run() {
        testCritterCanibalize.setSpeed(10);
        Point center = testCritterCanibalize.getGraphics().getPosition();
        double t = 0;
        while (true) {
            testCritterCanibalize.setGoal(new Point2D.Double(
                    center.getX() + Math.cos(t) * 5 + 5,
                    center.getY() + Math.sin(t) * 5 + 5));
            testCritterCanibalize.moveTowardsGoal(0.05);

            canvas.draw();
            canvas.pause(50);
            t = (t + 0.1) % (Math.PI * 2);
        }
    }

    public static void main(String[] args) {
        new CritterTesterCanibalize();
    }
}