package GroupProjectPersonalErik.Canibalize;

import comp127graphics.Rectangle;

import java.awt.*;

/**
 * @author Paul Cantrell
 */
public class BoxBotCanibalize extends CritterCanibalize {

    private static final Color
            strokeColor = new Color(40, 40, 60),
            fillColor = new Color(160, 172, 182);


    @Override
    protected void buildGraphics() {
        //
        // NOTE: the way this is drawn with negative values for x and y for parts
        //       of this critter, the initial location should be at least +20, +56
        //
        xOffset = 20.0;
        yOffset = 56.0;


        Rectangle body = new Rectangle(-20, -30, 40, 40);
        body.setStrokeColor(strokeColor);
        body.setFilled(true);
        body.setFillColor(fillColor);
        getGraphics().add(body);

        Rectangle head = new Rectangle(-18, -56, 36, 20);
        head.setStrokeColor(strokeColor);
        head.setFilled(true);
        head.setFillColor(fillColor);
        getGraphics().add(head);

    }
}
