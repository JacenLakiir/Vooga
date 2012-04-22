/**
 * Class to handle all platform and ground types
 * 
 * @author Glenn Rivkees
 * @author Ian McMahon
 */

package core.tiles;

import java.awt.image.BufferedImage;

import com.golden.gamedev.GameObject;

import core.characters.GameElement;

public class Tile extends GameElement{

    /*
     * Constructor for a Concrete Platform
     */
    public Tile (GameObject owner) {
        super(owner);
        this.setMovable(false);
        this.setPenetrable(false);
    }

    /*
     * Constructor for a Platform Decorator 
     */
    public Tile () {
        super();
    }

	public void afterHitFromBottomBy(GameElement e) {
		System.out.println("hit");
		
	}
	public void afterHitFromTopBy(GameElement e) {
		
	}
	public void afterHitFromLeftBy(GameElement e) {
	
}
	public void afterHitFromRightBy(GameElement e) {
	
}
    
    
}

