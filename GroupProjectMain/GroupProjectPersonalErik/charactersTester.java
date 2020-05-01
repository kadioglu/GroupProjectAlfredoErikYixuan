//package GroupProjectPersonalErik;
//
//import comp127graphics.CanvasWindow;
//import comp127graphics.Point;
//import comp127graphics.Rectangle;
//
//import java.awt.*;
//import java.awt.geom.Point2D;
//
//
///**
// * Created by shoop on 2/22/16.
// */
//public class charactersTester {
//
//    private CanvasWindow canvas;
//    private characters testPlayer;
//    private characters testSpiky;
//
//    public charactersTester() {
//        canvas = new CanvasWindow("characters Test", 500, 500);
//
//        Rectangle targetBounds = new Rectangle(40, 40, 400, 400);
//        targetBounds.setStrokeColor(new Color(0, 0, 0, 0.1f));
//        targetBounds.setStrokeWidth(3);
//        canvas.add(targetBounds);
//        testPlayer = new Player();
//        testSpiky = new Spiky();
//
//        showCharacter();
//        run();
//    }
//
//    private void showCharacter() {
//        canvas.add(testPlayer.getGraphics(),100,100);
//        canvas.add(testSpiky.getGraphics(),350,350);
//
//    }
//
//    @SuppressWarnings("InfiniteLoopStatement")
//    private void run() {
//        testPlayer.setSpeed(100);
//        testSpiky.setSpeed(80);
//        Point center = testPlayer.getGraphics().getPosition();
//        double t = 0;
//        while (true) {
//            testPlayer.setGoal(new Point2D.Double(
//                    center.getX() + Math.cos(t) * 5000 + 5,
//                    center.getY() + Math.sin(t) * 5000 + 5));
//            testPlayer.moveTowardsGoal(.05);
//
//
//            testSpiky.setGoal(new Point2D.Double(
//                    testPlayer.getPosition().getX(),
//                    testPlayer.getPosition().getY()
//            ));
//            testSpiky.moveTowardsGoal(.05);
//
//            canvas.draw();
//            canvas.pause(50);
//            t = (t + 0.1) % (Math.PI * 2);
//        }
//    }
//
//    public static void main(String[] args) {
//        new charactersTester();
//    }
//}