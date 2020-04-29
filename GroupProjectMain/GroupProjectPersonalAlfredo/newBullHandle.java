package GroupProjectPersonalAlfredo;

import comp127graphics.CanvasWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates a new handler for bullets
 */
public class newBullHandle {

    private List<newBull> bulletList;

    /**
     * Constructs a list of all the bullets on the canvas
     * @param canvas The canvas which houses the bullets
     */
    public newBullHandle(CanvasWindow canvas) {
        bulletList = new ArrayList<>();


//Should be an iterator, b/c then can remove items from list while changing list
    }

    /**
     * Returns the bullet list
     * @return The list of bullets
     */
    public List<newBull> getBulletList() {
        return bulletList;
    }

    /**
     * Removes a bullet from the list
     * @param bullet The bullet to be removed
     */
    public void removeFromList(newBull bullet) {
        bulletList.remove(bullet);
    }


}
