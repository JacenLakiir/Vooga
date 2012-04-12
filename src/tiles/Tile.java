/**
 * Class to handle all platform and ground types
 * 
 * @author Glenn Rivkees
 * @author Ian McMahon
 */

package tiles;

import java.awt.image.BufferedImage;



import charactersprites.GameElement;

import com.golden.gamedev.Game;

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

