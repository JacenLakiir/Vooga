/**
 * Class to handle all platform and ground types
 * 
 * @author Glenn Rivkees
 * @author Ian McMahon
 */

package setting;

import java.awt.image.BufferedImage;



import charactersprites.GameElement;

import com.golden.gamedev.Game;
import com.golden.gamedev.GameObject;

public abstract class Platform extends GameElement{

    /*
     * Constructor for a Concrete Platform
     */
    public Platform (GameObject owner) {
        super(owner);
    }

    /*
     * Constructor for a Platform Decorator 
     */
    public Platform () {
        super();
    }


    public void set(BufferedImage[] images, double x, double y) {
        this.setImages(images);
        this.setLocation(x, y);
    }

}

