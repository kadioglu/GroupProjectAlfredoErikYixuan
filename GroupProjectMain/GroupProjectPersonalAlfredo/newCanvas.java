package GroupProjectPersonalAlfredo;

import comp127graphics.CanvasWindow;

public class newCanvas {

    private static final int canvasSize = 500;

    private CanvasWindow canvas;
    private Firer firer;
    private newBullHandle bulletHandler;

    private boolean animating = true; //testing

    public newCanvas() {
        canvas = new CanvasWindow("wrg(new)", 500, 500);
        firer = new Firer(canvas);
        bulletHandler = new newBullHandle(canvas);

        canvas.onMouseDown(event -> new newBull(canvas, event, firer, bulletHandler));
        canvas.onMouseMove(event -> updateCanvas());
    }


    public static void main(String[] args) {
        new newCanvas();
    }


//    private void updateCanvas() {
//        if (!bulletHandler.getBulletList().isEmpty()) {
////            while (animating) {
//            Iterator<newBull> bullIterator = bulletHandler.getBulletList().iterator();
//
//            while(bullIterator.hasNext()) {
//                newBull currentBull = bullIterator.next();
////                    currentBull.removeFromCanvas();
//                    currentBull.move();
//                    currentBull.addToCanvas();
//                    bullIterator.remove();
////                    canvas.pause(30);
//                }
////            }
//        }
//    }



    private void updateCanvas() {
        if (!bulletHandler.getBulletList().isEmpty()) {
//            while (animating) {
            for (newBull bullet : bulletHandler.getBulletList()) {
//                    bullet.removeFromCanvas();
                bullet.move();
                bullet.addToCanvas();
                canvas.pause(30);
            }
//            }
        }
    }





}
