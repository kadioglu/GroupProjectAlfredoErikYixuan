package GroupProjectPersonalErik;

import comp127graphics.Rectangle;

import java.awt.*;

public class Player extends characters {

    private static final Color
            strokeColor = new Color(40, 40, 60),
            fillColor = new Color(160, 172, 182);

    @Override
    protected void buildGraphics() {


        Rectangle body = new Rectangle(-20, -30, 40, 40);
        body.setStrokeColor(strokeColor);
        body.setFilled(true);
        body.setFillColor(fillColor);
        getGraphics().add(body);
    }

}
