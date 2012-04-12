/**
 * Class to handle all platform and ground types
 * 
 * @author Glenn Rivkees
 * @author Ian McMahon
 */

package core.tiles;

import java.awt.image.BufferedImage;




import com.golden.gamedev.Game;

import core.characters.GameElement;

public abstract class Tile extends GameElement{

    /*
     * Constructor for a Concrete Platform
     */
    public Tile (Game owner) {
        super(owner);
    }

    /*
     * Constructor for a Platform Decorator 
     */
    public Tile () {
        super();
    }


    public void set(BufferedImage[] images, double x, double y) {
        this.setImages(images);
        this.setLocation(x, y);
    }

}

