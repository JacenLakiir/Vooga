/**
 * Class to handle all platform and ground types
 * 
 * @author Glenn Rivkees
 * @author Ian McMahon
 */

package core.tiles;

import com.golden.gamedev.GameObject;

import core.characters.GameElement;
import core.physicsengine.physicsplugin.PhysicsAttributes;

public class Tile extends GameElement {

    /*
     * Constructor for a Concrete Platform
     */
    public Tile(GameObject owner, PhysicsAttributes physicsAttribute) {
        this(physicsAttribute);
        setGame(owner);
    }

    public Tile(PhysicsAttributes physicsAttribute) {
        super(physicsAttribute);
        this.getPhysicsAttribute().setMovable(false);
        this.getPhysicsAttribute().setPenetrable(false);
    }
    
    /*
     * Constructor for a Platform Decorator
     */
    public Tile() {
        super();
    }
    
    public Tile removeDecorator(){
    	return this;
    }
}
