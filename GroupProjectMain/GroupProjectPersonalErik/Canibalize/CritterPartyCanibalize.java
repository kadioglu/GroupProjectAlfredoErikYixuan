package GroupProjectPersonalErik.Canibalize;

import comp127graphics.CanvasWindow;
import comp127graphics.GraphicsObject;
import org.reflections.Reflections;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Paul Cantrell
 */
public class CritterPartyCanibalize {

    private static final double TARGET_FPS = 30, MIN_EFFECTIVE_FPS = 5;

    private final Random rand = new Random();
    private List<Class<? extends CritterCanibalize>> critterClasses;
    private List<CritterCanibalize> critterCanibalizes;
    private CanvasWindow canvas;

    @SuppressWarnings("InfiniteLoopStatement")
    public CritterPartyCanibalize() {
        canvas = new CanvasWindow("Critters", 2500, 1680);
        loadCritterClasses();
        critterCanibalizes = new ArrayList<>();
        for (int n = 0; n < 50; n++)
            addNewCritter();

        long prevFrameTime = 0;
        while (true) {
            long frameTime = System.currentTimeMillis();
            double dt = Math.min(1 / MIN_EFFECTIVE_FPS, (frameTime - prevFrameTime) / 1000.0);

            moveCritters(dt);

            prevFrameTime = frameTime;
            canvas.draw();
            canvas.pause(1000 / TARGET_FPS);
        }
    }

    private void addNewCritter() {
        CritterCanibalize critterCanibalize = createRandomCritter();

        GraphicsObject g = critterCanibalize.getGraphics();
        Point.Double point = randLocationFor(critterCanibalize);
        g.setPosition(point.getX(), point.getY());
        chooseNewGoal(critterCanibalize);
        critterCanibalize.setSpeed(rand.nextDouble() * 20 + 10);

        canvas.add(critterCanibalize.getGraphics());
        critterCanibalizes.add(critterCanibalize);
    }

    private CritterCanibalize createRandomCritter() {
        Class<? extends CritterCanibalize> critterClass = critterClasses.get(rand.nextInt(critterClasses.size()));
        try {
            return critterClass.getConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Cannot instantiate " + critterClass, e);
        }
    }

    private void moveCritters(double dt) {
        for (CritterCanibalize critterCanibalize : critterCanibalizes) {
            critterCanibalize.moveTowardsGoal(dt);

            // Near our goal? Just time to go somewhere else? Pick a new direction!
            double distToGoal = Math.hypot(
                    critterCanibalize.getGoal().getX() - critterCanibalize.getGraphics().getX(),
                    critterCanibalize.getGoal().getY() - critterCanibalize.getGraphics().getY());
            if (distToGoal < critterCanibalize.getSize() || rand.nextDouble() < dt / 10)
                chooseNewGoal(critterCanibalize);
        }
    }

    /**
     * Finds all subclasses of Critter in this package.
     */
    private void loadCritterClasses() {
        Reflections reflections = new Reflections(getClass().getPackage().getName());
        critterClasses = new ArrayList<>(
            reflections.getSubTypesOf(CritterCanibalize.class));
    }

    /**
     * Sends the critter off in a new direction.
     */
    private void chooseNewGoal(CritterCanibalize critterCanibalize) {
        critterCanibalize.setGoal(randLocationInRange(critterCanibalize.getGraphics()));
    }

    /**
     * Picks a random location that will approximately fit the given graphics object within the window.
     */
    private Point.Double randLocationFor(CritterCanibalize critterCanibalize) {
        GraphicsObject g = critterCanibalize.getGraphics();
        Rectangle2D bounds = g.getBounds();
        return new Point2D.Double(
                rand.nextDouble() * (canvas.getWidth() - (bounds.getWidth() + critterCanibalize.getxOffset())),
                rand.nextDouble() * (canvas.getHeight() - (bounds.getHeight() + critterCanibalize.getyOffset()))
        );
    }

    private Point.Double randLocationInRange(GraphicsObject g) {
        double maxRange = 500.0;
        Rectangle2D bounds = g.getBounds();
        comp127graphics.Point p0 = g.getPosition();
        double dx = rand.nextDouble() * (2.0 * maxRange) - maxRange;
        double dy = rand.nextDouble() * (2.0 * maxRange) - maxRange;
        Point.Double p = new Point.Double(p0.getX() + dx, p0.getY() + dy);
        p.setLocation(Math.max(0.0, Math.min(p.getX(), canvas.getWidth() - bounds.getWidth())), Math.max(0.0, Math.min(p.getY(), canvas.getHeight() - bounds.getHeight())));
        return p;
    }

    public static void main(String[] args) {
        new CritterPartyCanibalize();
    }
}
