package GroupProjectPersonalAlfredo;

import comp127graphics.CanvasWindow;
import comp127graphics.Ellipse;

public class Firer extends Ellipse {

    public Firer(CanvasWindow canvas) {
        super(canvas.getWidth()/2.0, canvas.getHeight()/2.0, 10, 10);
        this.setCenter(canvas.getWidth()/2.0, canvas.getHeight()/2.0);
        canvas.add(this);
    }
}
